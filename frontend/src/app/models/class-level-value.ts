import { CounterValue } from "./counter-value";
import { FeatureValue } from "./feature-value";

export interface ClassLevelValue {
  id: number;
  level: number;
  scoreImprovement: boolean;
  spellSlots: String;
  features: FeatureValue[];
  counters: CounterValue[];
}