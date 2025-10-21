package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.application.pdf.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/character/{id}")
    public ResponseEntity<byte[]> getFilledCharacterSheet(Long id) throws Exception {
        File filledPdf = pdfService.fillCharacterSheet(id);
        byte[] pdfBytes = Files.readAllBytes(filledPdf.toPath());

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=\"Character_Sheet_Filled.pdf\"")
                .body(pdfBytes);
    }
}
