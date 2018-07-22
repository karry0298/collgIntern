import { NgModule,ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CaretakerComponent } from './caretaker/caretaker.component';
import { DoctorComponent } from './doctor/doctor.component';
import { GuardianComponent } from './guardian/guardian.component';
import { LoginComponent } from './login/login.component';
import { DoctorIssuesComponent } from './doctor-issues/doctor-issues.component';
import { DoctorMedicationsComponent } from './doctor-medications/doctor-medications.component';
import { AuthGuard } from './auth.service';

export const routes: Routes = [
  {
    path: 'caretaker',
    component: CaretakerComponent
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

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const router: ModuleWithProviders = RouterModule.forRoot(routes);
