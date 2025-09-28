package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.domain.race.RaceRepository;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceXmlService extends AbstractXmlService<RaceEntity, RaceRepository> {

    @Override
    protected String getMainTagName() {
        return "race";
    }

    @Override
    protected RaceEntity readData(Element raceElement) {
        RaceEntity raceEntity = new RaceEntity();

        raceEntity.setName(getTextByTagName(raceElement, "name"));

        raceEntity.setSize(getTextByTagName(raceElement, "size"));

        String speed = getTextByTagName(raceElement, "speed");
        if (speed != null) {
            raceEntity.setSpeed(Integer.valueOf(speed));
        }
        raceEntity.setAbility(getTextByTagName(raceElement, "ability"));
        raceEntity.setSpellAbility(getTextByTagName(raceElement, "spellAbility"));


        List<TraitEntity> traitEntityList = new ArrayList<>();
        NodeList traitNodes = raceElement.getElementsByTagName("trait");
        for (int i = 0; i < traitNodes.getLength() ; i++) {

            TraitEntity traitEntity = new TraitEntity();
            traitEntity.setName(getTextByTagName(traitNodes.item(i), "name"));
            traitEntity.setText(getTextByTagName(traitNodes.item(i), "text"));
            traitEntityList.add(traitEntity);
        }
        raceEntity.setTraits(traitEntityList);

        return raceEntity;
    }
}
