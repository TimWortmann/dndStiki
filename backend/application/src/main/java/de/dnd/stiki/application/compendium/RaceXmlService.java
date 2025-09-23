package de.dnd.stiki.application.compendium;

import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.domain.race.RaceRepository;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceXmlService extends AbstractXmlService<RaceEntity, RaceRepository> {

    @Autowired
    private RaceRepository repository;

    @Override
    public void readAndCreateData(Document document) {
        List<RaceEntity> races = readDataList(document, "race");
        repository.createRaces(races);
    }

    @Override
    protected RaceEntity readData(Element raceElement) {
        RaceEntity raceEntity = new RaceEntity();

        raceEntity.setName(raceElement.getFirstChild().getTextContent());

        raceEntity.setSize(getTextByTagName(raceElement, "size"));
        raceEntity.setSpeed(Integer.valueOf(getTextByTagName(raceElement, "speed")));
        raceEntity.setAbility(getTextByTagName(raceElement, "ability"));
        raceEntity.setSpellAbility(getTextByTagName(raceElement, "spellAbility"));


        List<TraitEntity> traitEntityList = new ArrayList<>();
        NodeList traitNodes = raceElement.getElementsByTagName("trait");
        for (int i = 0; i < traitNodes.getLength() ; i++) {

            TraitEntity traitEntity = new TraitEntity();
            NodeList traitChildNodes = traitNodes.item(i).getChildNodes();
            traitEntity.setName(traitChildNodes.item(0).getTextContent());
            traitEntity.setText(traitChildNodes.item(1).getTextContent());
            traitEntityList.add(traitEntity);
        }
        raceEntity.setTraits(traitEntityList);

        return raceEntity;
    }
}
