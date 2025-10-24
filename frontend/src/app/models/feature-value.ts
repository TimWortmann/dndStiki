import { TraitValue } from "./trait-value";

export interface FeatureValue extends TraitValue {
  optional: boolean;
}