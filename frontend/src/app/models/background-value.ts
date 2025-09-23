import { TraitValue } from "./trait-value";

export interface BackgroundValue {
  name: string; 
  proficiencies: string[]; 
  traits: TraitValue[];
}