import { CharacterAbilityValue } from "./character-ability-value";

export interface CharacterCreationValue {
    name: string; 
    dndClass: string;
    background: string;
    race: string;
    abilities: CharacterAbilityValue[];
}