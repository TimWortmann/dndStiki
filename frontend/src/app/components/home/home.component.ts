import { Component, NgZone } from '@angular/core';
import { BackgroundValue } from '../../models/BackgroundValue';
import { TableColumnValue } from '../../models/TableColumnValue';
import { BackgroundService } from '../../services/background.service';

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


  constructor(private backgroundService: BackgroundService, private ngZone: NgZone) {}

  ngOnInit(): void {
    this.backgroundService.getAllBackgrounds().subscribe((data) => {
      this.ngZone.run(() => {
        console.log("Subscribe")
        console.log("Data", data)
        this.backgrounds = data
        console.log("Backgrounds", this.backgrounds)
      });
    }); 
  }

}
