package de.dnd.stiki.application.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterRepository;
import de.dnd.stiki.domain.spell.SpellEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpellInfoSheetPdfService {


    @Autowired
    private CharacterRepository characterRepository;

    PdfFont bold;

    public File createSpellInfoSheet(Long characterId) throws IOException {

        bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        CharacterEntity character = characterRepository.get(characterId);

        // ✅ Create a temporary output file
        Path tempOutput = Files.createTempFile("Spell Info Sheet (" + character.getName() + ")", ".pdf");

        // ✅ Create a new PDF
        try (PdfWriter writer = new PdfWriter(tempOutput.toFile());
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            List<SpellEntity> cantrips = getSpellsOfLevel(character.getSpells(), 0);
            if (!cantrips.isEmpty()) {
                document.add(new Paragraph("Cantrips").setFont(bold));
                addSpellBoxes(document, cantrips);
            }

            addSpellBoxes(character, document,1);
            addSpellBoxes(character, document,2);
            addSpellBoxes(character, document,3);
            addSpellBoxes(character, document,4);
            addSpellBoxes(character, document,5);
            addSpellBoxes(character, document,6);
            addSpellBoxes(character, document,7);
            addSpellBoxes(character, document,8);
            addSpellBoxes(character, document,9);
        }

        File file = tempOutput.toFile();
        file.deleteOnExit();
        return file;
    }

    private void addSpellBoxes(CharacterEntity character, Document document, int level) {
        List<SpellEntity> filteredSpells = getSpellsOfLevel(character.getSpells(), level);
        if (!filteredSpells.isEmpty()) {
            if (document.getPdfDocument().getNumberOfPages() > 0) {
                document.add(new AreaBreak());
            }
            document.add(new Paragraph("Level " + level + " Spells").setFont(bold));
            addSpellBoxes(document, filteredSpells);
        }
    }

    private Div getSpellBox() {
        Div spellBox = new Div();
        spellBox.setBorder(new DottedBorder(1));     // 1pt solid black border
        spellBox.setPadding(8);                     // space inside box
        spellBox.setMarginBottom(8);                // space between boxes
        spellBox.setWidth(UnitValue.createPercentValue(100)); // full width
        return spellBox;
    }

    private void addSpellBoxes(Document document, List<SpellEntity> spells) {
        for (SpellEntity spell : spells) {
            Div spellBox = getSpellBox();
            spellBox.add(new Paragraph(spell.getName()).setFont(bold).setFontSize(12));
            spellBox.add(new Paragraph(spell.getText()).setFontSize(10));
            document.add(spellBox);
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
}
