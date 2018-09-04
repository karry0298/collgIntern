import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CaretakerComponent } from './caretaker/caretaker.component';
import { DoctorComponent } from './doctor/doctor.component';
import { LoginComponent } from './login/login.component';
import { GuardianComponent } from './guardian/guardian.component';
import { AngularFireModule } from 'angularfire2';
import { environment } from './../environments/environment';
import {AngularFireDatabaseModule } from 'angularfire2/database';
import { DoctorPatientviewComponent } from './doctor-patientview/doctor-patientview.component';
import { DoctorIssuesComponent } from './doctor-issues/doctor-issues.component';
import { DoctorMedicationsComponent } from './doctor-medications/doctor-medications.component';
import { HttpModule } from '@angular/http';
import { AuthGuard } from './auth.service';
import { router } from './app-routing.module';
import { GraphComponent } from './graph/graph.component';

import { AngularFireStorageModule } from 'angularfire2/storage';


@NgModule({
  declarations: [
    AppComponent,
    CaretakerComponent,
    DoctorComponent,
    LoginComponent,
    GuardianComponent,
    DoctorPatientviewComponent,
    DoctorIssuesComponent,
    DoctorMedicationsComponent,
    GraphComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireStorageModule,
    AngularFireAuthModule,
    HttpModule,
    router,
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
