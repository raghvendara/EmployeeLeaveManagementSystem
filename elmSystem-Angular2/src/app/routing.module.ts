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
import {AdminDashBoardComponent} from "../Admin/adminDashBoard.component";
import {EmployeeDashboardComponent} from "../Employee/employeeDashboard.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'home/login', component: LoginComponent },
  { path: 'admin', canActivate : [AuthGuardService], component: AdminComponent,
    children: [
      { path: 'search', canActivate : [AuthGuardService], component: SearchComponent },
      { path: 'dash-board', canActivate : [AuthGuardService], component: AdminDashBoardComponent },
      { path: 'addEmployee', canActivate : [AuthGuardService], component: AddEmpComponent },
      { path: 'edit-profile/:emp_id', canActivate : [AuthGuardService], component: EditProfileComponent },

    ]
  },
  { path: 'employee', canActivate : [AuthGuardService], component: EmployeeComponent,
    children: [
      { path: 'apply-leave/:emp_id', canActivate : [AuthGuardService], component: ApplyLeaveComponent },
      { path: 'dash-board', canActivate : [AuthGuardService], component: EmployeeDashboardComponent },
      { path: 'edit-profile/:emp_id', canActivate : [AuthGuardService], component: EditProfileComponent },
    ]
  },
  { path: 'home/login/forgotPassword', component: ForgotPasswordComponent },
  { path: 'home/login/forgotPassword/resetPassword', component: ResetPasswordComponent },

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
