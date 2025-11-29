package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.enums.SpellSchool;
import de.dnd.stiki.domain.spell.CompendiumSpellRepository;
import de.dnd.stiki.domain.spell.SpellEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

@Service
public class SpellXmlService extends AbstractXmlService<SpellEntity, CompendiumSpellRepository> {

    @Override
    protected String getMainTagName() {
        return "spell";
    }

    @Override
    protected SpellEntity readData(Element spellElement) {

        SpellEntity spellEntity = new SpellEntity();
        spellEntity.setName(getTextByTagName(spellElement, "name"));

        String level = getTextByTagName(spellElement, "level");
        if (level == null) {
            level = "0";
        }
        spellEntity.setLevel(Integer.valueOf(level));

        String spellSchoolShortName = getTextByTagName(spellElement, "school");
        if (spellSchoolShortName != null) {
            spellEntity.setSchool(SpellSchool.fromShortName(spellSchoolShortName).toString());
        }

        spellEntity.setTime(getTextByTagName(spellElement, "time"));
        spellEntity.setRange(getTextByTagName(spellElement, "range"));
        spellEntity.setComponents(getListByTagName(spellElement, "components"));
        spellEntity.setDuration(getTextByTagName(spellElement, "duration"));
        spellEntity.setText(getTextByTagName(spellElement, "text"));
        spellEntity.setRoll(getTextByTagName(spellElement, "roll"));
        spellEntity.setClasses(getListByTagName(spellElement, "classes"));

        Boolean isRitual= getXmlBooleanTag(spellElement, "ritual");
        if (isRitual != null) {
            spellEntity.setRitual(isRitual);
        }

        return spellEntity;
    }
}
