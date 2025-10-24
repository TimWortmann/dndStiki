import { CharacterAbilityValue } from "./character-ability-value";
import { CharacterItemValue } from "./character-item-value";
import { CharacterSkillValue } from "./character-skill-value";
import { TraitValue } from "./trait-value";

export interface CharacterValue {
    id: number;
    name: string; 
    level: number;
    dndClass: string;
    dndSubclass: string;
    dndSubclasses: string[];
    spellcastingAbility : string;
    background: string;
    race: string;
    maxHealth: number;
    currentHealth: number;
    hitDice: string;
    maxHitDice: number;
    currentHitDice: number;
    armorClass: number;
    speed: number;
    passivePerception: number;
    proficiencyBonus: number;
    abilities: CharacterAbilityValue[];
    skills: CharacterSkillValue[];
    currencies: CharacterItemValue[];
    items: CharacterItemValue[];
    weapons: CharacterItemValue[];
    armor: CharacterItemValue[];
    shields: CharacterItemValue[];
    classFeatures: TraitValue[];
    backgroundTraits: TraitValue[];
    raceTraits: TraitValue[];
    feats: TraitValue[];
}