import { ChangeDetectorRef, Component, Inject, TemplateRef, ViewChild } from '@angular/core';
import { SpellDetailsPopupComponent } from '../../spells/spell-details-popup/spell-details-popup.component';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { SpellValue } from '../../../models/spell-value';
import { TableColumnValue } from '../../../models/table-column-value';
import { SpellService } from '../../../services/spell/spell.service';
import { TableService } from '../../../services/table/table.service';

@Component({
  selector: 'app-spell-list-popup',
  standalone: false,
  templateUrl: './spell-list-popup.component.html',
  styleUrl: './spell-list-popup.component.scss'
})
export class SpellListPopupComponent {

  @ViewChild('levelTemplate') levelTemplate!: TemplateRef<any>;
  @ViewChild('actionsTemplate') actionsTemplate!: TemplateRef<any>;

  spells?: SpellValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private dialogRef: MatDialogRef<SpellListPopupComponent>,
    private spellService: SpellService, 
    private dialog: MatDialog,
    private cdr: ChangeDetectorRef,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){
    this.spells = data;
  }

  close(): void {
    this.dialogRef.close();
  }

  add(spell : SpellValue) {
    this.dialogRef.close(spell);   
  }

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
        name: 'Details',
        dataKey: 'details',
        position: 'left',
        isSortable: false,
        template: this.actionsTemplate,
      },
    ];
    this.cdr.detectChanges();
  }
  
  openDetailDialog(element: any): void {
     const dialogRef = this.dialog.open(SpellDetailsPopupComponent, {
       data: element,
       width: '50vw',     
       maxWidth: '50vw',  
       maxHeight: '60vh',  
       autoFocus: false,
     });
   }
}
