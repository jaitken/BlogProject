import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EntriesComponent} from './entries/entries.component';
import {NewEntryComponent} from './new-entry/new-entry.component';
import {EntryDetailComponent} from './entry-detail/entry-detail.component';
import { SigninComponent } from './signin/signin.component';
import { RegisterComponent } from './register/register.component';
import { AllentriesComponent } from './allentries/allentries.component';

const routes: Routes = [
  {path: '', redirectTo: '/signin', pathMatch: 'full' },
  {path: 'entries', component: EntriesComponent},
  {path: 'newEntry', component: NewEntryComponent},
  {path: 'detail/:id', component: EntryDetailComponent},
  {path: 'signin', component: SigninComponent},
  {path: 'signup', component: RegisterComponent},
  {path: 'allEntries', component: AllentriesComponent}
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
