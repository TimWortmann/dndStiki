package de.dnd.stiki.application.compendium;

import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.domain.compendium.CompendiumEntity;
import de.dnd.stiki.domain.compendium.CompendiumRepository;
import de.dnd.stiki.domain.race.RaceRepository;
import de.dnd.stiki.domain.trait.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class CompendiumService {

    @Autowired
    private CompendiumRepository repository;

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Autowired
    private TraitRepository traitRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private CompendiumXmlService xmlService;

    public String getCompendiumFileName(){
        return repository.getCompendium().getFileName();
    }

    public String uploadCompendium(MultipartFile file) throws IOException {
        CompendiumEntity entity = new CompendiumEntity();
        entity.setFileName(file.getOriginalFilename());
        entity.setXmlContent(new String(file.getBytes(), StandardCharsets.UTF_8));

        deleteCompendium();

        entity = repository.saveCompendium(entity);
        xmlService.saveDataFromCompendium(entity);

        return entity.getFileName();
    }

    public void deleteCompendium(){
        repository.deleteCompendium();
        backgroundRepository.deleteAllBackgrounds();
        traitRepository.deleteAllTraits();
        raceRepository.deleteAllRaces();
    }


}
