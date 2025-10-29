import { Injectable } from '@angular/core';
import { CharacterValue } from '../../models/character-value';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CharacterCreationValue } from '../../models/character-creation-value';
import { CharacterAbilityValue } from '../../models/character-ability-value';
import { CharacterSkillValue } from '../../models/character-skill-value';
import { FeatValue } from '../../models/feat-value';
import { ItemValue } from '../../models/item-value';
import { CharacterItemValue } from '../../models/character-item-value';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {
  
  private baseUrl = 'http://localhost:8080/api/character'; 

  constructor(private http: HttpClient) {}

  getAllCharacters() : Observable<CharacterValue[]> {
    return this.http.get<CharacterValue[]>(this.baseUrl);
  }

  getCharacterById(id : number) : Observable<CharacterValue> {
    return this.http.get<CharacterValue>(this.baseUrl + "/" + id);
  }

  createCharacter(creationValue : CharacterCreationValue) : Observable<CharacterValue> {
    return this.http.post<CharacterValue>(this.baseUrl, creationValue);
  }

  deleteCharacter(id : number) : Observable<CharacterValue> {
    return this.http.delete<CharacterValue>(this.baseUrl + "/" + id);
  }

  changeName(id : number, name : string) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/name/" + id + "/" + encodeURIComponent(name), undefined)
  }

  changeLevel(id : number, level : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/level/" + id + "/" + level, undefined)
  }

  changeDndClass(id : number, dndClass : string) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/class/" + id, dndClass)
  }

  changeSubclass(id : number, subclass : string) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/subclass/" + id, subclass)
  }

  changeBackground(id : number, background : string) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/background/" + id, background)
  }

  changeRace(id : number, race : string) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/race/" + id, race)
  }

  changeCurrentHealth(id : number, currentHealth : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/currentHealth/" + id + "/" + currentHealth, undefined)
  }

  changeMaxHealth(id : number, maxHealth : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/maxHealth/" + id + "/" + maxHealth, undefined)
  }

  changeCurrentHitDice(id : number, currentHitDice : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/currentHitDice/" + id + "/" + currentHitDice, undefined)
  }

  changeMaxHitDice(id : number, maxHitDice : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/maxHitDice/" + id + "/" + maxHitDice, undefined)
  }

  changeAbilities(id : number, abilities : CharacterAbilityValue[]) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/ability/" + id, abilities)
  }

  changeSkillProficiencies(id : number, skills : CharacterSkillValue[]) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/skill/" + id, skills)
  }

  addFeat(id : number, feat : FeatValue) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/feat/" + id, feat)
  }

  removeFeat(id : number, featName : string) : Observable<CharacterValue> {
    return this.http.delete<CharacterValue>(this.baseUrl + "/feat/" + id + "/" + encodeURIComponent(featName))
  }

  addItem(id : number, item : ItemValue) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/item/" + id, item)
  }

  changeItemQuantity(characterId : number, itemName : string, quantity : number) {
    return this.http.put<CharacterValue>(this.baseUrl + "/item/" + characterId + "/" + encodeURIComponent(itemName) + "/" + quantity, undefined)  
  }

  equipShield(id : number, shieldItem : CharacterItemValue) {
    return this.http.put<CharacterValue>(this.baseUrl + "/shield/" + id, shieldItem);
  }

  unequipShield(id : number) {
    return this.http.delete<CharacterValue>(this.baseUrl + "/shield/" + id);
  }

  equipArmor(id : number, armorItem : CharacterItemValue) {
    return this.http.put<CharacterValue>(this.baseUrl + "/armor/" + id, armorItem);
  }

  unequipArmor(id : number) {
    return this.http.delete<CharacterValue>(this.baseUrl + "/armor/" + id);
  }

  changeArmorClass(id : number, armorClass : number) {
    return this.http.put<CharacterValue>(this.baseUrl + "/armorClass/" + id + "/" + armorClass, undefined);
  }

  resetArmorClass(id : number) {
    return this.http.put<CharacterValue>(this.baseUrl + "/armorClass/" + id, undefined);
  }

  equipWeapon(id : number, weaponItem : CharacterItemValue) {
    return this.http.put<CharacterValue>(this.baseUrl + "/weapon/" + id, weaponItem);
  }
}
