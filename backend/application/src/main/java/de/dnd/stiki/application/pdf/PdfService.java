package de.dnd.stiki.application.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterRepository;
import de.dnd.stiki.domain.character.CharacterSkillEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.enums.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static de.dnd.stiki.domain.enums.AbilityType.*;
import static de.dnd.stiki.domain.enums.SkillType.*;

@Service
public class PdfService {

    @Autowired
    private CharacterRepository characterRepository;

    public File fillCharacterSheet(Long characterId) throws Exception {

        CharacterEntity character = characterRepository.get(characterId);

        Map<String, String> fieldValues = getFieldValues(character);

        // ✅ Load PDF from resources
        ClassPathResource resource = new ClassPathResource("Character Sheet.pdf");

        // ✅ Create a temporary output file (system temp directory)
        Path tempOutput = Files.createTempFile("Character Sheet (" + character.getName() + ")", ".pdf");

        try (InputStream inputStream = resource.getInputStream();
             PdfDocument pdfDoc = new PdfDocument(
                     new PdfReader(inputStream),
                     new PdfWriter(tempOutput.toFile()))
        ) {
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
            Map<String, PdfFormField> fields = form.getAllFormFields();

            for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
                PdfFormField field = fields.get(entry.getKey());
                if (field != null) {
                    field.setValue(entry.getValue());
                } else {
                    System.out.println("⚠️ Field not found: " + entry.getKey());
                }
            }
        }

        File file = tempOutput.toFile();
        file.deleteOnExit();
        return file;
    }

    private Map<String, String> getFieldValues( CharacterEntity character) {

        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("CharacterName", character.getName());
        fieldValues.put("ClassLevel", character.getDndClass() + " (Level " + character.getLevel().toString() + ")");
        fieldValues.put("Background", character.getBackground());
        fieldValues.put("Race ", character.getRace());

        setAbilityFieldValues(fieldValues, character, STRENGTH, "STR", 11);
        setAbilityFieldValues(fieldValues, character, DEXTERITY, "DEX", 18);
        setAbilityFieldValues(fieldValues, character, CONSTITUTION, "CON", 19);
        setAbilityFieldValues(fieldValues, character, INTELLIGENCE, "INT", 20);
        setAbilityFieldValues(fieldValues, character, WISDOM, "WIS", 21);
        setAbilityFieldValues(fieldValues, character, CHARISMA, "CHA", 22);

        fieldValues.put("ProfBonus", "+" + character.getProficiencyBonus());
        fieldValues.put("AC", character.getArmorClass().toString());
        fieldValues.put("Speed", character.getSpeed().toString() + " feet");
        fieldValues.put("Passive", character.getPassivePerception().toString());

        fieldValues.put("HPMax", character.getMaxHealth().toString());
        fieldValues.put("HPCurrent", character.getCurrentHealth().toString());

        fieldValues.put("HDTotal", character.getMaxHitDice().toString() + "d" + character.getHitDice().toString());
        fieldValues.put("HD", character.getCurrentHitDice().toString());

        setSkillFieldValues(fieldValues, character, ACROBATICS, 23);
        setSkillFieldValues(fieldValues, character, ANIMAL_HANDLING, 24, true);
        setSkillFieldValues(fieldValues, character, ARCANA, 25);
        setSkillFieldValues(fieldValues, character, ATHLETICS, 26);
        setSkillFieldValues(fieldValues, character, DECEPTION, 27, true);
        setSkillFieldValues(fieldValues, character, HISTORY, 28, true);
        setSkillFieldValues(fieldValues, character, INSIGHT, 29);
        setSkillFieldValues(fieldValues, character, INTIMIDATION, 30);
        setSkillFieldValues(fieldValues, character, INVESTIGATION ,31, true);
        setSkillFieldValues(fieldValues, character, MEDICINE, 32);
        setSkillFieldValues(fieldValues, character, NATURE, 33);
        setSkillFieldValues(fieldValues, character, PERCEPTION, 34, true);
        setSkillFieldValues(fieldValues, character, PERFORMANCE, 35);
        setSkillFieldValues(fieldValues, character, PERSUASION, 36);
        setSkillFieldValues(fieldValues, character, RELIGION, 37);
        setSkillFieldValues(fieldValues, character, SLEIGHT_OF_HAND, 38);
        setSkillFieldValues(fieldValues, character, STEALTH, 39, true);
        setSkillFieldValues(fieldValues, character, SURVIVAL, 40);

        return fieldValues;
    }

    private void setSkillFieldValues(Map<String, String> fieldValues, CharacterEntity character, SkillType skillType, int checkBoxNumber) {
        setSkillFieldValues(fieldValues, character, skillType, checkBoxNumber, false);
    }

    private void setSkillFieldValues(Map<String, String> fieldValues, CharacterEntity character, SkillType skillType, int checkBoxNumber, boolean whitespace) {
        String fieldName = StringUtils.trimAllWhitespace(skillType.getName());
        if (whitespace) {
            fieldName += " ";
        }
        if (ANIMAL_HANDLING.equals(skillType)) {
            fieldName = "Animal";
        }
        String skillModifierText = getSkillModifierString(character.getSkill(skillType), character.getProficiencyBonus());
        if (character.getSkill(skillType).getProficiency() == 2) {
            skillModifierText += "*";
        }
        fieldValues.put(fieldName, skillModifierText);
        if (character.getSkill(skillType).getProficiency() == 1) {
            fieldValues.put("Check Box " + checkBoxNumber, "Yes");
        }
    }

    private void setAbilityFieldValues(Map<String, String> fieldValues, CharacterEntity character, AbilityType abilityType, String shortName, int checkBoxNumber) {
        fieldValues.put(shortName, character.getAbility(abilityType).getScore().toString());
        String fieldNameMod = shortName + "mod";
        if (DEXTERITY.equals(abilityType)) {
            fieldNameMod += " ";
        }
        else if (CHARISMA.equals(abilityType)) {
            fieldNameMod = "CHamod";
        }
        fieldValues.put(fieldNameMod, getModifierString(character.getAbility(abilityType).getModifier()));
        String savingThrowModifierText = getSavingThrowModifierString(character.getAbility(abilityType), character.getProficiencyBonus());
        if (character.getAbility(abilityType).getSavingThrowProficiency() == 2) {
            savingThrowModifierText += "*";
        }
        fieldValues.put("ST " + abilityType.getName(), savingThrowModifierText);
        if (character.getAbility(abilityType).getSavingThrowProficiency() == 1) {
            fieldValues.put("Check Box " + checkBoxNumber, "Yes");
        }
    }

    private String getSavingThrowModifierString(CharacterAbilityEntity characterAbility, int proficiencyBonus) {
        return getModifierString(characterAbility.getSavingThrowModifier(proficiencyBonus));
    }

    private String getSkillModifierString(CharacterSkillEntity characterSkill, int proficiencyBonus) {
        return getModifierString(characterSkill.getModifierWithProficiency(proficiencyBonus));
    }

    private String getModifierString(Integer modifier) {
        if (modifier == null) {
            return "";
        }

        if (modifier > 0) {
            return "+" + modifier;
        }
        return modifier.toString();
    }
}
