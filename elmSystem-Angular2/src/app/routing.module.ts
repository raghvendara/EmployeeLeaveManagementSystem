import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from '../Login/login.component';
import {AdminComponent} from '../Admin/admin.component';
import {HomeComponent} from './home.component';
import {AuthGuardService} from './auth-guard-service';
import {SearchComponent} from '../EmployeeSearch/search.component';
import {AddEmpComponent} from '../Admin/addEmp.component';
import {EmployeeComponent} from '../Employee/employee.component';
import {ForgotPasswordComponent} from '../Login/forgotPassword.component';
import {ResetPasswordComponent} from '../Login/resetPassword.component';
import {EditProfileComponent} from '../Employee/edit-profile.component';
import {ApplyLeaveComponent} from '../Employee/apply-leave.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'home/login', component: LoginComponent },
  { path: 'admin/admin-page', canActivate : [AuthGuardService], component: AdminComponent },
  { path: 'admin/search', canActivate : [AuthGuardService], component: SearchComponent },
  { path: 'admin/addEmployee', canActivate : [AuthGuardService], component: AddEmpComponent },
  { path: 'employee', canActivate : [AuthGuardService], component: EmployeeComponent },
  { path: 'home/login/forgotPassword', component: ForgotPasswordComponent },
  { path: 'home/login/forgotPassword/resetPassword', component: ResetPasswordComponent },
  { path: 'employee/edit-profile/:emp_id', canActivate : [AuthGuardService], component: EditProfileComponent },
  { path: 'employee/apply-leave/:emp_id', canActivate : [AuthGuardService], component: ApplyLeaveComponent },

  ];
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports : [RouterModule],
  providers: [AuthGuardService]
})

export class RoutingModule { }
export const routingComponents = [ LoginComponent, AdminComponent, EmployeeComponent, HomeComponent, SearchComponent, AddEmpComponent ];
