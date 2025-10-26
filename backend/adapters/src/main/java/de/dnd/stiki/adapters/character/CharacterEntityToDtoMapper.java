package de.dnd.stiki.adapters.character;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterItem.CharacterItemDto;
import de.dnd.stiki.adapters.character.characterItem.CharacterItemEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterShield.CharacterShieldEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillEntityToDtoMapper;
import de.dnd.stiki.adapters.trait.TraitDto;
import de.dnd.stiki.adapters.trait.TraitEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterItemEntity;
import de.dnd.stiki.domain.enums.ItemType;
import de.dnd.stiki.domain.reader.SubclassReader;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static de.dnd.stiki.domain.enums.ItemType.*;

@Component
public class CharacterEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterEntity, CharacterDto> {

    @Autowired
    private CharacterAbilityEntityToDtoMapper abilityEntityToDtoMapper;

    @Autowired
    private CharacterSkillEntityToDtoMapper skillEntityToDtoMapper;

    @Autowired
    private CharacterItemEntityToDtoMapper itemEntityToDtoMapper;

    @Autowired
    private TraitEntityToDtoMapper traitEntityToDtoMapper;

    @Autowired
    private SubclassReader subclassReader;

    @Autowired
    private CharacterShieldEntityToDtoMapper shieldEntityToDtoMapper;

    @Override
    public CharacterDto mapEntityToDto(CharacterEntity entity) {

        CharacterDto dto = new CharacterDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLevel(entity.getLevel());
        dto.setDndClass(entity.getDndClass());
        dto.setDndSubclass(entity.getDndSubclass());
        dto.setDndSubclasses(entity.getDndSubclasses());
        if (entity.getSpellcastingAbility() != null) {
            dto.setSpellcastingAbility(entity.getSpellcastingAbility().getName());
        }
        dto.setBackground(entity.getBackground());
        dto.setRace(entity.getRace());
        dto.setMaxHealth(entity.getMaxHealth());
        dto.setCurrentHealth(entity.getCurrentHealth());
        dto.setHitDice(entity.getHitDice());
        dto.setMaxHitDice(entity.getMaxHitDice());
        dto.setCurrentHitDice(entity.getCurrentHitDice());
        dto.setArmorClass(entity.getArmorClass());
        dto.setSpeed(entity.getSpeed());
        dto.setPassivePerception(entity.getPassivePerception());
        dto.setProficiencyBonus(entity.getProficiencyBonus());
        dto.setAbilities(abilityEntityToDtoMapper.mapEntitiesToDtos(entity.getAbilities()));
        dto.setSkills(skillEntityToDtoMapper.mapEntitiesToDtos(entity.getSkills()));

        setInventory(dto, entity.getItems());

        dto.setClassFeatures(getRelevantClassFeatures(entity));
        dto.setBackgroundTraits(traitEntityToDtoMapper.mapEntitiesToDtos(entity.getBackgroundTraits()));
        dto.setRaceTraits(traitEntityToDtoMapper.mapEntitiesToDtos(entity.getRaceTraits()));
        dto.setFeats(traitEntityToDtoMapper.mapEntitiesToDtos(entity.getFeats()));

        dto.setEquippedShield(shieldEntityToDtoMapper.mapEntityToDto(entity.getEquippedShield()));

        return dto;
    }

    private void setInventory(CharacterDto dto, List<CharacterItemEntity> itemEntities) {

        dto.setItems(new ArrayList<>());
        dto.setEquipment(new ArrayList<>());

        if (itemEntities == null) {
            return;
        }

        for (CharacterItemEntity itemEntity : itemEntities) {
            CharacterItemDto itemDto = itemEntityToDtoMapper.mapEntityToDto(itemEntity);

            List<ItemType> equipmentItems = List.of(WEAPON, MELEE_WEAPON, RANGED_WEAPON, ARMOR, LIGHT_ARMOR, MEDIUM_ARMOR, HEAVY_ARMOR, SHIELD);
            if (equipmentItems.contains(itemEntity.getType())) {
                dto.getEquipment().add(itemDto);
                continue;
            }

            dto.getItems().add(itemDto);
        }
    }

    private List<TraitDto> getRelevantClassFeatures(CharacterEntity entity) {

        List<TraitEntity> relevantClassFeatures = subclassReader.getRelevantClassFeatures(entity, true);

        return traitEntityToDtoMapper.mapEntitiesToDtos(relevantClassFeatures);
    }
}
