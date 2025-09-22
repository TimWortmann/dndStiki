import { Component } from '@angular/core';
import { BackgroundValue } from '../../models/BackgroundValue';
import { TableColumnValue } from '../../models/TableColumnValue';
import { BackgroundService } from '../../services/background.service';
import { CompendiumService } from '../../services/compendium.service';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  backgrounds!: BackgroundValue[];

  backgroundtableColum: TableColumnValue[] = [
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
      isSortable: false 
    }
  ];


  constructor(
    private backgroundService: BackgroundService, 
    private compendiumService: CompendiumService,
  ) {}

  ngOnInit(): void {
    this.backgroundService.getAllBackgrounds().subscribe((data) => { 
      this.backgrounds = data
    }); 
  }

  getPaginationSizes() : number[] {
    let array = [];
    
    if (this.backgrounds.length > 5) {
      array.push(5);
    }
    if (this.backgrounds.length > 10) { 
      array.push(10);
    }
    if (this.backgrounds.length > 20) { 
      array.push(20);
    }

    if (this.backgrounds.length !== 5 && this.backgrounds.length !== 10 && this.backgrounds.length !== 20) {
      array.push(this.backgrounds.length);
    }

    return array;
  }

    uploadCompendium(file: File) {
      if (file) {
        file.text().then(compendiumContent => {
          this.compendiumService.uploadCompendium(compendiumContent).subscribe();
        })
      }
      
    }

}
