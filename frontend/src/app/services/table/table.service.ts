import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TraitPopupComponent } from '../../components/trait-popup/trait-popup.component';

@Injectable({
  providedIn: 'root'
})
export class TableService {

  constructor(
    public dialog: MatDialog,
  ){}
  
  getPaginationSizes(data:any[]) : number[] {
    let array = [];

    if (data.length > 5) {
      array.push(5);
    }
    if (data.length > 10) { 
      array.push(10);
    }
    if (data.length > 20) { 
      array.push(20);
    }

    if (data.length !== 5 && data.length !== 10 && data.length !== 20) {
      array.push(data.length);
    }

    return array;
  }

  openTraitDialog(column: any, element: any): void {
    const dialogRef = this.dialog.open(TraitPopupComponent, {
      data: {
        titleInfo: element.name,
        traits: element[column.dataKey]
      },
      width: '60vw',     
      height: '60vh',     
      maxWidth: '60vw',  
      maxHeight: '60vh',  
      autoFocus: false,
    });
  }
  
}
