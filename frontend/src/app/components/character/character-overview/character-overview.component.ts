import { ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { DndClassService } from '../../../services/dnd-class/dnd-class.service';
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
import { FeatListPopupComponent } from '../feat-list-popup/feat-list-popup.component';
import { FeatValue } from '../../../models/feat-value';
import { TraitValue } from '../../../models/trait-value';
import { DownloadsPopupComponent } from '../downloads-popup/downloads-popup.component';
import { ItemListPopupComponent } from '../item-list-popup/item-list-popup.component';
import { ItemValue } from '../../../models/item-value';
import { ItemDetailsPopupComponent } from '../../items/item-details-popup/item-details-popup.component';
import { ItemService } from '../../../services/item/item.service';
import { CharacterItemValue } from '../../../models/character-item-value';
import { ArmorClassPopupComponent } from '../armor-class-popup/armor-class-popup.component';

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

  allClasses? : DndClassValue[];
  classChangeActive : boolean = false;
  classFilter: string = '';
  currentClass? : DndClassValue;

  allBackgrounds? : BackgroundValue[];
  backgroundChangeActive : boolean = false;
  backgroundFilter: string = '';
  currentBackground? : BackgroundValue;
  
  allRaces? : RaceValue[];
  raceChangeActive : boolean = false;
  raceFilter: string = '';  

  currentHealthChangeActive : boolean = false;
  maxHealthChangeActive : boolean = false;

  currentHitDiceChangeActive : boolean = false;
  maxHitDiceChangeActive : boolean = false;

  skillChangeActive : boolean = false;

  allItems? : ItemValue[];

  constructor(
    private dndClassService: DndClassService, 
    private backgroundService: BackgroundService,
    private raceService: RaceService,
    private itemService: ItemService,
    private route: ActivatedRoute,
    private characterService : CharacterService,
    private dialog: MatDialog,
    private cdr: ChangeDetectorRef,
  ){}

  ngOnInit(): void {
    this.readCharacter();
  }

  changeNameState() {
    if (this.characterValue.name !== "") {
      if (this.characterNameChangeActive) {
        this.characterService.changeName(this.characterValue.id, this.characterValue.name).subscribe((response) => {
          this.setCharacter(response)
          this.characterNameChangeActive = false;
        })
      } 
      else {
        this.characterNameChangeActive = true;
      }
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

  readCurrentClass() {
    this.dndClassService.getDndClass(this.characterValue.dndClass).subscribe((response) => {
      this.currentClass = response;
    }) 
  }

  readCurrentBackground() {
    this.backgroundService.getBackground(this.characterValue.background).subscribe((response) => {
      this.currentBackground = response;
    })   
  }

  setCharacter(character : CharacterValue) {
    this.characterValue = character;  
    this.characterValue.skills = this.sortSkills();  
    this.readCurrentClass();
    this.readCurrentBackground();
    this.cdr.detectChanges();
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
      if (!this.allClasses) this.readAllClasses();
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
      if (!this.allBackgrounds) this.readAllBackgrounds();
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
      if (!this.allRaces) this.readAllRaces();
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
    if (this.skillChangeActive) {
      this.characterService.changeSkillProficiencies(this.characterValue.id, this.characterValue.skills).subscribe((response) => {
        this.setCharacter(response);
        this.skillChangeActive = false;   
      })
    } else {
      this.skillChangeActive = true;
    }
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
          width: '800px',     
          height: '360px',     
          maxWidth: '800px',  
          maxHeight: '360px',
          autoFocus: false,
    });

    dialogRef.afterClosed().subscribe((result: CharacterAbilityValue[] | undefined) => {
      if (result) {
        this.characterService.changeAbilities(this.characterValue.id, result).subscribe((response) => {
          this.setCharacter(response);  
        })
      }
    });
  }

  changeSubclass() {
    this.characterService.changeSubclass(this.characterValue.id, this.characterValue.dndSubclass).subscribe((response) => {
        this.setCharacter(response);
      });
  }

  getAllClassNames(): string[] {
    if (!this.allClasses) return [this.characterValue.dndClass];
      
    let result = this.allClasses.map(c => c.name);

    if (!result.includes(this.characterValue.dndClass)) {
      result = [this.characterValue.dndClass, ...result];
    }

    return result;
  }

  getFilteredClasses(): string[] {
    const list = this.getAllClassNames();
    if (!this.classFilter) return list;

    const filterValue = this.classFilter.toLowerCase();
    return list.filter(name => name.toLowerCase().includes(filterValue));
  }

  getAllBackgroundNames(): string[] {
  if (!this.allBackgrounds) return [this.characterValue.background];

  let result = this.allBackgrounds.map(b => b.name);

  if (!result.includes(this.characterValue.background)) {
    result = [this.characterValue.background, ...result];
  }

  return result;
}

  getFilteredBackgrounds(): string[] {
    const list = this.getAllBackgroundNames();
    if (!this.backgroundFilter) return list;

    const filterValue = this.backgroundFilter.toLowerCase();
    return list.filter(name => name.toLowerCase().includes(filterValue));
  }

  getAllRaceNames(): string[] {
    if (!this.allRaces) return [this.characterValue.race];

    let result = this.allRaces.map(r => r.name);

    if (!result.includes(this.characterValue.race)) {
      result = [this.characterValue.race, ...result];
    }

    return result;
  }

  getFilteredRaces(): string[] {
    const list = this.getAllRaceNames();
    if (!this.raceFilter) return list;

    const filterValue = this.raceFilter.toLowerCase();
    return list.filter(name => name.toLowerCase().includes(filterValue));
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

  openFeatDialog() {
    const dialogRef = this.dialog.open(FeatListPopupComponent, {
          width: '1300px',        
          maxWidth: '1300px',  
          maxHeight: '650px',  
          autoFocus: true,
    });

    dialogRef.afterClosed().subscribe((result: FeatValue | undefined) => {
      if (result) {
        this.characterService.addFeat(this.characterValue.id, result).subscribe((response) => {
          this.setCharacter(response)
        })  
      }
    });
  }

  removeFeat(feat : TraitValue) {
    this.characterService.removeFeat(this.characterValue.id, feat.name).subscribe((response) => {
      this.setCharacter(response)
    }) 
  }

  openDownloadsDialog() {
    this.dialog.open(DownloadsPopupComponent, {
      data: this.characterValue,    
      width: '500px',     
      height: '200px',     
      maxWidth: '500px',  
      maxHeight: '200px',  
      autoFocus: false,
    });
  }

  addItem() {
    if (this.allItems) {
      this.openItemListDialog();
    } else {
      this.itemService.getAllItems().subscribe((response) => {
        this.allItems = response;
        this.openItemListDialog();
      })
    }
  }

  openItemListDialog() {
    const dialogRef = this.dialog.open(ItemListPopupComponent, {
      data: this.allItems,
      width: '1300px',        
      maxWidth: '1300px',  
      maxHeight: '650px',  
      autoFocus: true,
    });

    dialogRef.afterClosed().subscribe((result: ItemValue | undefined) => {
      if (result) {
        this.characterService.addItem(this.characterValue.id, result).subscribe((response) => {
          this.setCharacter(response)
        })  
      }
    });
  }

  changeItemQuantity(item : CharacterItemValue) {
    this.characterService.changeItemQuantity(this.characterValue.id, item.name, item.quantity).subscribe((response) => {
      this.setCharacter(response);
    })
  }

  openItemDetailDialog(element: any): void {
    const dialogRef = this.dialog.open(ItemDetailsPopupComponent, {
      data: element,
      width: '60vw',          
      maxWidth: '60vw',  
      maxHeight: '60vh',  
      autoFocus: false,
    });
  }

  equipItem(item: CharacterItemValue) {
    if ("Shield" === item.type) { 
      this.characterService.equipShield(this.characterValue.id, item).subscribe((response) => {
        this.setCharacter(response);
      })
    }
    
    if (item.type.toLowerCase().includes("armor")) {
      this.characterService.equipArmor(this.characterValue.id, item).subscribe((response) => {
        this.setCharacter(response);
      })
    }
  }

  unequipItem(item: CharacterItemValue) {
    if ("Shield" === item.type) { 
      this.characterService.unequipShield(this.characterValue.id).subscribe((response) => {
        this.setCharacter(response);
      })
    }
    
    if (item.type.toLowerCase().includes("armor")) {
      this.characterService.unequipArmor(this.characterValue.id).subscribe((response) => {
        this.setCharacter(response);
      })  
    }
  }

  openArmorClassDialog(): void {
    const dialogRef = this.dialog.open(ArmorClassPopupComponent, {
      data: this.characterValue.realArmorClass, 
      minWidth: '350px',    
      height: '150px',     
      maxWidth: '350px',  
      maxHeight: '150px',   
      autoFocus: true,
    });
  
    dialogRef.afterClosed().subscribe((result: number | undefined) => {
      if (result) {
        this.characterService.changeArmorClass(this.characterValue.id, result).subscribe((response) => {
          this.setCharacter(response);
        })  
      }
    });
  }

  resetArmorClass() {
    this.characterService.resetArmorClass(this.characterValue.id).subscribe((response) => {
      this.setCharacter(response);
    })  
  }
}
