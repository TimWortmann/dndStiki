import { AfterViewInit, ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { TraitPopupComponent } from '../trait-popup/trait-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { BackgroundService } from '../../services/background/background.service';
import { BackgroundValue } from '../../models/BackgroundValue';
import { TableColumnValue } from '../../models/TableColumnValue';
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

  backgrounds?: BackgroundValue[];
  backgroundtableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private backgroundService: BackgroundService, 
    public dialog: MatDialog,
    private cdr: ChangeDetectorRef,
  ){}

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.backgroundtableColumns  = [
      {
        name: 'Name',
        dataKey: 'name',
        position: 'left',
        isSortable: false
      },
      {
        name: 'Proficiencies',
        dataKey: 'proficiencies',
        position: 'left',
        isSortable: false,
        template: this.proficiencyTemplate
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
    });
  }

  pullDataFromBackend() {
    this.backgroundService.getAllBackgrounds()
      .subscribe((data) => {
        this.backgrounds = data;
        this.cdr.detectChanges();
      });
  }

  getPaginationSizes() : number[] {
    return this.tableService.getPaginationSizes(this.backgrounds!);
  }

  openTraitDialog(column: any, element: any): void {
    this.tableService.openTraitDialog;
  }

}
