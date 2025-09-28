package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.item.ItemEntity;
import de.dnd.stiki.domain.item.ItemRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemXmlService extends AbstractXmlService<ItemEntity, ItemRepository> {


    @Override
    public void readAndCreateData(Document document) {
        List<ItemEntity> items = readDataList(document, "item");
        repository.saveItems(items);
    }

    @Override
    protected ItemEntity readData(Element itemElement) {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setName(getTextByTagName(itemElement, "name"));
        itemEntity.setDetail(getTextByTagName(itemElement, "detail"));
        itemEntity.setType(getTextByTagName(itemElement, "type"));

        String weight = getTextByTagName(itemElement, "weight");
        if (weight != null) {
            itemEntity.setWeight(Double.parseDouble(weight));
        }

        String value = getTextByTagName(itemElement, "value");
        if (value != null) {
            itemEntity.setValue(Double.parseDouble(value));
        }
        String concatinatedProperties = getTextByTagName(itemElement, "property");
        if (concatinatedProperties != null) {
            List<String> properties = Arrays.asList(concatinatedProperties.split("\\s*,\\s*"));
            itemEntity.setProperties(properties);
        }

        itemEntity.setDmg1(getTextByTagName(itemElement, "dmg1"));
        itemEntity.setDmg2(getTextByTagName(itemElement, "dmg2"));
        itemEntity.setDmgType(getTextByTagName(itemElement, "dmgType"));
        itemEntity.setRange(getTextByTagName(itemElement, "range"));

        String ac = getTextByTagName(itemElement, "ac");
        if (ac != null) {
            itemEntity.setAc(Integer.parseInt(ac));
        }

        itemEntity.setStealth(getXmlBooleanTag(itemElement, "stealth"));
        itemEntity.setMagic(getXmlBooleanTag(itemElement, "magic"));

        String strength = getTextByTagName(itemElement, "strength");
        if (strength != null) {
            itemEntity.setStrength(Integer.parseInt(strength));
        }

        itemEntity.setText(getTextByTagName(itemElement, "text"));

        return itemEntity;
    }
}
