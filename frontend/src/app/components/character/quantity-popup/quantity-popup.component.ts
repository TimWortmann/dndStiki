import { Component, Inject } from '@angular/core';
import { CharacterItemValue } from '../../../models/character-item-value';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { it } from 'node:test';

@Component({
  selector: 'app-quantity-popup',
  standalone: false,
  templateUrl: './quantity-popup.component.html',
  styleUrl: './quantity-popup.component.scss'
})
export class QuantityPopupComponent {

  item! : CharacterItemValue;

  constructor(
    public dialogRef: MatDialogRef<QuantityPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){
    this.item = { ...data };
  
  }

  close(): void {
    this.dialogRef.close();
  }

  isSavingDisabled() : boolean {
    return isNaN(this.item.quantity) || this.item.quantity < 0;
  }

  save(): void {
    if (!this.isSavingDisabled()) {
      this.dialogRef.close(this.item);
    }
  }

}
