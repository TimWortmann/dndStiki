import { ChangeDetectorRef, Component, Inject, TemplateRef, ViewChild } from '@angular/core';
import { ItemDetailsPopupComponent } from '../../items/item-details-popup/item-details-popup.component';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ItemValue } from '../../../models/item-value';
import { TableColumnValue } from '../../../models/table-column-value';
import { ItemService } from '../../../services/item/item.service';

@Component({
  selector: 'app-item-list-popup',
  standalone: false,
  templateUrl: './item-list-popup.component.html',
  styleUrl: './item-list-popup.component.scss'
})
export class ItemListPopupComponent {
  constructor(
    private dialogRef: MatDialogRef<ItemListPopupComponent>,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){}

  close(): void {
    this.dialogRef.close();
  }

}
