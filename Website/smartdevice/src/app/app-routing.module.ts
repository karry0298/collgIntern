import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CaretakerComponent } from './caretaker/caretaker.component';
import { DoctorComponent } from './doctor/doctor.component';
import { GuardianComponent } from './guardian/guardian.component';
import { LoginComponent } from './login/login.component';
import { MedicationComponent } from './doctor-medications/doctor-medications.component';
import { IssuesComponent } from './doctor-issues/doctor-issues.component';
const routes: Routes = [
  {
    path: 'caretaker',
    component: CaretakerComponent
  },
  {
    path: 'doctor',
    component: DoctorComponent
  },
  {
    path: 'guardian',
    component: GuardianComponent
  },
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'doctor/issues',
    component: IssuesComponent,
    outlet:lowerbar
  },
  {
    path: 'doctor/medications',
    component: MedicationComponent,
    outlet:lowerbar
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
