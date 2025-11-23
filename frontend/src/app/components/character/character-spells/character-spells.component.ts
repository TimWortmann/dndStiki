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
}
