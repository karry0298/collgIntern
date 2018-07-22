import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CaretakerComponent } from './caretaker/caretaker.component';
import { DoctorComponent } from './doctor/doctor.component';
import { GuardianComponent } from './guardian/guardian.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.service';

import { MedicationComponent } from './doctor-medications/doctor-medications.component';
import { IssuesComponent } from './doctor-issues/doctor-issues.component';
export const routes: Routes = [
  {
    path: 'caretaker',
    component: CaretakerComponent
  },
  {
    path: 'doctor',
    component: DoctorComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'guardian',
    component: GuardianComponent
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
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
export const router: ModuleWithProviders = RouterModule.forRoot(routes);
