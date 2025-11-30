package de.dnd.stiki.application.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterRepository;
import de.dnd.stiki.domain.character.CharacterSpellSlotsEntity;
import de.dnd.stiki.domain.spell.SpellEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class SpellcastingSheetPdfService {

    @Autowired
    private CharacterRepository characterRepository;

    public File fillSpellcastingSheet(Long characterId) throws Exception {

        CharacterEntity character = characterRepository.get(characterId);

        Map<String, String> fieldValues = getFieldValues(character);

        // ✅ Load PDF from resources
        ClassPathResource resource = new ClassPathResource("Spellcasting Sheet.pdf");

        // ✅ Create a temporary output file (system temp directory)
        Path tempOutput = Files.createTempFile("Spellcasting Sheet (" + character.getName() + ")", ".pdf");

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
        fieldValues.put("Spellcasting Class 2", character.getDndClass());
        fieldValues.put("SpellcastingAbility 2", character.getSpellcastingAbility().getName());

        int spellAttackBonus = character.getProficiencyBonus() + character.getAbility(character.getSpellcastingAbility()).getModifier();
        fieldValues.put("SpellSaveDC  2", String.valueOf(8 + spellAttackBonus));
        fieldValues.put("SpellAtkBonus 2", "+" + spellAttackBonus);

        setCantripFields(character, fieldValues);

        List<Integer> currentSpellSlots = getCurrentSpellSlots(character);

        setLevel1Fields(character, fieldValues, currentSpellSlots);
        setLevelFields(character, fieldValues, currentSpellSlots, 2, 20, 1034, 13);
        setLevelFields(character, fieldValues, currentSpellSlots, 3, 21, 1047, 13);
        setLevelFields(character, fieldValues, currentSpellSlots, 4, 22, 1060, 13);
        setLevelFields(character, fieldValues, currentSpellSlots, 5, 23, 1073, 9);
        setLevelFields(character, fieldValues, currentSpellSlots, 6, 24, 1082, 9);
        setLevelFields(character, fieldValues, currentSpellSlots, 7, 25, 1091, 9);
        setLevelFields(character, fieldValues, currentSpellSlots, 8, 26, 10100, 7);
        setLevelFields(character, fieldValues, currentSpellSlots, 9, 27, 10107, 7);

        return fieldValues;
    }

    private void setLevel1Fields(CharacterEntity character, Map<String, String> fieldValues, List<Integer> currentSpellSlots) {
        fieldValues.put("SlotsTotal 19", String.valueOf(getSpellSlots(currentSpellSlots, 1)));
        List<String> level1Fields = getLevel1Fields();
        List<SpellEntity> level1Spells = getSpellsOfLevel(character.getSpells(), 1);
        for (int i = 0; i < level1Spells.size(); i++) {
            if (i < level1Fields.size()) {
                fieldValues.put(level1Fields.get(i), level1Spells.get(i).getName());
            }
        }
    }

    private void setCantripFields(CharacterEntity character, Map<String, String> fieldValues) {
        List<String> cantripFields = getCantripFields();
        List<SpellEntity> cantrips = getSpellsOfLevel(character.getSpells(), 0);
        for (int i = 0; i < cantrips.size(); i++) {
            if (i < cantripFields.size()) {
                fieldValues.put(cantripFields.get(i), cantrips.get(i).getName());
            }
        }
    }

    private List<String> getCantripFields() {
        List<String> cantripFields = new ArrayList<>();
        cantripFields.add("Spells 1014");
        cantripFields.add("Spells 1016");
        cantripFields.add("Spells 1017");
        cantripFields.add("Spells 1018");
        cantripFields.add("Spells 1019");
        cantripFields.add("Spells 1020");
        cantripFields.add("Spells 1021");
        cantripFields.add("Spells 1022");
        return cantripFields;
    }

    private List<String> getSpellFields(int startingFieldNumber, int numberOfFields) {
        List<String> spellFields = new ArrayList<>();
        for (int i = 0; i < numberOfFields; i++) {
            int fieldNumber = startingFieldNumber + i;
            spellFields.add("Spells " + fieldNumber);
        }
        return spellFields;
    }

    private List<SpellEntity> getSpellsOfLevel(List<SpellEntity> allSpells, int level) {
        List<SpellEntity> filteredSpells = new ArrayList<>();
        for (SpellEntity spell : allSpells) {
            if (spell.getLevel() == level) {
                filteredSpells.add(spell);
            }
        }
        return filteredSpells;
    }

    private List<Integer> getCurrentSpellSlots(CharacterEntity character) {
        for (CharacterSpellSlotsEntity spellSlotsEntity : character.getSpellSlots()) {
            if (Objects.equals(character.getLevel(), spellSlotsEntity.getLevel())) {
                return spellSlotsEntity.getSpellSlots();
            }
        }
        return new ArrayList<>();
    }

    private int getSpellSlots(List<Integer> currentSpellSlots, int level) {
        if (currentSpellSlots.size() < level + 1) {
            return 0;
        }
        return currentSpellSlots.get(level);
    }

    private List<String> getLevel1Fields() {
        List<String> level1Fields = new ArrayList<>();
        level1Fields.add("Spells 1015");
        level1Fields.add("Spells 1023");
        level1Fields.add("Spells 1024");
        level1Fields.add("Spells 1025");
        level1Fields.add("Spells 1026");
        level1Fields.add("Spells 1027");
        level1Fields.add("Spells 1028");
        level1Fields.add("Spells 1029");
        level1Fields.add("Spells 1030");
        level1Fields.add("Spells 1031");
        level1Fields.add("Spells 1032");
        level1Fields.add("Spells 1033");
        return level1Fields;
    }

    private void setLevelFields(CharacterEntity character, Map<String, String> fieldValues, List<Integer> currentSpellSlots, int level, int slotstotalFieldNumber, int startingNumber, int numberOfFields) {
        fieldValues.put("SlotsTotal " + slotstotalFieldNumber, String.valueOf(getSpellSlots(currentSpellSlots, level)));
        List<String> levelFields = getSpellFields(startingNumber, numberOfFields);
        List<SpellEntity> filteredSpells = getSpellsOfLevel(character.getSpells(), level);
        for (int i = 0; i < filteredSpells.size(); i++) {
            if (i < levelFields.size()) {
                fieldValues.put(levelFields.get(i), filteredSpells.get(i).getName());
            }
        }
        for (String levelField : levelFields) {
            fieldValues.put(levelField, levelField + " (Level " + level +" Field)");
        }
    }

    private List<String> getLevel9Fields() {
        List<String> level9Fields = new ArrayList<>();
        level9Fields.add("Spells 10108");
        level9Fields.add("Spells 10107");
        level9Fields.add("Spells 10109");
        level9Fields.add("Spells 101010");
        level9Fields.add("Spells 101012");
        level9Fields.add("Spells 101013");
        return level9Fields;
    }

    private void setLevel9Fields(CharacterEntity character, Map<String, String> fieldValues, List<Integer> currentSpellSlots) {
        fieldValues.put("SlotsTotal 27", String.valueOf(getSpellSlots(currentSpellSlots, 9)));
        List<String> level9Fields = getLevel9Fields();
        List<SpellEntity> level1Spells = getSpellsOfLevel(character.getSpells(), 9);
        for (int i = 0; i < level1Spells.size(); i++) {
            if (i < level9Fields.size()) {
                fieldValues.put(level9Fields.get(i), level1Spells.get(i).getName());
            }
        }
        for (String levelField : level9Fields) {
            fieldValues.put(levelField, levelField + " (Level 9 Field)");
        }
    }
}
