import { Component, Inject, OnInit } from '@angular/core';
import { CharacterValue } from '../../../models/character-value';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DndClassValue } from '../../../models/dnd-class-value';
import { ActivatedRoute } from '@angular/router';
import { BackgroundService } from '../../../services/background/background.service';
import { CharacterService } from '../../../services/character/character.service';
import { DndClassService } from '../../../services/dnd-class/dnd-class.service';
import { RaceService } from '../../../services/race/race.service';
import { BackgroundValue } from '../../../models/background-value';
import { RaceValue } from '../../../models/race-value';

@Component({
  selector: 'app-character-creation',
  standalone: false,
  templateUrl: './character-creation.component.html',
  styleUrl: './character-creation.component.scss'
})
export class CharacterCreationComponent implements OnInit {

  characterValue : CharacterValue = {
    id: undefined,
    name: '',
    level: 0,
    dndClass: '',
    background: '',
    race: '',
    maxHealth: 0,
    currentHealth: 0,
    hitDice: '',
    maxHitDice: 0,
    currentHitDice: 0,
    armorClass: 0,
    speed: 0,
    passivePerception: 0,
    proficiencyBonus: 0
  };

  allClasses! : DndClassValue[];
  classesSorted: boolean = false;
    
  allBackgrounds! : BackgroundValue[];
  backgroundsSorted: boolean = false;
  
  allRaces! : RaceValue[];
  racesSorted: boolean = false;

  constructor(
    public dialogRef: MatDialogRef<CharacterCreationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dndClassService: DndClassService, 
    private backgroundService: BackgroundService,
    private raceService: RaceService,
    private route: ActivatedRoute,
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
}
