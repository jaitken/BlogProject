import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { EntriesComponent } from './entries/entries.component';
import { MessagesComponent } from './messages/messages.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NewEntryComponent } from './new-entry/new-entry.component';
import { EntryDetailComponent } from './entry-detail/entry-detail.component';
import { CommentsComponent } from './comments/comments.component';
import { SigninComponent } from './signin/signin.component';
import { RegisterComponent } from './register/register.component';
import { AuthInterceptor } from './auth-interceptor';
import { FormsModule } from '@angular/forms';
import { AllentriesComponent } from './allentries/allentries.component';

@NgModule({
  declarations: [
    AppComponent,
    EntriesComponent,
    MessagesComponent,
    NewEntryComponent,
    EntryDetailComponent,
    CommentsComponent,
    SigninComponent,
    RegisterComponent,
    AllentriesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
