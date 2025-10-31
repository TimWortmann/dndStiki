package de.dnd.stiki.domain.helper;

import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import de.dnd.stiki.domain.character.CharacterAttackEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttackHelper {

    public String getFinalHitBonus(CharacterAttackEntity attack, List<CharacterAbilityEntity> abilities, int proficiencyBonus) {
        int hitBonus = getAbilityModifier(abilities, attack.getAbility());

        if (attack.isProficient()) {
            hitBonus += proficiencyBonus;
        }

        return "+ " + hitBonus;
    }

    public String getFinalDamageRoll(CharacterAttackEntity attack, List<CharacterAbilityEntity> abilities) {
        return attack.getBaseDamageRoll() + " + " + getAbilityModifier(abilities, attack.getAbility());
    }

    private int getAbilityModifier(List<CharacterAbilityEntity> abilities, AbilityType abilityType) {
        for (CharacterAbilityEntity ability : abilities) {
            if (ability.getName().equals(abilityType)) {
               return ability.getModifier();
            }
        }
        return 0;
    }
}
