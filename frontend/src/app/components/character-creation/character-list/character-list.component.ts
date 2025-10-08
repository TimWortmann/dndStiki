import { ChangeDetectorRef, Component, TemplateRef, ViewChild } from '@angular/core';
import { CharacterValue } from '../../../models/character-value';
import { CharacterService } from '../../../services/character/character.service';
import { TableColumnValue } from '../../../models/table-column-value';
import { TableService } from '../../../services/table/table.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-character-list',
  standalone: false,
  templateUrl: './character-list.component.html',
  styleUrl: './character-list.component.scss'
})
export class CharacterListComponent {

  @ViewChild('detailsTemplate') detailsTemplate!: TemplateRef<any>;

  data?: CharacterValue[];
  tableColumns: TableColumnValue[] = [];

  constructor(
    private tableService: TableService,
    private characterService: CharacterService, 
    private cdr: ChangeDetectorRef,
    private router: Router,
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
        name: 'Class',
        dataKey: 'dndClass',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Level',
        dataKey: 'level',
        position: 'left',
        isSortable: true
      },
      {
        name: 'Details',
        dataKey: 'details',
        position: 'left',
        isSortable: true,
        template: this.detailsTemplate
      },
    ];
    this.pullDataFromBackend();
  }

  pullDataFromBackend() {
    this.characterService.getAllCharacters()
      .subscribe((response) => {
        this.data = response;
        this.cdr.detectChanges();
      });
  }

  openCharacterDetails(id : number) {
    this.router.navigate(['/character', id]);  
  }

  openCharacterCreationDialog() {

  }
}
