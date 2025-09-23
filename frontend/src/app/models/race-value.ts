import { TraitValue } from "./trait-value";

export interface RaceValue {
  name: string; 
  size: string;
  speed: number
  ability: string;
  spellability: string;
  traits: TraitValue[];
}