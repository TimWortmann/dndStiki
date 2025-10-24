import { AfterViewInit, ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ItemValue } from '../../../models/item-value';
import { TableColumnValue } from '../../../models/table-column-value';

import { ItemDetailsPopupComponent } from '../../items/item-details-popup/item-details-popup.component';


@Component({
  selector: 'app-item-list',
  standalone: false,
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.scss'
})
export class ItemListComponent implements AfterViewInit {
  
  @ViewChild('propertiesTemplate') propertiesTemplate!: TemplateRef<any>;
  @ViewChild('quantityTemplate') quantityTemplate!: TemplateRef<any>;
  @ViewChild('detailsTemplate') detailsTemplate!: TemplateRef<any>;
  @ViewChild('equippedTemplate') equippedTemplate!: TemplateRef<any>;
  @ViewChild('addTemplate') addTemplate!: TemplateRef<any>;

  @Input() data!: ItemValue[];

  @Input() isFilterableOnlyVisible: boolean = false;;

  @Input() typeColumn: boolean = false;
  @Input() propertiesColumn: boolean = false;
  @Input() quantityColumn: boolean = false;
  @Input() equippedColumn: boolean = false;
  @Input() addColumn: boolean = false;

  @Output() addItemEvent = new EventEmitter<ItemValue>();

  tableColumns: TableColumnValue[] = [];

  constructor( 
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
    ];

    if (this.typeColumn) {
      this.tableColumns.push(
        {
          name: 'Type',
          dataKey: 'type',
          position: 'left',
          isSortable: true,
        })   
    }

    if (this.propertiesColumn) {
      this.tableColumns.push(
        {
          name: 'Properties',
          dataKey: 'properties',
          position: 'left',
          isSortable: true,
          template: this.propertiesTemplate
        })    
    }

    if (this.quantityColumn) {
      this.tableColumns.push(
        {
          name: 'Quantity',
          dataKey: 'quantity',
          position: 'left',
          isSortable: true,
          template: this.quantityTemplate,
        })  
    }

    this.tableColumns.push(
      {
        name: 'Details',
        dataKey: 'details',
        position: 'left',
        isSortable: false,
        template: this.detailsTemplate,
      }
    )

    if (this.equippedColumn) {
      this.tableColumns.push(
        {
          name: 'Equipped',
          dataKey: 'equipped',
          position: 'left',
          isSortable: true,
          template: this.equippedTemplate,
        })
    }
    
    if (this.addColumn) {
      this.tableColumns.push(
        {
          name: 'Actions',
          dataKey: 'actions',
          position: 'left',
          isSortable: true,
          template: this.addTemplate,
        })
    }

    this.cdr.detectChanges();
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

  addItem(item : ItemValue) {
    this.addItemEvent.emit(item);
  }
}
