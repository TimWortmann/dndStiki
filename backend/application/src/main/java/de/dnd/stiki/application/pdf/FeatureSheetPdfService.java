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
import de.dnd.stiki.domain.reader.SubclassReader;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FeatureSheetPdfService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private SubclassReader subclassReader;

    PdfFont bold;

    public File createFeatureSheet(Long characterId, boolean filterLevelFeatures) throws IOException {

        bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        CharacterEntity character = characterRepository.get(characterId);

        // ✅ Create a temporary output file
        Path tempOutput = Files.createTempFile("Feature Sheet (" + character.getName() + ")", ".pdf");

        // ✅ Create a new PDF
        try (PdfWriter writer = new PdfWriter(tempOutput.toFile());
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("Feature & Traits Sheet (" + character.getName() + ")")
                    .setFont(bold)
                    .setFontSize(20));

            document.add(new Paragraph("Background Traits (" + character.getBackground() + ")").setFont(bold));
            addTraitBoxes(document, character.getBackgroundTraits());

            document.add(new AreaBreak());
            document.add(new Paragraph("Race Traits (" + character.getRace() + ")").setFont(bold));
            addTraitBoxes(document, character.getRaceTraits());

            if (character.getFeats() != null && !character.getFeats().isEmpty()) {
                document.add(new AreaBreak());
                document.add(new Paragraph("Feats").setFont(bold));
                addTraitBoxes(document, character.getFeats());
            }

            String classFeaturesHeader = "Class Features (" + character.getDndClass() + " | " + character.getDndSubclass() + " | Level ";
            if (filterLevelFeatures) {
                classFeaturesHeader += character.getLevel().toString();
            }
            else {
                classFeaturesHeader += "20";
            }
            classFeaturesHeader += ")";

            document.add(new AreaBreak());
            document.add(new Paragraph(classFeaturesHeader).setFont(bold));
            addTraitBoxes(document, subclassReader.getRelevantClassFeatures(character, filterLevelFeatures));
        }

        File file = tempOutput.toFile();
        file.deleteOnExit();
        return file;
    }

    private Div getTraitBox() {
        Div featureBox = new Div();
        featureBox.setBorder(new DottedBorder(1));     // 1pt solid black border
        featureBox.setPadding(8);                     // space inside box
        featureBox.setMarginBottom(8);                // space between boxes
        featureBox.setWidth(UnitValue.createPercentValue(100)); // full width
        return featureBox;
    }

    private void addTraitBoxes(Document document, List<TraitEntity> traits) {
        for (TraitEntity trait : traits) {
            Div featureBox = getTraitBox();
            featureBox.add(new Paragraph(trait.getName()).setFont(bold).setFontSize(12));
            featureBox.add(new Paragraph(trait.getText()).setFontSize(10));
            document.add(featureBox);
        }
    }
}
