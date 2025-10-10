import { Component, Inject, OnInit } from '@angular/core';
import { CharacterValue } from '../../../models/character-value';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DndClassValue } from '../../../models/dnd-class-value';
import { ActivatedRoute, Router } from '@angular/router';
import { BackgroundService } from '../../../services/background/background.service';
import { CharacterService } from '../../../services/character/character.service';
import { DndClassService } from '../../../services/dnd-class/dnd-class.service';
import { RaceService } from '../../../services/race/race.service';
import { BackgroundValue } from '../../../models/background-value';
import { RaceValue } from '../../../models/race-value';
import { CharacterCreationValue } from '../../../models/character-creation-value';
import { subscribe } from 'diagnostics_channel';
import id from '@angular/common/locales/id';

@Component({
  selector: 'app-character-creation',
  standalone: false,
  templateUrl: './character-creation.component.html',
  styleUrl: './character-creation.component.scss'
})
export class CharacterCreationComponent implements OnInit {

  creationValue : CharacterCreationValue = {
    name: '',
    dndClass: '',
    background: '',
    race: '',
    abilities: [
    {
      id: null, ability: 'Strength', basicScore: 8, bonus: 0, savingThrowProficiency: 0
    },
    { id: null, ability: 'Dexterity', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
    { id: null, ability: 'Constitution', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
    { id: null, ability: 'Intelligence', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
    { id: null, ability: 'Wisdom', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
    { id: null, ability: 'Charisma', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },],
  }

  allClasses! : DndClassValue[];
  classesSorted: boolean = false;
    
  allBackgrounds! : BackgroundValue[];
  backgroundsSorted: boolean = false;
  
  allRaces! : RaceValue[];
  racesSorted: boolean = false;

  pointBuySum : number = 0;

  constructor(
    public dialogRef: MatDialogRef<CharacterCreationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dndClassService: DndClassService, 
    private backgroundService: BackgroundService,
    private raceService: RaceService,
    private router: Router,
    private characterService : CharacterService,
  ){}

  ngOnInit(): void {
    this.readAllClasses();
    this.readAllBackgrounds();
    this.readAllRaces();
  }

  close(): void {
    this.dialogRef.close();
  }

  create(): void {
    if (this.creationValue.name !== '' 
      && this.creationValue.dndClass !== '' 
      && this.creationValue.background !== '' 
      && this.creationValue.race !== ''
      && this.pointBuySum === 27
    ) {
      this.characterService.createCharacter(this.creationValue).subscribe(response => {
        this.dialogRef.close();
        this.router.navigate(['/character', response.id]); 
      })
      }
  }

  readAllClasses() {
    this.dndClassService.getAllDndClasses().subscribe((response) => {
      this.allClasses = response;
    })
  }

  readAllBackgrounds() {
    this.backgroundService.getAllBackgrounds().subscribe((response) => {
      this.allBackgrounds = response;
    })
  }

  readAllRaces() {
    this.raceService.getAllRaces().subscribe((response) => {
      this.allRaces = response;
    })
  }

  getPotentiallySortedData(data : any[], sorted : boolean) : DndClassValue[] {
      if (sorted) {
        return [...data].sort((a, b) => a.name.localeCompare(b.name));  
      }
  
      return data;
  }

  changeBasicScore(ability: any, delta: number) {
    
    let newScore = ability.basicScore + delta; 

    if (newScore > 7 && newScore < 16 && this.isAllowedAccordingToPointBuy(newScore, delta)) {
      ability.basicScore = newScore;
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
    }
  }

  getBonus(bonus: number) {
    return bonus > 0 ? `+${bonus}` : `${bonus}`;
  }

  getModifier(score : number) : string  {
    const result = Math.floor((score - 10) / 2);
    return result > 0 ? `+${result}` : `${result}`;
  }
}
