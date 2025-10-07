import { Component, OnInit } from '@angular/core';
import { DndClassService } from '../../../services/dnd-class/dnd-class.service';
import { response } from 'express';
import { DndClassValue } from '../../../models/dnd-class-value';
import { BackgroundService } from '../../../services/background/background.service';
import { BackgroundValue } from '../../../models/background-value';
import { RaceService } from '../../../services/race/race.service';
import { RaceValue } from '../../../models/race-value';

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

  characterName : string = "Togil";
  characterNameChangeActive : boolean = false;
  
  
  selectedClass: string = 'Old Class';
  allClasses! : DndClassValue[];
  classesSorted: boolean = false;
  classChangeActive : boolean = false;
  
  selectedBackground: string = 'Old Background';
  allBackgrounds! : BackgroundValue[];
  backgroundsSorted: boolean = false;
  backgroundChangeActive : boolean = false;
  
  selectedRace: string = 'Old Race/Species'
  allRaces! : RaceValue[];
  racesSorted: boolean = false;
  raceChangeActive : boolean = false;

  constructor(
    private dndClassService: DndClassService, 
    private backgroundService: BackgroundService,
    private raceService: RaceService,
  ){}

  ngOnInit(): void {
    this.readAllClasses();
    this.readAllBackgrounds();
    this.readAllRaces();
  }

  changeNameState() {
    if (this.characterName !== "") {
      this.characterNameChangeActive = ! this.characterNameChangeActive;
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

  changeClassState() {
    this.classChangeActive = !this.classChangeActive;
  }

  changeBackgroundState() {
    this.backgroundChangeActive = !this.backgroundChangeActive;
  }

  changeRaceState() {
    this.raceChangeActive = !this.raceChangeActive;
  }
}
