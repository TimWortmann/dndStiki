import { AfterViewInit, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { BackgroundValue } from '../../models/BackgroundValue';
import { TableColumnValue } from '../../models/TableColumnValue';
import { BackgroundService } from '../../services/background/background.service';
import { CompendiumService } from '../../services/compendium/compendium.service';
import { MatDialog } from '@angular/material/dialog';
import { TraitPopupComponent } from '../trait-popup/trait-popup.component';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, AfterViewInit {

  @ViewChild('proficiencyTemplate') proficiencyTemplate!: TemplateRef<any>;
  @ViewChild('traitTemplate') traitTemplate!: TemplateRef<any>;

  compendiumFileName?: string;
  backgrounds?: BackgroundValue[];

  backgroundtableColumns: TableColumnValue[] = [];


  constructor(
    private backgroundService: BackgroundService, 
    private compendiumService: CompendiumService,
    public dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.compendiumService.getCompendiumFileName().subscribe(data => this.compendiumFileName = data);
  }
  
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
    this.getBackgrounds();
    });
  }

  getBackgrounds() {
    this.backgroundService.getAllBackgrounds()
      .subscribe((data) => {
        this.backgrounds = data;
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

  uploadCompendium(file: File) {
    if (file) {
      this.compendiumService.uploadCompendium(file).subscribe((data) => {
        this.compendiumFileName = data
        this.getBackgrounds();
    });
    }   
  }

  deleteCompendium() {
    this.compendiumService.deleteCompendium().subscribe(() => {
      this.compendiumFileName = undefined
      this.getBackgrounds();
    })
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
