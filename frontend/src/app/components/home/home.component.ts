import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { BackgroundValue } from '../../models/BackgroundValue';
import { TableColumnValue } from '../../models/TableColumnValue';
import { CompendiumService } from '../../services/compendium/compendium.service';
import { BackgroundsComponent } from '../backgrounds/backgrounds.component';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  @ViewChild(BackgroundsComponent) backgroundsComponent!: BackgroundsComponent;

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
        this.backgroundsComponent.pullDataFromBackend();
    });
    }   
  }

  deleteCompendium() {
    this.compendiumService.deleteCompendium().subscribe(() => {
      this.compendiumFileName = undefined
      this.backgroundsComponent.pullDataFromBackend();
    })
  }
}
