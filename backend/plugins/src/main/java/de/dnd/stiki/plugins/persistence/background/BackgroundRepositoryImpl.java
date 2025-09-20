package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackgroundRepositoryImpl implements BackgroundRepository {

    @Autowired
    private BackgroundJpaRepository jpaRepository;

    @Autowired
    private BackgroundJpaToEntityMapper jpaToEntityMapper;

    @Override
    public List<BackgroundEntity> getAllBackgrounds() {

        List<BackgroundJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);


        /*BackgroundEntity background = new BackgroundEntity();
        background.setName("Acolyte");
        background.setProficiencies(List.of("Insight", "Religion"));

        TraitEntity trait = new TraitEntity();
        trait.setText("""
                 You have spent your life in the service of a temple to a specific god or pantheon of gods. You act as an intermediary between the realm of the holy and the mortal world, performing sacred rites and offering sacrifices in order to conduct worshipers into the presence of the divine. You are not necessarily a cleric—performing sacred rites is not the same thing as channeling divine power.
                 Choose a god, a pantheon of gods, or some other quasi-divine being from among those listed in appendix B or those specified by your DM, and work with your DM to detail the nature of your religious service. Were you a lesser functionary in a temple, raised from childhood to assist the priests in the sacred rites? Or were you a high priest who suddenly experienced a call to serve your god in a different way? Perhaps you were the leader of a small cult outside of any established temple structure, or even an occult group that served a fiendish master that you now deny.
                
                 • Skill Proficiencies: Insight, Religion
                 • Languages: Two of your choice
                 • Equipment: A holy symbol (a gift to you when you entered the priesthood), a prayer book or prayer wheel, 5 sticks of incense, vestments, a set of common clothes, and a belt pouch containing 15 gp
                
                 Source: Player&#39;s Handbook p. 127
                 """);
        background.setTraits(List.of(trait));*/
    }
}
