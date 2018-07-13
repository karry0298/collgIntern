import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CaretakerComponent } from './caretaker/caretaker.component';
import { DoctorComponent } from './doctor/doctor.component';
import { LoginComponent } from './login/login.component';
import { GuardianComponent } from './guardian/guardian.component';
import { AngularFireModule } from 'angularfire2';
import { environment } from './../environments/environment';
import {AngularFireDatabaseModule } from 'angularfire2/database';


@NgModule({
  declarations: [
    AppComponent,
    CaretakerComponent,
    DoctorComponent,
    LoginComponent,
    GuardianComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
