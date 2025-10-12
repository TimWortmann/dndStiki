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
import { MatSelectionListChange } from '@angular/material/list';

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
      { id: null, name: 'Strength', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Dexterity', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Constitution', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Intelligence', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Wisdom', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Charisma', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
    ],
    skillProficiencies: []
  }

  allClasses! : DndClassValue[];
  selectedClass? : DndClassValue;
  classFilter: string = '';
  selectedClassSkills : string[] = [];

  allBackgrounds! : BackgroundValue[];
  backgroundFilter: string = '';
  selectedBackground? : BackgroundValue;
  
  allRaces! : RaceValue[];
  raceFilter: string = '';
  selectedRace? : RaceValue; 

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
    if (this.isEveryCreationInformationFilled()) {
      this.characterService.createCharacter(this.creationValue).subscribe(response => {
        this.dialogRef.close();
        this.router.navigate(['/character', response.id]); 
      })
      }
  }

  isEveryCreationInformationFilled() : boolean {
    return this.creationValue.name !== '' 
      && this.creationValue.dndClass !== '' 
      && this.classSkillsAreChoosen()
      && this.creationValue.background !== '' 
      && this.creationValue.race !== ''
      && this.pointBuySum === 27
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

  changeSavingThrowProficiency(ability: any, delta: number) {
    if (ability.bonus + delta > -1) {
      ability.bonus += delta;
    }
  }

  selectClass() {
    this.selectedClass = this.allClasses.find(c => c.name === this.creationValue.dndClass);
    this.setSavingThrowProficiencies();
  }

  selectBackground() {
    this.selectedBackground = this.allBackgrounds.find(b => b.name === this.creationValue.background);
    this.setCharacterSkillProficiencies();
  }

  selectRace() {
    this.selectedRace = this.allRaces.find(r => r.name === this.creationValue.race);
  }

  getFancyListString(list : string[] | undefined) : string {
    let fancyString : string = "";

    list?.forEach((element: string) => {
      if (fancyString === "") {
        fancyString += element;
      }
      else {
        fancyString += " | " + element;
      }
    });

    return fancyString;
  }

  onSkillSelectionChange(event: MatSelectionListChange) {
    const changedOption = event.options[0];

    if (this.selectedClassSkills.length > this.selectedClass!.numberOfSkillProficiencies) {
      // Undo last selection if limit exceeded
      changedOption.selected = false;
      this.selectedClassSkills.pop();
    }

    this.setCharacterSkillProficiencies();
  }

  // Return only visible skills based on current selection
  getVisibleSkills(): string[] {
    if (!this.selectedClass) return [];

    const max = this.selectedClass.numberOfSkillProficiencies;

    if (this.selectedClassSkills.length >= max) {
      // Show only already selected skills
      return this.selectedClassSkills;
    } else {
      // Show all skills
      return this.selectedClass.skillProficiencies;
    }
  }

  setSavingThrowProficiencies() {
    this.creationValue.abilities.forEach(ability => {
      if (this.selectedClass?.savingThrowProficiencies.includes(ability.name)) {
        ability.savingThrowProficiency = 1;
      } else {
        ability.savingThrowProficiency = 0; 
      }
    });
  }

  setCharacterSkillProficiencies() {
    this.creationValue.skillProficiencies = Array.from(
      new Set([
        ...this.selectedClassSkills,
        ...(this.selectedBackground?.proficiencies ?? [])
      ])
    );
  }

  classSkillsAreChoosen() : boolean {
    return this.selectedClassSkills.length === this.selectedClass?.numberOfSkillProficiencies;
  }

  
  getFilteredClasses(): DndClassValue[] {
    const list = this.allClasses;
    if (!this.classFilter) return list;
    const filterValue = this.classFilter.toLowerCase();
    return list.filter(c => c.name.toLowerCase().includes(filterValue));
  }

  getFilteredBackgrounds(): BackgroundValue[] {
    const list = this.allBackgrounds;
    if (!this.backgroundFilter) return list;
    const filterValue = this.backgroundFilter.toLowerCase();
    return list.filter(b => b.name.toLowerCase().includes(filterValue));
  }

  getFilteredRaces(): RaceValue[] {
    const list = this.allRaces;
    if (!this.raceFilter) return list;
    const filterValue = this.raceFilter.toLowerCase();
    return list.filter(r => r.name.toLowerCase().includes(filterValue));
  }
}
