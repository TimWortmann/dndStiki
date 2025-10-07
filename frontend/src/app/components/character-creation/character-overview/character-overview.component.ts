import { Component, OnInit } from '@angular/core';
import { DndClassService } from '../../../services/dnd-class/dnd-class.service';
import { response } from 'express';
import { DndClassValue } from '../../../models/dnd-class-value';
import { BackgroundService } from '../../../services/background/background.service';
import { BackgroundValue } from '../../../models/background-value';

@Component({
  selector: 'app-character-overview',
  standalone: false,
  templateUrl: './character-overview.component.html',
  styleUrl: './character-overview.component.scss'
})
export class CharacterOverviewComponent implements OnInit {

  characterName : string = "Togil";
  characterNameChangeIsActive : boolean = false;
  
  selectedClass: string = 'Class';
  selectedBackground: string = 'Background';

  allClasses! : DndClassValue[];
  allBackgrounds! : BackgroundValue[];

  constructor(
    private dndClassService: DndClassService, 
    private backgroundService: BackgroundService,
  ){}

  ngOnInit(): void {
    this.getAllClasses();
    this.getAllBackgrounds();
  }

  changeNameState() {
    if (this.characterName !== "") {
      this.characterNameChangeIsActive = ! this.characterNameChangeIsActive;
    }
  }

  getAllClasses() {
    this.dndClassService.getAllDndClasses().subscribe((response) => {
      this.allClasses = response;
    })
  }

 showDefaultClass(): boolean {
    if (!this.allClasses) return true; // still loading
    return !this.allClasses.some(c => c.name === this.selectedClass);
  }

  getAllBackgrounds() {
    this.backgroundService.getAllBackgrounds().subscribe((response) => {
      this.allBackgrounds = response;
    })
  }
}
