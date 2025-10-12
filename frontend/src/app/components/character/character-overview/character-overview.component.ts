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
import { CharacterAbilityValue } from '../../../models/character-ability-value';
import { CharacterSkillValue } from '../../../models/character-skill-value';

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

  currentHealthChangeActive : boolean = false;
  maxHealthChangeActive : boolean = false;

  currentHitDiceChangeActive : boolean = false;
  maxHitDiceChangeActive : boolean = false;

  skillChangeActive : boolean = false;

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
      this.characterValue.skills = this.sortSkills();
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

  getModifier(ability : CharacterAbilityValue) : string  {
    const modifier = Math.floor((ability.basicScore + ability.bonus - 10) / 2);
    return modifier > 0 ? `+${modifier}` : `${modifier}`;
  }

  getSavingThrowModifier(ability : CharacterAbilityValue) {
    const modifier = Math.floor((ability.basicScore + ability.bonus - 10) / 2);
    const savingThrowModifier = modifier + (ability.savingThrowProficiency * this.characterValue.proficiencyBonus)

    return savingThrowModifier > 0 ? `+${savingThrowModifier}` : `${savingThrowModifier}`;
  } 

  getSkillModifier(skill : CharacterSkillValue) {
    const savingThrowModifier = skill.basicModifier + (skill.proficiency * this.characterValue.proficiencyBonus)

    return savingThrowModifier > 0 ? `+${savingThrowModifier}` : `${savingThrowModifier}`;
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

  sortSkills(): CharacterSkillValue[] {
    return this.characterValue.skills.sort((a, b) => a.name.localeCompare(b.name));
  }

  addProficiency(skill : CharacterSkillValue) {
    if (skill.proficiency < 2) {
      skill.proficiency++;
    }
  }

  removeProficiency(skill : CharacterSkillValue) {
    if (skill.proficiency > 0) {
      skill.proficiency--;
    }
  }

  changeSkillEditorState() {
    this.skillChangeActive = ! this.skillChangeActive;
  }

  isHealthChangeActive() {
    return this.currentHealthChangeActive || this.maxHealthChangeActive;
  }

  activateCurrentHealthChange() {
    this.maxHealthChangeActive = false;
    this.currentHealthChangeActive = true;
  }

  activateMaxHealthChange() {
    this.currentHealthChangeActive = false;
    this.maxHealthChangeActive = true;
  }

  discardHealthChange() {
    this.readCharacter();
    
    this.currentHealthChangeActive = false;
    this.maxHealthChangeActive = false;
  }

  saveHealthChange() {
    if (this.currentHealthChangeActive) {

    }
    else {

    }
    this.currentHealthChangeActive = false;
    this.maxHealthChangeActive = false;
  }

  changeHealth(delta : number) {
    if (this.currentHealthChangeActive) {
      let newHealth = this.characterValue.currentHealth + delta;
      if (newHealth >= 0 && newHealth <= this.characterValue.maxHealth) {
        this.characterValue.currentHealth = newHealth;
      }
    }
    else {
      let newHealth = this.characterValue.maxHealth + delta;
      if (newHealth > 0) {
        this.characterValue.maxHealth = newHealth;
        
        if (this.characterValue.currentHealth > newHealth) {
          this.characterValue.currentHealth = newHealth;
        }
      }
    }  
  }

  isHitDiceChangeActive() {
    return this.currentHitDiceChangeActive || this.maxHitDiceChangeActive;
  }

  activateCurrentHitDiceChange() {
    this.maxHitDiceChangeActive = false;
    this.currentHitDiceChangeActive = true;
  }

  activateMaxHitDiceChange() {
    this.currentHitDiceChangeActive = false;
    this.maxHitDiceChangeActive = true;
  }

  discardHitDiceChange() {
    this.readCharacter();
    
    this.currentHitDiceChangeActive = false;
    this.maxHitDiceChangeActive = false;
  }

  saveHitDiceChange() {
    if (this.currentHitDiceChangeActive) {

    }
    else {

    }
    this.currentHitDiceChangeActive = false;
    this.maxHitDiceChangeActive = false;
  }

  changeHitDice(delta : number) {
    if (this.currentHitDiceChangeActive) {
      let newHitDice = this.characterValue.currentHitDice + delta;
      if (newHitDice >= 0 && newHitDice <= this.characterValue.maxHitDice) {
        this.characterValue.currentHitDice = newHitDice;
      }
    }
    else {
      let newHitDice = this.characterValue.maxHitDice + delta;
      if (newHitDice > 0) {
        this.characterValue.maxHitDice = newHitDice;
        
        if (this.characterValue.currentHitDice > newHitDice) {
          this.characterValue.currentHitDice = newHitDice;
        }
      }
    }  
  }
}
