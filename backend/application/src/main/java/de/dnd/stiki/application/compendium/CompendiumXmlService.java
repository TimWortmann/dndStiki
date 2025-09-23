package de.dnd.stiki.application.compendium;

import de.dnd.stiki.domain.compendium.CompendiumEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@Service
public class CompendiumXmlService {

    @Autowired
    BackgroundXmlService backgroundXmlService;

    @Autowired
    RaceXmlService raceXmlService;

    public void saveDataFromCompendium(CompendiumEntity compendiumEntity) {

        Document document = null;

        try {
            document = parseXmlToNormalizedDocument(compendiumEntity.getXmlContent());
            backgroundXmlService.readAndCreateData(document);
            raceXmlService.readAndCreateData(document);

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
}
