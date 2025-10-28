import { CharacterAbilityValue } from "./character-ability-value";
import { CharacterArmorValue } from "./character-armor-value";
import { CharacterItemValue } from "./character-item-value";
import { CharacterShieldValue } from "./character-shield-value";
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
    basicArmorClass: number;
    realArmorClass: number;
    speed: number;
    passivePerception: number;
    proficiencyBonus: number;
    abilities: CharacterAbilityValue[];
    skills: CharacterSkillValue[];
    items: CharacterItemValue[];
    equipment: CharacterItemValue[];
    classFeatures: TraitValue[];
    backgroundTraits: TraitValue[];
    raceTraits: TraitValue[];
    feats: TraitValue[];
    equippedShield: CharacterShieldValue;
    equippedArmor: CharacterArmorValue;
}