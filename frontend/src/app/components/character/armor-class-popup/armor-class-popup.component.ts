import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-armor-class-popup',
  standalone: false,
  templateUrl: './armor-class-popup.component.html',
  styleUrl: './armor-class-popup.component.scss'
})
export class ArmorClassPopupComponent {

  armorClass! : number;

  constructor(
    public dialogRef: MatDialogRef<ArmorClassPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){
    this.armorClass = data;
  
  }

  close(): void {
    this.dialogRef.close();
  }

  isSavingDisabled() : boolean {
    return this.armorClass < 1;
  }

  save(): void {
    if (!this.isSavingDisabled()) {
      this.dialogRef.close(this.armorClass);
    }
  }

  changeArmorClass(delta : number) {
    this.armorClass += delta;
  }

}
