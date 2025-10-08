import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { CharacterOverviewComponent } from './components/character-creation/character-overview/character-overview.component';

const routes: Routes = [
  { path: '', component: HomeComponent }, 
  { 
    path: 'character/:id', 
    component: CharacterOverviewComponent,
    data: { renderMode: 'client' } // ⬅️ Disable prerendering for dynamic route
  },
  { path: '**', redirectTo: '' }   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
