import { Component, Inject } from '@angular/core';
import { DndClassValue } from '../../../models/dnd-class-value';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TraitPopupComponent } from '../../trait-popup/trait-popup.component';
import { ClassLevelValue } from '../../../models/class-level-value';

@Component({
  selector: 'app-class-details-popup',
  standalone: false,
  templateUrl: './class-details-popup.component.html',
  styleUrl: './class-details-popup.component.scss'
})
export class ClassDetailsPopupComponent {

  dndClass! : DndClassValue;
  selectedSubclass : string = "No Subclass"; 

  constructor(
    public dialogRef: MatDialogRef<ClassDetailsPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.dndClass = data;
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

  getSpellSlotsHeaderArray() : string[] {
    return ["Known Cantrips", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7", "Level 8", "Level 9"];  
  }

  isFeatureVisible(featureName: string): boolean {
    // Only filter by subclass if one is selected 
    if (this.selectedSubclass && this.selectedSubclass !== "No Subclass") {
      for (const subclass of this.dndClass.subclasses) {
        if (featureName.includes(subclass) && subclass !== this.selectedSubclass) {
          // Feature belongs to a different subclass → hide it
          return false;
        }
      }
    }

    return true; // visible by default
  }

}
