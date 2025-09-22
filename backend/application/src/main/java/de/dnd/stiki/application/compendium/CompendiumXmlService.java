package de.dnd.stiki.application.compendium;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.domain.compendium.CompendiumEntity;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CompendiumXmlService {

    @Autowired
    BackgroundRepository backgroundRepository;

    public void saveDataFromCompendium(CompendiumEntity compendiumEntity) {

        Document document = null;

        try {
            document = parseXmlToNormalizedDocument(compendiumEntity.getXmlContent());
            readAndCreateBackgrouns(document);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Document parseXmlToNormalizedDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        InputSource is = new InputSource(new StringReader(xml));
        return getDocumentBuilder().parse(is);
    }

    private DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        return factory.newDocumentBuilder();
    }

    private void readAndCreateBackgrouns(Document document) {
        List<BackgroundEntity> backgrounds = readBackgrounds(document);
        backgroundRepository.deleteAllBackgrounds();
        backgroundRepository.createBackgrounds(backgrounds);
    }

    private List<BackgroundEntity> readBackgrounds(Document document) {
        List<BackgroundEntity> backgrounds = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName("background");
        for (int i = 0; i < nodeList.getLength(); i++) {
            backgrounds.add(readBackground(nodeList.item(i)));
        }

        return backgrounds;
    }

    private BackgroundEntity readBackground(Node backgroundNode) {

        if (backgroundNode.getNodeType() == Node.ELEMENT_NODE) {
            BackgroundEntity backgroundEntity = new BackgroundEntity();

            NodeList backgroundChildNodes = backgroundNode.getChildNodes();

            backgroundEntity.setName(backgroundChildNodes.item(0).getTextContent());

            if (backgroundChildNodes.item(1).getNodeName().equals("proficiency")) {
                String concatinatedProficiencies = backgroundChildNodes.item(1).getTextContent();
                concatinatedProficiencies = StringUtils.trimAllWhitespace(concatinatedProficiencies);
                List<String> proficiencies = Arrays.asList(concatinatedProficiencies.split(","));
                backgroundEntity.setProficiencies(proficiencies);
            }


            List<TraitEntity> traitEntityList = new ArrayList<>();
            int length = backgroundChildNodes.getLength();
            Node errorNode = backgroundChildNodes.item(3);
            for (int i = 2; i < backgroundChildNodes.getLength(); i++) {
                if (backgroundChildNodes.item(i).getNodeName().equals("trait")) {
                    TraitEntity traitEntity = new TraitEntity();

                    NodeList traitChildNodes = backgroundChildNodes.item(i).getChildNodes();
                    traitEntity.setName(traitChildNodes.item(0).getTextContent());
                    traitEntity.setText(traitChildNodes.item(1).getTextContent());
                    traitEntityList.add(traitEntity);
                }
            }
            backgroundEntity.setTraits(traitEntityList);

            return backgroundEntity;
        }

        return  null;
    }
}
