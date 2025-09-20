import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Home } from './components/components/home/home';

const routes: Routes = [
  { path: '', component: Home }, // default route (start page)
  { path: '**', redirectTo: '' }          // fallback for unknown routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
