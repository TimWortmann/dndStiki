import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FeatValue } from '../../../models/feat-value';

@Component({
  selector: 'app-feat-text-popup',
  standalone: false,
  templateUrl: './feat-text-popup.component.html',
  styleUrl: './feat-text-popup.component.scss'
})
export class FeatTextPopupComponent {

  feat! : FeatValue;

  constructor(
    public dialogRef: MatDialogRef<FeatTextPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.feat = data;
  }

  close(): void {
    this.dialogRef.close();
  }

}
