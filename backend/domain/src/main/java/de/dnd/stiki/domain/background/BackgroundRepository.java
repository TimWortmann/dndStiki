package de.dnd.stiki.domain.background;

import java.util.List;

public interface BackgroundRepository {

    public List<BackgroundEntity> getAllBackgrounds();

    public List<BackgroundEntity> createBackgrounds(List<BackgroundEntity> entities);

    public void deleteAllBackgrounds();
}
