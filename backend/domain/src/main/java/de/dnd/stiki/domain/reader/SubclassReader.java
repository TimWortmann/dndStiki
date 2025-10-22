package de.dnd.stiki.domain.reader;

import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.enums.SubclassPrefix;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class SubclassReader {

    public List<String> getDndSubclasses(List<String> featureNames) {
        List<String> subclasses = featureNames.stream()
                .map(name -> {
                    String prefix = getPrefix(name);
                    if (prefix != null) {
                        return name.substring(name.indexOf(prefix) + prefix.length()).trim();
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toCollection(LinkedList::new));

        subclasses.addFirst("No Subclass");

        return subclasses;
    }

    private static String getPrefix(String name) {
        for (SubclassPrefix subclassPrefix : SubclassPrefix.values()) {
            String potentialPrefix = subclassPrefix.getName() + ": ";
            if (name.contains(potentialPrefix)) {
                return potentialPrefix;
            }
        }
        return null;
    }

    public List<TraitEntity> getRelevantClassFeatures(CharacterEntity character, boolean filterLevelFeatures) {
        List<TraitEntity> relevantClassFeatures = new ArrayList<>();

        for (TraitEntity classFeature : character.getClassFeatures()) {
            String featureName = classFeature.getName();

            // Check if feature has a level requirement
            Matcher levelMatcher = Pattern.compile("Level (\\d+)").matcher(featureName);
            if (levelMatcher.find()) {
                int levelNumber = Integer.parseInt(levelMatcher.group(1));
                if (filterLevelFeatures && levelNumber > character.getLevel()) {
                    continue; // Skip this feature
                }
            }

            // Hide subclass-related features before level 3
            if (filterLevelFeatures && character.getLevel() < 3 && featuresIncludesSubclassPrefix(featureName)) {
                continue;
            }

            // Only filter by subclass if level >= 3 and subclass is selected
            if (character.getLevel() >= 3 && character.getDndSubclass() != null && !"No Subclass".equals(character.getDndSubclass())) {

                boolean belongsToOtherSubclass = false;
                for (String subclass : character.getDndSubclasses()) {
                    if (featureName.toLowerCase().contains(subclass.toLowerCase())
                            && !subclass.equalsIgnoreCase(character.getDndSubclass())) {
                        belongsToOtherSubclass = true;
                        break;
                    }
                }
                if (belongsToOtherSubclass) {
                    continue; // Skip features from other subclasses
                }
            }

            // Feature passed all checks → include it
            relevantClassFeatures.add(classFeature);
        }

        return relevantClassFeatures;
    }

    private boolean featuresIncludesSubclassPrefix(String featureName) {
        for (SubclassPrefix subclassPrefix : SubclassPrefix.values()) {
            if (featureName.toLowerCase().contains(subclassPrefix.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
