import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ItemValue } from '../../../models/item-value';

@Component({
  selector: 'app-item-details-popup',
  standalone: false,
  templateUrl: './item-details-popup.component.html',
  styleUrl: './item-details-popup.component.scss'
})
export class ItemDetailsPopupComponent {

  item! : ItemValue;

  constructor(
    public dialogRef: MatDialogRef<ItemDetailsPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.item = data;
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

  isListThere(list :any[] | undefined) {
    return list !== undefined && list.length > 0;
  }

  getFancyBoolean(rawBoolean : boolean) : string {
    if (rawBoolean === true) {
      return "Yes";
    }

    return "No";
  }

  hasBasicInfos() : boolean {
    if (this.item.detail
    || this.item.weight
    || this.item.value
    || this.isListThere(this.item.properties)
    || this.item.dmg1
    || this.item.dmg2
    || this.item.dmgType
    || this.item.range
    || this.item.ac
    || this.item.stealth
    || this.item.magic
    || this.item.strength) {
      return true;
    }

    return false;
  }
}
