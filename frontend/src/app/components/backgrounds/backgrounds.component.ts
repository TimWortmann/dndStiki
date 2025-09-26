import { AfterViewInit, ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { TraitPopupComponent } from '../trait-popup/trait-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { BackgroundService } from '../../services/background/background.service';
import { BackgroundValue } from '../../models/background-value';
import { TableColumnValue } from '../../models/table-column-value';
import { TableService } from '../../services/table/table.service';

@Component({
  selector: 'app-backgrounds',
  standalone: false,
  templateUrl: './backgrounds.component.html',
  styleUrl: './backgrounds.component.scss'
})
export class BackgroundsComponent implements AfterViewInit {

  @ViewChild('proficiencyTemplate') proficiencyTemplate!: TemplateRef<any>;
  @ViewChild('traitTemplate') traitTemplate!: TemplateRef<any>;

  data?: BackgroundValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private backgroundService: BackgroundService, 
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
        name: 'Skill Proficiencies',
        dataKey: 'proficiencies',
        position: 'left',
        isSortable: true,
        template: this.proficiencyTemplate
      },
      {
        name: 'Traits',
        dataKey: 'traits',
        position: 'left',
        isSortable: true,
        template: this.traitTemplate
      },
    ];
    this.pullDataFromBackend();
  }

  pullDataFromBackend() {
    this.backgroundService.getAllBackgrounds()
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
