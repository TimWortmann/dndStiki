import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { TableComponent } from './components/table/table.component';
import { HomeComponent } from './components/home/home.component';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { SingleFileUploadComponent } from './components/single-file-upload/single-file-upload.component';

// Angular Material
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { loadingInterceptor } from './services/loading/LoadingInterceptor';
import { TraitPopupComponent } from './components/trait-popup/trait-popup.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { BackgroundsComponent } from './components/backgrounds/backgrounds.component';
import { RacesComponent } from './components/races/races.component';
import { CharacterClassesComponent } from './components/character-classes/character-classes.component';
import { ClassDetailsPopupComponent } from './components/character-classes/class-details-popup/class-details-popup.component';
import { SpellsComponent } from './components/spells/spells.component';
import { SpellDetailsPopupComponent } from './components/spells/spell-details-popup/spell-details-popup.component';
import { ItemsComponent } from './components/items/items.component';
import { ItemDetailsPopupComponent } from './components/items/item-details-popup/item-details-popup.component';


@NgModule({
  declarations: [
    App,
    TableComponent,
    HomeComponent,
    SingleFileUploadComponent,
    TraitPopupComponent,
    BackgroundsComponent,
    RacesComponent,
    CharacterClassesComponent,
    ClassDetailsPopupComponent,
    SpellsComponent,
    SpellDetailsPopupComponent,
    ItemsComponent,
    ItemDetailsPopupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatExpansionModule,
],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideClientHydration(withEventReplay()),
    provideHttpClient(withFetch(), withInterceptors([loadingInterceptor])),
  ],
  bootstrap: [App]
})
export class AppModule { }
