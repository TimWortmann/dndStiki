import { CharacterAbilityValue } from "./character-ability-value";

export interface CharacterValue {
    id: number;
    name: string; 
    level: number;
    dndClass: string;
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
}