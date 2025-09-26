import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SpellValue } from '../../models/spell-value';
import { TableColumnValue } from '../../models/table-column-value';
import { SpellService } from '../../services/spell/spell.service';
import { TableService } from '../../services/table/table.service';
import { SpellDetailsPopupComponent } from './spell-details-popup/spell-details-popup.component';

@Component({
  selector: 'app-spells',
  standalone: false,
  templateUrl: './spells.component.html',
  styleUrl: './spells.component.scss'
})
export class SpellsComponent {

  @ViewChild('levelTemplate') levelTemplate!: TemplateRef<any>;
  @ViewChild('detailsTemplate') detailsTemplate!: TemplateRef<any>;

  data?: SpellValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private spellService: SpellService, 
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
        name: 'Level',
        dataKey: 'level',
        position: 'left',
        isSortable: true,
        template: this.levelTemplate
      },
      {
        name: 'Classes',
        dataKey: 'classes',
        position: 'left',
        isSortable: true,
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
    this.spellService.getAllSpells()
      .subscribe((response) => {
        this.data = response;
        this.cdr.detectChanges();
      });
  }

  getPaginationSizes() : number[] {
    return this.tableService.getPaginationSizes(this.data!);
  }

  openDetailDialog(element: any): void {
      const dialogRef = this.dialog.open(SpellDetailsPopupComponent, {
        data: element,
        width: '60vw',     
        height: '60vh',     
        maxWidth: '60vw',  
        maxHeight: '60vh',  
        autoFocus: false,
      });
    }

}
