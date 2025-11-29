import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CharacterValue } from '../../../models/character-value';
import { SpellValue } from '../../../models/spell-value';
import { SpellDetailsPopupComponent } from '../../spells/spell-details-popup/spell-details-popup.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-character-spells',
  standalone: false,
  templateUrl: './character-spells.component.html',
  styleUrl: './character-spells.component.scss'
})
export class CharacterSpellsComponent {
  
  @Input() characterValue! : CharacterValue;
  @Output() removeSpellEvent = new EventEmitter<SpellValue>();

  constructor(
    private dialog: MatDialog,
  ){}

  isSpellWithoutSpellslot(level : number, spellSlots : number[]) : boolean {
    return level >= spellSlots.length;
  }

  hasSpellsWithoutSpellslots(spellSlots : number[]) : boolean {
    if (this.characterValue.spells) {
      for (let spell of this.characterValue.spells) {
        if (this.isSpellWithoutSpellslot(spell.level, spellSlots)) {
          return true;
        }
      }
    }
    return false;
  }

  countSpells(level : number) : number {
    if (!this.characterValue.spells) {
      return 0;
    }

    let counter = 0;
    for (let spell of this.characterValue.spells) {
      if (spell.level === level) {
        counter++;
      }
    }
    return counter;
  }

  countSpellsWithoutSpellslot(spellSlots: number[]) : number {
    let counter = 0;
    for (let spell of this.characterValue.spells) {
      if (this.isSpellWithoutSpellslot(spell.level, spellSlots)) {
        counter++;
      }  
    }
    return counter;
  }

  removeSpell(spell: SpellValue) {
    this.removeSpellEvent.emit(spell);
  }

  openDetailDialog(element: any): void {
      const dialogRef = this.dialog.open(SpellDetailsPopupComponent, {
        data: element,
        width: '60vw',          
        maxWidth: '60vw',  
        maxHeight: '60vh',  
        autoFocus: false,
      });
    }

}
