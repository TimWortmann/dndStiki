package de.dnd.stiki.application;

import de.dnd.stiki.application.xml.CompendiumXmlService;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.domain.characterClass.CharacterClassRepository;
import de.dnd.stiki.domain.compendium.CompendiumEntity;
import de.dnd.stiki.domain.compendium.CompendiumRepository;
import de.dnd.stiki.domain.feat.FeatRepository;
import de.dnd.stiki.domain.item.ItemRepository;
import de.dnd.stiki.domain.race.RaceRepository;
import de.dnd.stiki.domain.spell.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class CompendiumService {

    @Autowired
    private CompendiumRepository compendiumRepository;

    @Autowired
    private CompendiumXmlService xmlService;

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private CharacterClassRepository characterClassRepository;

    @Autowired
    SpellRepository spellRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FeatRepository featRepository;

    public String getCompendiumFileName(){
        return compendiumRepository.getCompendium().getFileName();
    }

    public String uploadCompendium(MultipartFile file) throws IOException {
        CompendiumEntity entity = new CompendiumEntity();
        entity.setFileName(file.getOriginalFilename());
        entity.setXmlContent(new String(file.getBytes(), StandardCharsets.UTF_8));

        deleteCompendium();

        entity = compendiumRepository.saveCompendium(entity);
        xmlService.saveDataFromCompendium(entity);

        return entity.getFileName();
    }

    public void deleteCompendium(){
        backgroundRepository.deleteAll();
        raceRepository.deleteAll();
        characterClassRepository.deleteAll();
        spellRepository.deleteAll();
        itemRepository.deleteAll();
        featRepository.deleteAll();
        compendiumRepository.deleteCompendium();
    }


}
