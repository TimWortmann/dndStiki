import { Component, Inject } from '@angular/core';
import { CharacterClassValue } from '../../../models/character-class-value';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TraitPopupComponent } from '../../trait-popup/trait-popup.component';

@Component({
  selector: 'app-class-details-popup',
  standalone: false,
  templateUrl: './class-details-popup.component.html',
  styleUrl: './class-details-popup.component.scss'
})
export class ClassDetailsPopupComponent {

  characterClass! : CharacterClassValue;

  constructor(
    public dialogRef: MatDialogRef<TraitPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.characterClass = data;
  }

  close(): void {
    this.dialogRef.close();
  }

  getFancyListString(list : string[]) : string {
    let fancyString : string = "";

    list.forEach((element: string) => {
      if (fancyString === "") {
        fancyString += element;
      }
      else {
        fancyString += " | " + element;
      }
    });

    return fancyString;
  }

  getSpellSlotsDataArray(spellSlotsString: string): number[] {
    return spellSlotsString
    .split(",")
    .filter(s => s !== "")
    .map(s => Number(s)); 
  }

  getSpellSlotsHeaderArray() : string[] {
    return ["Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7", "Level 8", "Level 9", "Level 10"];  
  }

}
