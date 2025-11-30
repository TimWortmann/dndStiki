package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.application.pdf.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/character/{id}")
    public ResponseEntity<byte[]> getFilledCharacterSheet(@PathVariable Long id) throws Exception {
        File filledPdf = pdfService.fillCharacterSheet(id);
        byte[] pdfBytes = Files.readAllBytes(filledPdf.toPath());

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=\"Character Sheet (Filled).pdf\"")
                .body(pdfBytes);
    }

    @GetMapping("/features/{id}")
    public ResponseEntity<byte[]> getFeatureSheet(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean filterLevelFeatures) throws Exception {
        File filledPdf = pdfService.createFeatureSheet(id, filterLevelFeatures);
        byte[] pdfBytes = Files.readAllBytes(filledPdf.toPath());

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=\"Feature Sheet (Filled).pdf\"")
                .body(pdfBytes);
    }

    @GetMapping("/spellcasting/{id}")
    public ResponseEntity<byte[]> getFilledSpellcastingSheet(@PathVariable Long id) throws Exception {
        File filledPdf = pdfService.fillSpellcastingSheet(id);
        byte[] pdfBytes = Files.readAllBytes(filledPdf.toPath());

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=\"Spellcasting Sheet (Filled).pdf\"")
                .body(pdfBytes);
    }
}
