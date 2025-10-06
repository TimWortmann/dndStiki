import { AfterViewInit, ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TableColumnValue } from '../../models/table-column-value';
import { TableService } from '../../services/table/table.service';
import { DndClassValue } from '../../models/dnd-class-value';
import { DndClassService } from '../../services/dnd-class/dnd-class.service';
import { ClassDetailsPopupComponent } from './class-details-popup/class-details-popup.component';

@Component({
  selector: 'app-dnd-classes',
  standalone: false,
  templateUrl: './dnd-classes.component.html',
  styleUrl: './dnd-classes.component.scss'
})
export class DndClassesComponent implements AfterViewInit {

  @ViewChild('proficiencyTemplate') proficiencyTemplate!: TemplateRef<any>;
  @ViewChild('detailsTemplate') detailsTemplate!: TemplateRef<any>;

  data?: DndClassValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private dndClassService: DndClassService, 
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
        name: 'Hit Dice',
        dataKey: 'hitDice',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Skill Proficiencies',
        dataKey: 'numberOfSkillProficiencies',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Spell Ability',
        dataKey: 'spellAbility',
        position: 'left',
        isSortable: true
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
    this.dndClassService.getAllDndClasses().subscribe((response) => {
        this.data = response;
        this.cdr.detectChanges();
      });
  }

  getPaginationSizes() : number[] {
    return this.tableService.getPaginationSizes(this.data!);
  }

  openDetailDialog(element: any): void {
    const dialogRef = this.dialog.open(ClassDetailsPopupComponent, {
      data: element,
      width: '60vw',     
      height: '60vh',     
      maxWidth: '60vw',  
      maxHeight: '60vh',  
      autoFocus: false,
    });
  }
}
