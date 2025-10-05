import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FeatValue } from '../../models/feat-value';
import { TableColumnValue } from '../../models/table-column-value';
import { FeatService } from '../../services/feat/feat.service';
import { TableService } from '../../services/table/table.service';
import { ItemDetailsPopupComponent } from '../items/item-details-popup/item-details-popup.component';
import { FeatTextPopupComponent } from './feat-text-popup/feat-text-popup.component';

@Component({
  selector: 'app-feats',
  standalone: false,
  templateUrl: './feats.component.html',
  styleUrl: './feats.component.scss'
})
export class FeatsComponent {

  @ViewChild('listTemplate') listTemplate!: TemplateRef<any>;
  @ViewChild('textTemplate') textTemplate!: TemplateRef<any>;

  data?: FeatValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private featService: FeatService, 
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
        name: 'Prerequisites',
        dataKey: 'prerequisites',
        position: 'left',
        isSortable: true,
        template: this.listTemplate
      },
      {
        name: 'Description',
        dataKey: 'description',
        position: 'left',
        isSortable: false,
        template: this.textTemplate,
      },
    ];
    this.pullDataFromBackend();
  }

  pullDataFromBackend() {
    this.featService.getAllFeats()
      .subscribe((response) => {
        this.data = response;
        this.cdr.detectChanges();
      });
  }

  getPaginationSizes() : number[] {
    return this.tableService.getPaginationSizes(this.data!);
  }

  openDetailDialog(element: any): void {
    const dialogRef = this.dialog.open(FeatTextPopupComponent, {
      data: element,
      width: '60vw',     
      height: '50vh',     
      maxWidth: '60vw',  
      maxHeight: '50vh',  
      autoFocus: false,
    });
  }
  
}
