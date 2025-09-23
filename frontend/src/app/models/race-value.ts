import { TraitValue } from "./trait-value";

export interface RaceValue {
  name: string; 
  size: string;
  speed: number
  traits: TraitValue[];
}