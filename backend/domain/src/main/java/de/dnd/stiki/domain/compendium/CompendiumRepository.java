package de.dnd.stiki.domain.compendium;

public interface CompendiumRepository {

    public CompendiumEntity getCompendium();

    public CompendiumEntity saveCompendium(CompendiumEntity entity);

    public void deleteCompendium();
}
