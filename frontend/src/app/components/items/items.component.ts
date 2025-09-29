import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ItemValue } from '../../models/item-value';
import { TableColumnValue } from '../../models/table-column-value';
import { ItemService } from '../../services/item/item.service';
import { TableService } from '../../services/table/table.service';
import { SpellDetailsPopupComponent } from '../spells/spell-details-popup/spell-details-popup.component';
import { ItemDetailsPopupComponent } from './item-details-popup/item-details-popup.component';

@Component({
  selector: 'app-items',
  standalone: false,
  templateUrl: './items.component.html',
  styleUrl: './items.component.scss'
})
export class ItemsComponent {

  @ViewChild('propertiesTemplate') propertiesTemplate!: TemplateRef<any>;
  @ViewChild('detailsTemplate') detailsTemplate!: TemplateRef<any>;

  data?: ItemValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
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
        template: this.detailsTemplate,
      },
    ];
    this.pullDataFromBackend();
  }

  pullDataFromBackend() {
    this.itemService.getAllSpells()
      .subscribe((response) => {
        this.data = response;
        this.cdr.detectChanges();
      });
  }

  getPaginationSizes() : number[] {
    return this.tableService.getPaginationSizes(this.data!);
  }

  openDetailDialog(element: any): void {
    const dialogRef = this.dialog.open(ItemDetailsPopupComponent, {
      data: element,
      width: '60vw',     
      height: '60vh',     
      maxWidth: '60vw',  
      maxHeight: '60vh',  
      autoFocus: false,
    });
  }

}
