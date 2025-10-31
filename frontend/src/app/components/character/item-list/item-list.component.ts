import { AfterViewInit, ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ItemValue } from '../../../models/item-value';
import { TableColumnValue } from '../../../models/table-column-value';

import { ItemDetailsPopupComponent } from '../../items/item-details-popup/item-details-popup.component';
import { CharacterItemValue } from '../../../models/character-item-value';
import { it } from 'node:test';
import { QuantityPopupComponent } from '../quantity-popup/quantity-popup.component';
import { CharacterShieldValue } from '../../../models/character-shield-value';
import { CharacterArmorValue } from '../../../models/character-armor-value';
import { CharacterAttackValue } from '../../../models/character-attack-value';


@Component({
  selector: 'app-item-list',
  standalone: false,
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.scss'
})
export class ItemListComponent implements AfterViewInit {
  
  @ViewChild('propertiesTemplate') propertiesTemplate!: TemplateRef<any>;
  @ViewChild('quantityTemplate') quantityTemplate!: TemplateRef<any>;
  @ViewChild('bigQuantityTemplate') bigQuantityTemplate!: TemplateRef<any>;
  @ViewChild('detailsTemplate') detailsTemplate!: TemplateRef<any>;
  @ViewChild('equippedTemplate') equippedTemplate!: TemplateRef<any>;
  @ViewChild('addTemplate') addTemplate!: TemplateRef<any>;

  @Input() data!: ItemValue[] | CharacterItemValue[];
  @Input() equippedShield? : CharacterShieldValue;
  @Input() equippedArmor? : CharacterArmorValue;
  @Input() equippedWeapons? : CharacterAttackValue[];

  @Input() isFilterableOnlyVisible: boolean = false;;

  @Input() typeColumn: boolean = false;
  @Input() propertiesColumn: boolean = false;
  @Input() quantityColumn: boolean = false;
  @Input() bigQuantityColumn: boolean = false;
  @Input() equippedColumn: boolean = false;
  @Input() addColumn: boolean = false;

  @Output() addItemEvent = new EventEmitter<ItemValue>();
  @Output() changeItemQuantityEvent = new EventEmitter<CharacterItemValue>();
  @Output() equipItemEvent = new EventEmitter<CharacterItemValue>();
  @Output() unequipItemEvent = new EventEmitter<CharacterItemValue>();

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

    if (this.bigQuantityColumn) {
      this.tableColumns.push(
        {
          name: 'Quantity',
          dataKey: 'quantity',
          position: 'left',
          isSortable: true,
          template: this.bigQuantityTemplate,
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
          name: 'Equip',
          dataKey: 'equip',
          position: 'left',
          isSortable: false,
          template: this.equippedTemplate,
        })
    }
    
    if (this.addColumn) {
      this.tableColumns.push(
        {
          name: 'Actions',
          dataKey: 'actions',
          position: 'left',
          isSortable: false,
          template: this.addTemplate,
        })
    }

    this.cdr.detectChanges();
  }

  openDetailDialog(element: any): void {
    this.dialog.open(ItemDetailsPopupComponent, {
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

  changeItemQuantity(item : CharacterItemValue, delta : number) {
    item.quantity += delta;
    this.changeItemQuantityEvent.emit(item);
  }

  openQuantityDialog(item : CharacterItemValue): void {
    const dialogRef = this.dialog.open(QuantityPopupComponent, {
      data: item, 
      minWidth: '750px',    
      height: '225px',     
      maxWidth: '750px',  
      maxHeight: '225px',   
      autoFocus: true,
    });

    dialogRef.afterClosed().subscribe((result: CharacterItemValue | undefined) => {
      if (result) {
        item = result;
        this.changeItemQuantityEvent.emit(item); 
      }
    });
  }

  equipItem(item: CharacterItemValue) {
    this.equipItemEvent.emit(item);
  }

  unequipItem(item: CharacterItemValue) {
    this.unequipItemEvent.emit(item);
  }

  isEquipped(name : string) : boolean {

    if (this.equippedWeapons) {
      for (const weapon of this.equippedWeapons) {
        if (name === weapon.name 
          || name + " (Two Handed)" == weapon.name 
          || name + " (Finesse)" === weapon.name) {
          return true;
        }
      }
    }

    return name === this.equippedShield?.name || name === this.equippedArmor?.name;
  }
}
