import { LOCALE_ID, NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
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
import {MatSelectModule} from '@angular/material/select';
import {MatTabsModule} from '@angular/material/tabs';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';


import { loadingInterceptor } from './services/loading/LoadingInterceptor';
import { TraitPopupComponent } from './components/trait-popup/trait-popup.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { BackgroundsComponent } from './components/backgrounds/backgrounds.component';
import { RacesComponent } from './components/races/races.component';
import { SpellsComponent } from './components/spells/spells.component';
import { SpellDetailsPopupComponent } from './components/spells/spell-details-popup/spell-details-popup.component';
import { ItemsComponent } from './components/items/items.component';
import { ItemDetailsPopupComponent } from './components/items/item-details-popup/item-details-popup.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { FormsModule } from '@angular/forms';
import { FeatsComponent } from './components/feats/feats.component';
import { FeatTextPopupComponent } from './components/feats/feat-text-popup/feat-text-popup.component';
import { DndClassesComponent } from './components/dnd-classes/dnd-classes.component';
import { ClassDetailsPopupComponent } from './components/dnd-classes/class-details-popup/class-details-popup.component';
import { CharacterOverviewComponent } from './components/character/character-overview/character-overview.component';
import { CharacterListComponent } from './components/character/character-list/character-list.component';
import { CharacterCreationComponent } from './components/character/character-creation/character-creation.component';
import { MatListModule } from '@angular/material/list';
import { PointBuyComponent } from './components/character/point-buy/point-buy.component';
import { PointBuyPopupComponent } from './components/character/point-buy-popup/point-buy-popup.component';
import { FeatListPopupComponent } from './components/character/feat-list-popup/feat-list-popup.component';
import { DownloadsPopupComponent } from './components/character/downloads-popup/downloads-popup.component';
import { ItemListPopupComponent } from './components/character/item-list-popup/item-list-popup.component';
import { ItemListComponent } from './components/character/item-list/item-list.component';
import { QuantityPopupComponent } from './components/character/quantity-popup/quantity-popup.component';
import { registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import { ArmorClassPopupComponent } from './components/character/armor-class-popup/armor-class-popup.component';
import { CharacterSpellsComponent } from './components/character/character-spells/character-spells.component';

registerLocaleData(localeDe);

@NgModule({
  declarations: [
    App,
    TableComponent,
    HomeComponent,
    SingleFileUploadComponent,
    TraitPopupComponent,
    BackgroundsComponent,
    RacesComponent,
    DndClassesComponent,
    ClassDetailsPopupComponent,
    SpellsComponent,
    SpellDetailsPopupComponent,
    ItemsComponent,
    ItemDetailsPopupComponent,
    FeatsComponent,
    FeatTextPopupComponent,
    CharacterOverviewComponent,
    CharacterListComponent,
    CharacterCreationComponent,
    PointBuyComponent,
    PointBuyPopupComponent,
    FeatListPopupComponent,
    DownloadsPopupComponent,
    ItemListPopupComponent,
    ItemListComponent,
    QuantityPopupComponent,
    ArmorClassPopupComponent,
    CharacterSpellsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatExpansionModule,
    MatCheckboxModule,
    MatSelectModule,
    MatTabsModule,
    MatProgressBarModule,
    MatTooltipModule,
    MatListModule,
    NgxMatSelectSearchModule, 
],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideClientHydration(withEventReplay()),
    provideHttpClient(withFetch(), withInterceptors([loadingInterceptor])),
     { provide: LOCALE_ID, useValue: 'de-DE' },
  ],
  bootstrap: [App]
})
export class AppModule { }
