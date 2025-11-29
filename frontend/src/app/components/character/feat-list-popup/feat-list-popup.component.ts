import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FeatValue } from '../../../models/feat-value';
import { TableColumnValue } from '../../../models/table-column-value';
import { FeatService } from '../../../services/feat/feat.service';
import { FeatTextPopupComponent } from '../../feats/feat-text-popup/feat-text-popup.component';

@Component({
  selector: 'app-feat-list-popup',
  standalone: false,
  templateUrl: './feat-list-popup.component.html',
  styleUrl: './feat-list-popup.component.scss'
})
export class FeatListPopupComponent {

  @ViewChild('listTemplate') listTemplate!: TemplateRef<any>;
  @ViewChild('actionsTemplate') actionsTemplate!: TemplateRef<any>;

  data?: FeatValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private dialogRef: MatDialogRef<FeatListPopupComponent>,
    private featService: FeatService, 
    private dialog: MatDialog,
    private cdr: ChangeDetectorRef,
  ){}

  close(): void {
    this.dialogRef.close();
  }

  add(feat : FeatValue) {
    this.dialogRef.close(feat);   
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
        name: 'Prerequisites',
        dataKey: 'prerequisites',
        position: 'left',
        isSortable: true,
        template: this.listTemplate
      },
      {
        name: 'Actions',
        dataKey: 'actions',
        position: 'left',
        isSortable: false,
        template: this.actionsTemplate,
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
  
  openDetailDialog(element: any): void {
     const dialogRef = this.dialog.open(FeatTextPopupComponent, {
       data: element,
       width: '50vw',     
       maxWidth: '50vw',  
       maxHeight: '60vh',  
       autoFocus: false,
     });
   }
}
