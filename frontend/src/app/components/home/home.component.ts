import { Component } from '@angular/core';
import { TableColumnValue } from '../../models/TableColumnValue';
import { BackgroundValue } from '../../models/BackgroundValue';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  backgrounds: BackgroundValue[] = [
    {
      name: "Acolythe",
      proficiencies: ["Insight", "Religion"],
      traits: []
    }, 
    {
      name: "Acolythe 2",
      proficiencies: ["Insight", "Religion"],
      traits: []
    }
  ]

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
}
