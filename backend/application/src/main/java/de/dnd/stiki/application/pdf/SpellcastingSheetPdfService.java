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
        if (character.getSpellcastingAbility() != null) {
            fieldValues.put("SpellcastingAbility 2", character.getSpellcastingAbility().getName());
            int spellAttackBonus = character.getProficiencyBonus() + character.getAbility(character.getSpellcastingAbility()).getModifier();
            fieldValues.put("SpellSaveDC  2", String.valueOf(8 + spellAttackBonus));
            fieldValues.put("SpellAtkBonus 2", "+" + spellAttackBonus);
        }

        setCantripFields(character, fieldValues);

        List<Integer> currentSpellSlots = getCurrentSpellSlots(character);
        setSpellFields(character, fieldValues, currentSpellSlots, 1, getLevel1Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 2, getLevel2Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 3, getLevel3Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 4, getLevel4Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 5, getLevel5Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 6, getLevel6Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 7, getLevel7Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 8, getLevel8Fields());
        setSpellFields(character, fieldValues, currentSpellSlots, 9, getLevel9Fields());

        return fieldValues;
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
        if (character.getSpellSlots() != null) {
            for (CharacterSpellSlotsEntity spellSlotsEntity : character.getSpellSlots()) {
                if (Objects.equals(character.getLevel(), spellSlotsEntity.getLevel())) {
                    return spellSlotsEntity.getSpellSlots();
                }
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

    private void setSpellFields(CharacterEntity character, Map<String, String> fieldValues, List<Integer> currentSpellSlots, int level, List<String> spellFields) {
        int slotsTotalFieldNumber = 18 + level;
        fieldValues.put("SlotsTotal " + slotsTotalFieldNumber, String.valueOf(getSpellSlots(currentSpellSlots, level)));
        List<SpellEntity> filteredSpells = getSpellsOfLevel(character.getSpells(), level);
        for (int i = 0; i < filteredSpells.size(); i++) {
            if (i < spellFields.size()) {
                fieldValues.put(spellFields.get(i), filteredSpells.get(i).getName());
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

    private List<String> getLevel1Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 1015");
        spellFields.add("Spells 1023");
        spellFields.add("Spells 1024");
        spellFields.add("Spells 1025");
        spellFields.add("Spells 1026");
        spellFields.add("Spells 1027");
        spellFields.add("Spells 1028");
        spellFields.add("Spells 1029");
        spellFields.add("Spells 1030");
        spellFields.add("Spells 1031");
        spellFields.add("Spells 1032");
        spellFields.add("Spells 1033");
        return spellFields;
    }

    private List<String> getLevel2Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 1046");
        spellFields.add("Spells 1034");
        spellFields.add("Spells 1035");
        spellFields.add("Spells 1036");
        spellFields.add("Spells 1037");
        spellFields.add("Spells 1038");
        spellFields.add("Spells 1039");
        spellFields.add("Spells 1040");
        spellFields.add("Spells 1041");
        spellFields.add("Spells 1042");
        spellFields.add("Spells 1043");
        spellFields.add("Spells 1044");
        spellFields.add("Spells 1045");
        return spellFields;
    }

    private List<String> getLevel3Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 1048");
        spellFields.add("Spells 1047");
        spellFields.add("Spells 1049");
        spellFields.add("Spells 1050");
        spellFields.add("Spells 1051");
        spellFields.add("Spells 1052");
        spellFields.add("Spells 1053");
        spellFields.add("Spells 1054");
        spellFields.add("Spells 1055");
        spellFields.add("Spells 1056");
        spellFields.add("Spells 1057");
        spellFields.add("Spells 1058");
        spellFields.add("Spells 1059");
        return spellFields;
    }

    private List<String> getLevel4Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 1061");
        spellFields.add("Spells 1060");
        spellFields.add("Spells 1062");
        spellFields.add("Spells 1063");
        spellFields.add("Spells 1064");
        spellFields.add("Spells 1065");
        spellFields.add("Spells 1066");
        spellFields.add("Spells 1067");
        spellFields.add("Spells 1068");
        spellFields.add("Spells 1069");
        spellFields.add("Spells 1070");
        spellFields.add("Spells 1071");
        spellFields.add("Spells 1072");
        return spellFields;
    }

    private List<String> getLevel5Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 1074");
        spellFields.add("Spells 1073");
        spellFields.add("Spells 1075");
        spellFields.add("Spells 1076");
        spellFields.add("Spells 1077");
        spellFields.add("Spells 1078");
        spellFields.add("Spells 1079");
        spellFields.add("Spells 1080");
        spellFields.add("Spells 1081");
        return spellFields;
    }

    private List<String> getLevel6Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 1083");
        spellFields.add("Spells 1082");
        spellFields.add("Spells 1084");
        spellFields.add("Spells 1085");
        spellFields.add("Spells 1086");
        spellFields.add("Spells 1087");
        spellFields.add("Spells 1088");
        spellFields.add("Spells 1089");
        spellFields.add("Spells 1090");
        return spellFields;
    }

    private List<String> getLevel7Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 1092");
        spellFields.add("Spells 1091");
        spellFields.add("Spells 1093");
        spellFields.add("Spells 1094");
        spellFields.add("Spells 1095");
        spellFields.add("Spells 1096");
        spellFields.add("Spells 1097");
        spellFields.add("Spells 1098");
        spellFields.add("Spells 1099");
        return spellFields;
    }

    private List<String> getLevel8Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 10101");
        spellFields.add("Spells 10100");
        spellFields.add("Spells 10102");
        spellFields.add("Spells 10103");
        spellFields.add("Spells 10104");
        spellFields.add("Spells 10105");
        spellFields.add("Spells 10106");
        return spellFields;
    }

    private List<String> getLevel9Fields() {
        List<String> spellFields = new ArrayList<>();
        spellFields.add("Spells 10108");
        spellFields.add("Spells 10107");
        spellFields.add("Spells 10109");
        spellFields.add("Spells 101010");
        spellFields.add("Spells 101011");
        spellFields.add("Spells 101012");
        spellFields.add("Spells 101013");
        return spellFields;
    }
}
