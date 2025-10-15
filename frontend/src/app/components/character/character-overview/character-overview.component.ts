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
import { MatDialog } from '@angular/material/dialog';
import { PointBuyPopupComponent } from '../point-buy-popup/point-buy-popup.component';

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
  classChangeActive : boolean = false;
  classFilter: string = '';

  allBackgrounds! : BackgroundValue[];
  backgroundChangeActive : boolean = false;
  backgroundFilter: string = '';
  
  allRaces! : RaceValue[];
  raceChangeActive : boolean = false;
  raceFilter: string = '';  

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
    private dialog: MatDialog,
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
      this.setCharacter(response);
    });
  }

  setCharacter(character : CharacterValue) {
    this.characterValue = character;  
    this.characterValue.skills = this.sortSkills();  
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

  changeClassState() {
    if (this.classChangeActive) {
      this.characterService.changeDndClass(this.characterValue.id, this.characterValue.dndClass).subscribe(response => {
        this.setCharacter(response);  
        this.classChangeActive = !this.classChangeActive;
      });
    }
    else {
      this.classChangeActive = !this.classChangeActive;
    }
  }

  changeBackgroundState() {
    if (this.backgroundChangeActive) {
      this.characterService.changeBackground(this.characterValue.id, this.characterValue.background).subscribe(response => {
        this.setCharacter(response);  
        this.backgroundChangeActive = !this.backgroundChangeActive;
      });
    }
    else {
      this.backgroundChangeActive = !this.backgroundChangeActive;
    }
  }

  changeRaceState() {
    if (this.raceChangeActive) {
      this.characterService.changeRace(this.characterValue.id, this.characterValue.race).subscribe(response => {
        this.setCharacter(response);  
        this.raceChangeActive = !this.raceChangeActive;
      });
    }
    else {
      this.raceChangeActive = !this.raceChangeActive;
    }
  }

  changeLevel(delta : number) {
    if (delta === 1 && this.characterValue.level < 20
      || delta === -1 && this.characterValue.level > 1
    ) {
      this.characterService.changeLevel(this.characterValue.id, this.characterValue.level + delta).subscribe(response => {
        this.setCharacter(response);  
      });
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

  isFeatureVisible(featureName: string): boolean {
    const levelMatch = featureName.match(/Level (\d+)/);
    if (levelMatch) {
      const levelNumber = parseInt(levelMatch[1], 10);
      if (levelNumber > this.characterValue.level) {
        return false;
      }
    }

    // Hide subclass-related features before level 3
    if (this.characterValue.level < 3 && this.featuresIncludesSubclassPrefix(featureName)) {
      return false;
    }

    // Only filter by subclass if one is selected and level >= 3
    if (this.characterValue.level >= 3 && this.characterValue.dndSubclass && this.characterValue.dndSubclass !== "No Subclass") {
      for (const subclass of this.characterValue.dndSubclasses) {
        if (featureName.includes(subclass) && subclass !== this.characterValue.dndSubclass) {
          // Feature belongs to a different subclass → hide it
          return false;
        }
      }
    }

    return true; // visible by default
  }

  featuresIncludesSubclassPrefix(featureName : string) : boolean {
    return featureName.toLowerCase().includes("subclass") 
      || featureName.toLowerCase().includes("archetype") 
      || featureName.toLowerCase().includes("college") 
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
      this.characterService.changeCurrentHealth(this.characterValue.id, this.characterValue.currentHealth).subscribe((response) => {
        this.setCharacter(response);
      });
    }
    else {
      this.characterService.changeMaxHealth(this.characterValue.id, this.characterValue.maxHealth).subscribe((response) => {
        this.setCharacter(response);
      });
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
      else if (newHealth < 0) {
        this.characterValue.currentHealth = 0;
      }
      else if (newHealth > this.characterValue.maxHealth) {
        this.characterValue.currentHealth = this.characterValue.maxHealth;
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
      else {
        this.characterValue.maxHealth = 1;
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
      this.characterService.changeCurrentHitDice(this.characterValue.id, this.characterValue.currentHitDice).subscribe((response) => {
        this.setCharacter(response);
      });
    }
    else {
      this.characterService.changeMaxHitDice(this.characterValue.id, this.characterValue.maxHitDice).subscribe((response) => {
        this.setCharacter(response);
      });
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

  openPointBuyDialog() {
    const dialogRef = this.dialog.open(PointBuyPopupComponent, {
          data: this.characterValue.abilities,
          width: '60vw',     
          height: '46vh',     
          maxWidth: '60vw',  
          maxHeight: '46vh',  
          autoFocus: false,
    });

    dialogRef.afterClosed().subscribe((result: CharacterAbilityValue[] | undefined) => {
      if (result) {
        this.characterValue.abilities = result;
      }
    });
  }

  changeSubclass() {
    this.characterService.changeSubclass(this.characterValue.id, this.characterValue.dndSubclass).subscribe((response) => {
        this.setCharacter(response);
      });
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
