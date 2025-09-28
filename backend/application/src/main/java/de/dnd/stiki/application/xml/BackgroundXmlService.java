package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BackgroundXmlService extends AbstractXmlService<BackgroundEntity, BackgroundRepository> {

    @Override
    public void readAndCreateData(Document document) {
        List<BackgroundEntity> backgrounds = readDataList(document, "background");
        repository.save(backgrounds);
    }

    @Override
    protected BackgroundEntity readData(Element backgroundElement) {

        BackgroundEntity backgroundEntity = new BackgroundEntity();

        backgroundEntity.setName(getTextByTagName(backgroundElement, "name"));

        String concatinatedProficiencies = getTextByTagName(backgroundElement, "proficiency");
        if (concatinatedProficiencies != null) {
            List<String> proficiencies = Arrays.asList(concatinatedProficiencies.split("\\s*,\\s*"));
            backgroundEntity.setProficiencies(proficiencies);
        }

        backgroundEntity.setTraits(getTraits(backgroundElement));

        return backgroundEntity;
    }

    private List<TraitEntity> getTraits(Element backgroundElement) {
        List<TraitEntity> traitEntityList = new ArrayList<>();
        NodeList traitNodes = backgroundElement.getElementsByTagName("trait");
        for (int i = 0; i < traitNodes.getLength(); i++) {

                TraitEntity traitEntity = new TraitEntity();
                traitEntity.setName(getTextByTagName(traitNodes.item(i), "name"));
                traitEntity.setText(getTextByTagName(traitNodes.item(i), "text"));
                traitEntityList.add(traitEntity);

        }
        return traitEntityList;
    }
}
