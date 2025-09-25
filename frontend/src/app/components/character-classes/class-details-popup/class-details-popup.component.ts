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

}
