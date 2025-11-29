package de.dnd.stiki.application.xml;

import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.domain.dndClass.DndClassRepository;
import de.dnd.stiki.domain.dndClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.domain.dndClass.counter.CounterEntity;
import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class DndClassXmlService extends AbstractXmlService<DndClassEntity, DndClassRepository> {

    @Override
    protected String getMainTagName() {
        return "class";
    }

    @Override
    protected DndClassEntity readData(Element classElement) {

        DndClassEntity entity = new DndClassEntity();

        entity.setName(getTextByTagName(classElement, "name"));

        String hitDice = getTextByTagName(classElement, "hd");
        if (hitDice != null) {
            entity.setHitDice(Integer.valueOf(hitDice));
        }

        List<String> mixedProficiencies = getProficiencyList(classElement, "proficiency");
        entity.setSavingThrowProficiencies(repository.getSavingThrowProficiencies(mixedProficiencies));
        entity.setSkillProficiencies(repository.getSkillProficiencies(mixedProficiencies));

        String numberOfSkillProficiencies = getTextByTagName(classElement, "numSkills");
        if (numberOfSkillProficiencies != null) {
            entity.setNumberOfSkillProficiencies(Integer.valueOf(numberOfSkillProficiencies));
        }
        entity.setArmorProficiencies(getProficiencyList(classElement, "armor"));
        entity.setWeaponProficiencies(getProficiencyList(classElement, "weapons"));
        entity.setToolProficiencies(getProficiencyList(classElement, "tools"));
        entity.setWealth(getTextByTagName(classElement, "wealth"));
        entity.setSpellAbility(getTextByTagName(classElement, "spellAbility"));

        NodeList levelNodes = classElement.getElementsByTagName("autolevel");
        entity.setClassLevels(getLevelEntities(levelNodes).stream().sorted(Comparator.comparing(ClassLevelEntity::getLevel)).toList());

        NodeList traitNodes = classElement.getElementsByTagName("trait");
        entity.setTraits(getTraitEntities(traitNodes));

        return entity;
    }

    private List<ClassLevelEntity> getLevelEntities(NodeList levelNodes) {
        List<ClassLevelEntity> levelEntities = new ArrayList<>();

        for (int i = 0; i < levelNodes.getLength(); i++) {
            Node node = levelNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;

            Element levelElement = (Element) node;
            String levelAttribute = levelElement.getAttribute("level");
            if (levelAttribute.isBlank()) continue;

            int level = Integer.parseInt(levelAttribute);

            // Check if entity for this level already exists
            ClassLevelEntity existing = levelEntities.stream()
                    .filter(e -> e.getLevel() == level)
                    .findFirst()
                    .orElse(null);

            if (existing != null) {
                // Merge new data into existing entity
                existing.getFeatures().addAll(
                        getFeatureEntities(levelElement.getElementsByTagName("feature"))
                );
                existing.getCounters().addAll(
                        getCounterEntities(levelElement.getElementsByTagName("counter"))
                );
            } else {
                // Create a new one if none exists
                ClassLevelEntity newEntity = getLevelEntity(levelElement);
                levelEntities.add(newEntity);
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
        levelEntity.setScoreImprovement(getXmlBooleanAttribute(levelElement, "scoreImprovement"));

        List<String> spellSlotsStringList = getListByTagName(levelElement, "slots");
        if (spellSlotsStringList != null) {
            levelEntity.setSpellSlots(spellSlotsStringList.stream().map(Integer::valueOf).toList());
        }

        levelEntity.setFeatures(getFeatureEntities(levelElement.getElementsByTagName("feature")));
        levelEntity.setCounters(getCounterEntities(levelElement.getElementsByTagName("counter")));

        return levelEntity;
    }

    private List<String> getProficiencyList(Element classElement, String tagName) {

        List<String> mixedProficiencies = new ArrayList<>();

        String concatinateMixedProficiencies = getTextByTagName(classElement, tagName);
        if (concatinateMixedProficiencies != null) {
            mixedProficiencies =  Arrays.asList(concatinateMixedProficiencies.split("\\s*,\\s*"));

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
        featureEntity.setOptional(getXmlBooleanAttribute(featureElement, "optional"));
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

    private List<TraitEntity> getTraitEntities(NodeList traitNodes) {
        List<TraitEntity> traitEntities = new ArrayList<>();
        for (int i = 0; i < traitNodes.getLength(); i++) {
            if (traitNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                traitEntities.add(getTraitEntity((Element) traitNodes.item(i)));
            }

        }
        return traitEntities;
    }

    private TraitEntity getTraitEntity(Element traitElement) {
        TraitEntity traitEntity = new TraitEntity();
        traitEntity.setName(getTextByTagName(traitElement, "name"));
        traitEntity.setText(getTextByTagName(traitElement, "text"));
        return traitEntity;
    }


}
