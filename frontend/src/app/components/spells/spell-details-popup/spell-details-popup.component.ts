import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SpellValue } from '../../../models/spell-value';

@Component({
  selector: 'app-spell-details-popup',
  standalone: false,
  templateUrl: './spell-details-popup.component.html',
  styleUrl: './spell-details-popup.component.scss'
})
export class SpellDetailsPopupComponent {

  spell! : SpellValue;

  constructor(
    public dialogRef: MatDialogRef<SpellDetailsPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.spell = data;
  }

  close(): void {
    this.dialogRef.close();
  }

}
