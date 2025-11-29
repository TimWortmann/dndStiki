import { Component, Input, OnInit } from '@angular/core';
import { CharacterValue } from '../../../models/character-value';

@Component({
  selector: 'app-character-spells',
  standalone: false,
  templateUrl: './character-spells.component.html',
  styleUrl: './character-spells.component.scss'
})
export class CharacterSpellsComponent {
  
  @Input() characterValue! : CharacterValue;


  isSpellWithoutSpellslot(level : number, spellSlots : number[]) : boolean {

    console.log("Spell level", level);   

    return level >= spellSlots.length;
  }

  hasSpellsWithoutSpellslots(spellSlots : number[]) : boolean {
    for (let spell of this.characterValue.spells) {
      if (this.isSpellWithoutSpellslot(spell.level, spellSlots)) {
        return true;
      }
    }
    return false;
  }
}
