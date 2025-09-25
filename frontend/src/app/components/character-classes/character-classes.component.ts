import { AfterViewInit, ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BackgroundValue } from '../../models/background-value';
import { TableColumnValue } from '../../models/table-column-value';
import { BackgroundService } from '../../services/background/background.service';
import { TableService } from '../../services/table/table.service';
import { CharacterClassValue } from '../../models/character-class-value';
import { CharacterClassesService } from '../../services/character-classes/character-classes.service';
import { ClassDetailsPopupComponent } from './class-details-popup/class-details-popup.component';

@Component({
  selector: 'app-character-classes',
  standalone: false,
  templateUrl: './character-classes.component.html',
  styleUrl: './character-classes.component.scss'
})
export class CharacterClassesComponent implements AfterViewInit {

  @ViewChild('proficiencyTemplate') proficiencyTemplate!: TemplateRef<any>;
  @ViewChild('detailTemplate') detailTemplate!: TemplateRef<any>;

  data?: CharacterClassValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private characterClassService: CharacterClassesService, 
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
        template: this.detailTemplate,
      },
    ];
    this.pullDataFromBackend();
  }

  pullDataFromBackend() {
    this.characterClassService.getAllCharacterClasses().subscribe((response) => {
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
