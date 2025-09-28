import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { BackgroundValue } from '../../models/background-value';
import { TableColumnValue } from '../../models/table-column-value';
import { CompendiumService } from '../../services/compendium/compendium.service';
import { BackgroundsComponent } from '../backgrounds/backgrounds.component';
import { RacesComponent } from '../races/races.component';
import { CharacterClassesComponent } from '../character-classes/character-classes.component';
import { SpellsComponent } from '../spells/spells.component';
import { ItemsComponent } from '../items/items.component';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  @ViewChild(BackgroundsComponent) backgroundsComponent!: BackgroundsComponent;
  @ViewChild(RacesComponent) racesComponent!: RacesComponent;
  @ViewChild(CharacterClassesComponent) characterClassesComponent!: CharacterClassesComponent;
  @ViewChild(SpellsComponent) spellComponent!: SpellsComponent;
  @ViewChild(ItemsComponent) itemsComponent!: ItemsComponent;

  compendiumFileName?: string;

  constructor( 
    private compendiumService: CompendiumService,
  ) {}

  ngOnInit(): void {
    this.compendiumService.getCompendiumFileName().subscribe(data => this.compendiumFileName = data);
  }

  uploadCompendium(file: File) {
    if (file) {
      this.compendiumService.uploadCompendium(file).subscribe((data) => {
        this.compendiumFileName = data
        this.repullDataFromBackend();
    });
    }   
  }

  deleteCompendium() {
    this.compendiumService.deleteCompendium().subscribe(() => {
      this.compendiumFileName = undefined
      this.repullDataFromBackend();
    })
  }

  repullDataFromBackend() {
    this.backgroundsComponent.pullDataFromBackend();
    this.racesComponent.pullDataFromBackend();
    this.characterClassesComponent.pullDataFromBackend();
    this.spellComponent.pullDataFromBackend();
    this.itemsComponent.pullDataFromBackend();
  }
}
