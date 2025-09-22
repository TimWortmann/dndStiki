package de.dnd.stiki.plugins.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/compendium")
public class CompendiumController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCompendium(@RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("Content: " + new String(file.getBytes(), StandardCharsets.UTF_8));

        return ResponseEntity.ok().body(file.getOriginalFilename());
    }
}
