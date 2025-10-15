package de.dnd.stiki.adapters.reader;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SubclassReader {

    public List<String> getDndSubclasses(List<String> featureNames) {
        List<String> subclasses = featureNames.stream()
                .map(name -> {
                    String prefix = null;
                    if (name.contains("Subclass: ")) {
                        prefix = "Subclass: ";
                    } else if (name.contains("Archetype: ")) {
                        prefix = "Archetype: ";
                    } else if (name.contains("College: ")) {
                        prefix = "College: ";
                    }

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

}
