import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import {routingComponents} from './routing.module';
import {RoutingModule} from './routing.module';
import {EmployeeService} from './employeeService.component';
import {RouterModule} from '@angular/router';
import {AuthGuardService} from './auth-guard-service';
import {AdminModule} from '../Admin/admin.module';
import {LoginModule} from '../Login/login.module';
import {EmployeeModule} from '../Employee/employee.module';
import {EmployeeSearchModule} from '../EmployeeSearch/employeeSearch.module';
import {LoginServiceComponent} from '../Login/loginService.component';
@NgModule({
  declarations: [
    AppComponent, routingComponents
  ],
  imports: [
    BrowserModule, FormsModule, HttpModule, ReactiveFormsModule, RoutingModule,
    RouterModule, AdminModule, LoginModule, EmployeeModule, EmployeeSearchModule
  ],
  providers: [EmployeeService, AuthGuardService, LoginServiceComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
