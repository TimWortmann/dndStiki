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
      {
        id: null, name: 'Strength', basicScore: 8, bonus: 0, savingThrowProficiency: 0
      },
      { id: null, name: 'Dexterity', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Constitution', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Intelligence', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Wisdom', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
      { id: null, name: 'Charisma', basicScore: 8, bonus: 0, savingThrowProficiency: 0 },
    ],
    skillProficiencies: []
  }

  allClasses! : DndClassValue[];
  classesSorted: boolean = false;
  selectedClass? : DndClassValue;
  selectedClassSkills : string[] = [];
    
  allBackgrounds! : BackgroundValue[];
  backgroundsSorted: boolean = false;
  selectedBackground? : BackgroundValue;
  
  allRaces! : RaceValue[];
  racesSorted: boolean = false;
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

  changeSavingThrowProficiency(ability: any, delta: number) {
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

  selectClass() {
    this.selectedClass = this.allClasses.find(c => c.name === this.creationValue.dndClass);
  }

  selectBackground() {
    this.selectedBackground = this.allBackgrounds.find(b => b.name === this.creationValue.background);
  }

  selectRace() {
    this.selectedRace = this.allRaces.find(r => r.name === this.creationValue.race);
    this.updateCharacterProficiencies();
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

  onSelectionChange(event: MatSelectionListChange) {
    const changedOption = event.options[0];

    if (this.selectedClassSkills.length > this.selectedClass!.numberOfSkillProficiencies) {
      // Undo last selection if limit exceeded
      changedOption.selected = false;
      this.selectedClassSkills.pop();
    }

    this.updateCharacterProficiencies();
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

  updateCharacterProficiencies() {
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
}
