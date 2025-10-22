package de.dnd.stiki.application.pdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfService {

    @Autowired
    private CharacterSheetPdfService characterSheetPdfService;

    @Autowired
    private FeatureSheetPdfService featureSheetPdfService;

    public File fillCharacterSheet(Long characterId) throws Exception {
        return characterSheetPdfService.fillCharacterSheet(characterId);
    }

    public File createFeatureSheet(Long characterId, boolean filterLevelFeatures) throws IOException {
        return featureSheetPdfService.createFeatureSheet(characterId, filterLevelFeatures);
    }
}
