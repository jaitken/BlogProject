import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EntriesComponent} from './entries/entries.component';
import {NewEntryComponent} from './new-entry/new-entry.component';
import {EntryDetailComponent} from './entry-detail/entry-detail.component';

const routes: Routes = [
  {path: '', redirectTo: '/entries', pathMatch: 'full' },
  {path: 'entries', component: EntriesComponent},
  {path: 'newEntry', component: NewEntryComponent},
  {path: 'detail/:id', component: EntryDetailComponent}
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
