import { Component, Inject } from '@angular/core';
import { CharacterAbilityValue } from '../../../models/character-ability-value';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CharacterService } from '../../../services/character/character.service';

@Component({
  selector: 'app-point-buy-popup',
  standalone: false,
  templateUrl: './point-buy-popup.component.html',
  styleUrl: './point-buy-popup.component.scss'
})
export class PointBuyPopupComponent {

  abilities! : CharacterAbilityValue[];
  pointBuySum : number = 27;

  constructor(
    public dialogRef: MatDialogRef<PointBuyPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){
    this.abilities = data.map((a: any) => ({ ...a }));
  }

  close(): void {
    this.dialogRef.close();
  }

  isSavingDisabled() : boolean {
    return this.pointBuySum !== 27;
  }

  save(): void {
    this.dialogRef.close(this.abilities);
  }
}
