import { Component, OnInit } from '@angular/core';
import { DndClassService } from '../../../services/dnd-class/dnd-class.service';
import { response } from 'express';
import { DndClassValue } from '../../../models/dnd-class-value';
import { BackgroundService } from '../../../services/background/background.service';
import { BackgroundValue } from '../../../models/background-value';
import { RaceService } from '../../../services/race/race.service';
import { RaceValue } from '../../../models/race-value';
import { CharacterValue } from '../../../models/character-value';
import { ActivatedRoute } from '@angular/router';
import { CharacterService } from '../../../services/character/character.service';

@Component({
  selector: 'app-character-overview',
  standalone: false,
  templateUrl: './character-overview.component.html',
  styleUrl: './character-overview.component.scss'
})
export class CharacterOverviewComponent implements OnInit {
onClassSelected(arg0: any) {
throw new Error('Method not implemented.');
}

  characterValue! : CharacterValue;
  characterNameChangeActive : boolean = false;  

  allClasses! : DndClassValue[];
  classesSorted: boolean = false;
  classChangeActive : boolean = false;
  
  allBackgrounds! : BackgroundValue[];
  backgroundsSorted: boolean = false;
  backgroundChangeActive : boolean = false;

  allRaces! : RaceValue[];
  racesSorted: boolean = false;
  raceChangeActive : boolean = false;

  constructor(
    private dndClassService: DndClassService, 
    private backgroundService: BackgroundService,
    private raceService: RaceService,
    private route: ActivatedRoute,
    private characterService : CharacterService,
  ){}

  ngOnInit(): void {
    this.readCharacter();
    this.readAllClasses();
    this.readAllBackgrounds();
    this.readAllRaces();
  }

  changeNameState() {
    if (this.characterValue.name !== "") {
      this.characterNameChangeActive = ! this.characterNameChangeActive;
    }
  }

  readCharacter() {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (!idParam) {
      throw new Error('No ID provided in route');        
    }

    const id: number = +idParam; // safe, guaranteed to be a number now

    this.characterService.getCharacterById(id).subscribe((response) => {
      this.characterValue = response;  
    });
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

  changeClassState() {
    this.classChangeActive = !this.classChangeActive;
  }

  changeBackgroundState() {
    this.backgroundChangeActive = !this.backgroundChangeActive;
  }

  changeRaceState() {
    this.raceChangeActive = !this.raceChangeActive;
  }

  increaseLevel() {
    if (this.characterValue.level < 20) {
      this.characterValue.level++;
    }
  }

  reduceLevel() {
    if (this.characterValue.level > 1) {
      this.characterValue.level--;
    }
  }

  getProcentualHealth() : number {
    return (this.characterValue.currentHealth / this.characterValue.maxHealth) * 100;
  }

  getProcentualHitDice() : number {
    return (this.characterValue.currentHitDice / this.characterValue.maxHitDice) * 100;
  }

  getModifier(score : number) : string  {
    const result = Math.floor((score - 10) / 2);
    return result > 0 ? `+${result}` : `${result}`;
  }

  isFeatureVisible(featureName : string) : boolean {
    const match = featureName.match(/Level (\d+)/);  

    if (match) {
      const levelNumber = parseInt(match[1], 10);
      if (levelNumber > this.characterValue.level) {
          return false;
      }
    }
    
    if (this.characterValue.level < 3 && featureName.toLowerCase().includes("subclass")) {
      return false;
    }

    return true;
  }
}
