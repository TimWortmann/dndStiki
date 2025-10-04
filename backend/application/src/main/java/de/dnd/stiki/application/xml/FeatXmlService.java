package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.feat.FeatEntity;
import de.dnd.stiki.domain.feat.FeatRepository;
import de.dnd.stiki.domain.feat.ModifierEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeatXmlService extends AbstractXmlService<FeatEntity, FeatRepository> {

    @Override
    protected String getMainTagName() {
        return "feat";
    }

    @Override
    protected FeatEntity readData(Element featElement) {

        FeatEntity featEntity = new FeatEntity();

        featEntity.setName(getTextByTagName(featElement, "name"));
        featEntity.setPrerequisites(getListByTagName(featElement, "prerequisite"));
        featEntity.setText(getTextByTagName(featElement, "text"));
        featEntity.setModifiers(getModifiers(featElement));

        return featEntity;
    }

    private List<ModifierEntity> getModifiers(Element featElement) {

        List<ModifierEntity> modifierEntityList = new ArrayList<>();
        NodeList modifierNodes = featElement.getElementsByTagName("modifier");
        for (int i = 0; i < modifierNodes.getLength(); i++) {
            if (modifierNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element modifierElement = (Element) modifierNodes.item(i);

                ModifierEntity modifierEntity = new ModifierEntity();

                String category = modifierElement.getAttribute("category");
                if (!category.isBlank()) {
                    modifierEntity.setCategory(category);
                }

                String value = modifierElement.getTextContent();
                if (!value.isBlank()) {
                    modifierEntity.setValue(value);
                }

                modifierEntityList.add(modifierEntity);
            }
        }

        return modifierEntityList;
    }
}
