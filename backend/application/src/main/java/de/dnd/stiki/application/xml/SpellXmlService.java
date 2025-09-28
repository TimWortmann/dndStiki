package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.domain.spell.SpellRepository;
import de.dnd.stiki.domain.spell.SpellSchool;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;

@Service
public class SpellXmlService extends AbstractXmlService<SpellEntity, SpellRepository> {

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

        String concatinatedComponents = getTextByTagName(spellElement, "components");
        if (concatinatedComponents != null) {
            List<String> components = Arrays.asList(concatinatedComponents.split("\\s*,\\s*"));
            spellEntity.setComponents(components);
        }

        spellEntity.setDuration(getTextByTagName(spellElement, "duration"));
        spellEntity.setText(getTextByTagName(spellElement, "text"));
        spellEntity.setRoll(getTextByTagName(spellElement, "roll"));

        String concatinatedClasses = getTextByTagName(spellElement, "classes");
        if (concatinatedClasses != null) {
            List<String> classes = Arrays.asList(concatinatedClasses.split("\\s*,\\s*"));
            spellEntity.setClasses(classes);
        }

        return spellEntity;
    }
}
