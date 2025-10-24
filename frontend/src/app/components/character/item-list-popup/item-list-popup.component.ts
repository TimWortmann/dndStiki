import { AfterViewInit, ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { ItemDetailsPopupComponent } from '../../items/item-details-popup/item-details-popup.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ItemValue } from '../../../models/item-value';
import { TableColumnValue } from '../../../models/table-column-value';
import { ItemService } from '../../../services/item/item.service';

@Component({
  selector: 'app-item-list-popup',
  standalone: false,
  templateUrl: './item-list-popup.component.html',
  styleUrl: './item-list-popup.component.scss'
})
export class ItemListPopupComponent implements AfterViewInit {

  @ViewChild('propertiesTemplate') propertiesTemplate!: TemplateRef<any>;
  @ViewChild('actionsTemplate') actionsTemplate!: TemplateRef<any>;

  data?: ItemValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private dialogRef: MatDialogRef<ItemListPopupComponent>,
    private itemService: ItemService, 
    public dialog: MatDialog,
    private cdr: ChangeDetectorRef,
  ){}

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.setTableInformations();  
    });
  }

  setTableInformations() {
    this.tableColumns  = [
      {
        name: 'Name',
        dataKey: 'name',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Type',
        dataKey: 'type',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Properties',
        dataKey: 'properties',
        position: 'left',
        isSortable: true,
        template: this.propertiesTemplate
      },
      {
        name: 'Details',
        dataKey: 'details',
        position: 'left',
        isSortable: false,
        template: this.actionsTemplate,
      },
    ];
    this.pullDataFromBackend();
  }

  pullDataFromBackend() {
    this.itemService.getAllItems()
      .subscribe((response) => {
        this.data = response;
        this.cdr.detectChanges();
      });
  }

  openDetailDialog(element: any): void {
    const dialogRef = this.dialog.open(ItemDetailsPopupComponent, {
      data: element,
      width: '60vw',          
      maxWidth: '60vw',  
      maxHeight: '60vh',  
      autoFocus: false,
    });
  }

  close(): void {
    this.dialogRef.close();
  }
    
  add(item : ItemValue) {
    this.dialogRef.close(item);   
  }

}
