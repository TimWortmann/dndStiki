import { ClassLevelValue } from "./class-level-value";

export interface CharacterClassValue {
  name: string;
  hitDice: number;
  savingThrowProficiencies: string[];
  skillProficiencies: string[];
  weaponProficiencies: string[];
  armorProficiencies: string[];
  toolProficiencies: string[];
  wealth: string;
  spellAbility: string;
  classLevels: ClassLevelValue[];
}