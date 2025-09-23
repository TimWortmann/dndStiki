import { AfterViewInit, ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { TraitPopupComponent } from '../trait-popup/trait-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { BackgroundService } from '../../services/background/background.service';
import { BackgroundValue } from '../../models/BackgroundValue';
import { TableColumnValue } from '../../models/TableColumnValue';

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
    let array = [];

    if (this.backgrounds!.length > 5) {
      array.push(5);
    }
    if (this.backgrounds!.length > 10) { 
      array.push(10);
    }
    if (this.backgrounds!.length > 20) { 
      array.push(20);
    }

    if (this.backgrounds!.length !== 5 && this.backgrounds!.length !== 10 && this.backgrounds!.length !== 20) {
      array.push(this.backgrounds!.length);
    }

    return array;
  }

  openTraitDialog(column: any, element: any): void {
    const dialogRef = this.dialog.open(TraitPopupComponent, {
      data: {
        titleInfo: element.name,
        traits: element[column.dataKey]
      },
      width: '60vw',     
      height: '60vh',     
      maxWidth: '60vw',  
      maxHeight: '60vh',  
      autoFocus: false,
    });
  }

}
