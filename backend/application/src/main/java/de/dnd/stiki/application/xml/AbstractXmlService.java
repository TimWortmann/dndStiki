package de.dnd.stiki.application.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class AbstractXmlService <E, R>  {

    @Autowired
    R repository;

    public abstract void readAndCreateData(Document document);

    protected List<E> readDataList(Document document, String mainTagName) {
        List<E> dataList = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName(mainTagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                dataList.add(readData((Element) nodeList.item(i)));
            }
        }

        return dataList;
    }

    protected abstract E readData(Element mainTagElement);

    protected String getTextByTagName(Element element, String tagName) {
        Node node = element.getElementsByTagName(tagName).item(0);

        if (node == null) {
            return null;
        }

        if (node.getTextContent().isBlank()) {
            return null;
        }

        return node.getTextContent();
    }

    protected String getTextByTagName(Node node, String tagName) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return getTextByTagName((Element) node, tagName);
        }

        return null;
    }

    protected boolean getXmlBooleanAttribute(Element element, String attributeName) {
        String booleanString = element.getAttribute(attributeName);

        return "YES".equals(booleanString);
    }

    protected Boolean getXmlBooleanTag(Element element, String tagName) {
        String booleanString = getTextByTagName(element, tagName);

        if (booleanString == null) {
            return null;
        }

        return "YES".equals(booleanString);
    }

}
