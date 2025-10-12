import { ClassLevelValue } from "./class-level-value";
import { TraitValue } from "./trait-value";

export interface DndClassValue {
  name: string;
  subclasses: string[];
  hitDice: number;
  savingThrowProficiencies: string[];
  skillProficiencies: string[];
  numberOfSkillProficiencies: number;
  weaponProficiencies: string[];
  armorProficiencies: string[];
  toolProficiencies: string[];
  wealth: string;
  spellAbility: string;
  classLevels: ClassLevelValue[];
  traits: TraitValue[];
}