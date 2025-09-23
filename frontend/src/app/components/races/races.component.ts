import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TableColumnValue } from '../../models/table-column-value';
import { TableService } from '../../services/table/table.service';
import { RaceService } from '../../services/race/race.service';
import { RaceValue } from '../../models/race-value';

@Component({
  selector: 'app-races',
  standalone: false,
  templateUrl: './races.component.html',
  styleUrl: './races.component.scss'
})
export class RacesComponent {

  @ViewChild('traitTemplate') traitTemplate!: TemplateRef<any>;

  data?: RaceValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private raceService: RaceService, 
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
        isSortable: false
      },
      {
        name: 'Size',
        dataKey: 'size',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Speed',
        dataKey: 'speed',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Ability',
        dataKey: 'ability',
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
        name: 'Traits',
        dataKey: 'traits',
        position: 'left',
        isSortable: false,
        template: this.traitTemplate
      },
    ];
    this.pullDataFromBackend();
  }

  pullDataFromBackend() {
    this.raceService.getAllRaces()
      .subscribe((response) => {
        this.data = response;
        this.cdr.detectChanges();
      });
  }

  getPaginationSizes() : number[] {
    return this.tableService.getPaginationSizes(this.data!);
  }

  openTraitDialog(column: any, element: any): void {
    this.tableService.openTraitDialog(column, element);
  }
}
