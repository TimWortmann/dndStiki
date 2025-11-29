import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CharacterValue } from '../../../models/character-value';
import { SpellValue } from '../../../models/spell-value';
import { SpellDetailsPopupComponent } from '../../spells/spell-details-popup/spell-details-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { CharacterSpellSlotsValue } from '../../../models/character-spell-slots-value';

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

  getCurrentSpellSlots() : number[] {
    if (!this.characterValue.spellSlots) {
      return [];
    }

    for (let spellSlotsElement of this.characterValue.spellSlots) {
      if (spellSlotsElement.level === this.characterValue.level) {
        return spellSlotsElement.spellSlots;
      }
    }
    return [];
  }

  hasSpellSlotForSpell(spell : SpellValue) : boolean {
    const currentSpellSlots = this.getCurrentSpellSlots();

    if (currentSpellSlots.length > spell.level && currentSpellSlots[spell.level] !== 0) {
      return true;
    } 

    return false;
  }
  
  getSpellsWithoutSpellSlot() : SpellValue[] {
    let spellsWithoutSpellSlot = [];

    if (!this.characterValue.spells) {
      return [];
    }

    for (let spell of this.characterValue.spells) {
      if (!this.hasSpellSlotForSpell(spell)) {
        spellsWithoutSpellSlot.push(spell);
      }
    }
    return spellsWithoutSpellSlot;
  }
}
