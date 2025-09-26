package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.characterClass.CharacterClassEntity;
import de.dnd.stiki.domain.characterClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.domain.characterClass.counter.CounterEntity;
import de.dnd.stiki.domain.characterClass.feature.CharacterClassRepository;
import de.dnd.stiki.domain.characterClass.feature.FeatureEntity;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CharacterClassXmlService extends AbstractXmlService<CharacterClassEntity, CharacterClassRepository> {

    @Override
    public void readAndCreateData(Document document) {
        List<CharacterClassEntity> characterClasses = readDataList(document, "class");
        repository.createCharacterClasses(characterClasses);
    }

    @Override
    protected CharacterClassEntity readData(Element classElement) {

        CharacterClassEntity entity = new CharacterClassEntity();

        entity.setName(getTextByTagName(classElement, "name"));
        entity.setHitDice(Integer.valueOf(getTextByTagName(classElement, "hd")));

        List<String> mixedProficiencies = getProficiencyList(classElement, "proficiency");
        entity.setSavingThrowProficiencies(repository.getSavingThrowProficiencies(mixedProficiencies));
        entity.setSkillProficiencies(repository.getSkillProficiencies(mixedProficiencies));

        entity.setNumberOfSkillProficiencies(Integer.valueOf(getTextByTagName(classElement, "numSkills")));
        entity.setArmorProficiencies(getProficiencyList(classElement, "armor"));
        entity.setWeaponProficiencies(getProficiencyList(classElement, "weapons"));
        entity.setToolProficiencies(getProficiencyList(classElement, "tools"));
        entity.setWealth(getTextByTagName(classElement, "wealth"));
        entity.setSpellAbility(getTextByTagName(classElement, "spellAbility"));

        NodeList levelNodes = classElement.getElementsByTagName("autolevel");
        entity.setClassLevels(getLevelEntities(levelNodes));

        return entity;
    }

    private List<ClassLevelEntity> getLevelEntities(NodeList levelNodes) {

        List<ClassLevelEntity> levelEntities = new ArrayList<>();
        for (int i = 0; i < levelNodes.getLength(); i++) {
            if (levelNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                levelEntities.add(getLevelEntity((Element) levelNodes.item(i)));
            }
        }
        return levelEntities;
    }

    private ClassLevelEntity getLevelEntity(Element levelElement) {
        ClassLevelEntity levelEntity = new ClassLevelEntity();

        String levelAttribute = levelElement.getAttribute("level");
        if (!levelAttribute.isBlank()) {
            levelEntity.setLevel(Integer.valueOf(levelAttribute));
        }
        levelEntity.setScoreImprovement(getXmlBoolean(levelElement, "scoreImprovement"));
        levelEntity.setSpellSlots(getTextByTagName(levelElement, "slots"));

        levelEntity.setFeatures(getFeatureEntities(levelElement.getElementsByTagName("feature")));
        levelEntity.setCounters(getCounterEntities(levelElement.getElementsByTagName("counter")));

        return levelEntity;
    }

    private List<String> getProficiencyList(Element classElement, String tagName) {

        List<String> mixedProficiencies = new ArrayList<>();

        String concatinateMixedProficiencies = getTextByTagName(classElement, tagName);
        if (concatinateMixedProficiencies != null) {
            mixedProficiencies =  Arrays.asList(concatinateMixedProficiencies.split(", "));

        }
        return mixedProficiencies;
    }

    private List<FeatureEntity> getFeatureEntities(NodeList featureNodes) {
        List<FeatureEntity> featureEntities = new ArrayList<>();
        for (int i = 0; i < featureNodes.getLength(); i++) {
            if (featureNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                featureEntities.add(getFeatureEntity((Element) featureNodes.item(i)));
            }
        }
        return featureEntities;
    }

    private FeatureEntity getFeatureEntity(Element featureElement) {
        FeatureEntity featureEntity = new FeatureEntity();
        featureEntity.setOptional(getXmlBoolean(featureElement, "optional"));
        featureEntity.setName(getTextByTagName(featureElement, "name"));
        featureEntity.setText(getTextByTagName(featureElement, "text"));
        return featureEntity;
    }

    private List<CounterEntity> getCounterEntities(NodeList counterNodes) {
        List<CounterEntity> counterEntities = new ArrayList<>();
        for (int i = 0; i < counterNodes.getLength(); i++) {
            if (counterNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                counterEntities.add(getCounterEntity((Element) counterNodes.item(i)));
            }
        }
        return counterEntities;
    }

    private CounterEntity getCounterEntity(Element counterElement) {
       CounterEntity counterEntity = new CounterEntity();
       counterEntity.setName(getTextByTagName(counterElement, "name"));
       counterEntity.setReset(getTextByTagName(counterElement, "reset"));
       return counterEntity;
    }


}
