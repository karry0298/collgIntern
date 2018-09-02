import { NgModule,ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CaretakerComponent } from './caretaker/caretaker.component';
import { DoctorComponent } from './doctor/doctor.component';
import { GraphComponent } from './graph/graph.component';
import { GuardianComponent } from './guardian/guardian.component';
import { LoginComponent } from './login/login.component';
import { DoctorIssuesComponent } from './doctor-issues/doctor-issues.component';
import { DoctorMedicationsComponent } from './doctor-medications/doctor-medications.component';
import { AuthGuard } from './auth.service';
import { NewDoctorRegisterComponent } from './new-doctor-register/new-doctor-register.component';

export const routes: Routes = [
  {
    path: 'register',
    component: NewDoctorRegisterComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'doctor',
    component: DoctorComponent,
    canActivate: [AuthGuard],
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
    path: 'graph',
    component: GraphComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const router: ModuleWithProviders = RouterModule.forRoot(routes);
