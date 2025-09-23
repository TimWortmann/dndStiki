import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TraitValue } from '../../models/TraitValue';

@Component({
  selector: 'app-trait-popup',
  standalone: false,
  templateUrl: './trait-popup.component.html',
  styleUrl: './trait-popup.component.scss'
})
export class TraitPopupComponent {
  
  titleInfo! : string;
  traits : TraitValue[] = [];

  constructor(
    public dialogRef: MatDialogRef<TraitPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.titleInfo = data.titleInfo;
    this.traits = data.traits;
  }

  close(): void {
    this.dialogRef.close();
  }

}
