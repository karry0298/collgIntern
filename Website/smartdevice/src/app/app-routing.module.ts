import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CaretakerComponent } from './caretaker/caretaker.component';
import { DoctorComponent } from './doctor/doctor.component';
import { GuardianComponent } from './guardian/guardian.component';
import { LoginComponent } from './login/login.component';
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
