import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CharacterAbilityValue } from '../../../models/character-ability-value';

@Component({
  selector: 'app-point-buy',
  standalone: false,
  templateUrl: './point-buy.component.html',
  styleUrl: './point-buy.component.scss'
})
export class PointBuyComponent {

  @Input() abilities: CharacterAbilityValue[] = [];
  @Input() pointBuySum: number = 0;

  @Output() abilitiesChange = new EventEmitter<CharacterAbilityValue[]>();
  @Output() pointBuySumChange = new EventEmitter<number>();

  changeBasicScore(ability: any, delta: number) {
    
    let newScore = ability.basicScore + delta; 

    if (newScore > 7 && newScore < 16 && this.isAllowedAccordingToPointBuy(newScore, delta)) {
      ability.basicScore = newScore;
      this.emitChanges();
    }
  }

  isAllowedAccordingToPointBuy(newScore: any, delta: number) : boolean {
    if (delta > 0) {
      if (newScore < 14 && this.pointBuySum < 27) {
        this.pointBuySum += 1;
        return true;
      }
  
      if (newScore >= 14 && this.pointBuySum < 26) {
        this.pointBuySum += 2;
        return true;  
      }
  
      return false;
    }
    else {
      if (newScore < 13) {
        this.pointBuySum -= 1;  
      }
      else {
        this.pointBuySum -= 2;   
      }
      return true;
    }
  }

  changeBonus(ability: any, delta: number) {
    if (ability.bonus + delta > -1) {
      ability.bonus += delta;
      this.emitChanges();
    }
  }

  getBonus(bonus: number) {
    return bonus > 0 ? `+${bonus}` : `${bonus}`;
  }

  getModifier(score : number) : string  {
    const result = Math.floor((score - 10) / 2);
    return result > 0 ? `+${result}` : `${result}`;
  }

  private emitChanges() {
    this.abilitiesChange.emit(this.abilities);
    this.pointBuySumChange.emit(this.pointBuySum);
  }
}
