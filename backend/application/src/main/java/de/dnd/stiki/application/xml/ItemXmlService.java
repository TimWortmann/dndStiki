package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.item.*;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemXmlService extends AbstractXmlService<ItemEntity, ItemRepository> {

    @Override
    protected String getMainTagName() {
        return "item";
    }

    @Override
    protected ItemEntity readData(Element itemElement) {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setName(getTextByTagName(itemElement, "name"));
        itemEntity.setDetail(getTextByTagName(itemElement, "detail"));

        String type = getTextByTagName(itemElement, "type");
        if (type != null) {
            itemEntity.setType(ItemType.fromShortName(type).toString());
        }

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
            String[] properties = concatinatedProperties.split("\\s*,\\s*");

            List<String> fullNameProperties = new ArrayList<>();
            for (String property : properties) {
                fullNameProperties.add(ItemProperty.fromShortName(property).toString());
            }

            itemEntity.setProperties(fullNameProperties);
        }

        itemEntity.setDmg1(getTextByTagName(itemElement, "dmg1"));
        itemEntity.setDmg2(getTextByTagName(itemElement, "dmg2"));

        String dmgType = getTextByTagName(itemElement, "dmgType");
        if (dmgType != null) {
            itemEntity.setDmgType(DamageType.fromShortName(dmgType).toString());
        }

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
