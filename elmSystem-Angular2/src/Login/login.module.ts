import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';
import {RouterModule} from '@angular/router';
import {AdminModule} from '../Admin/admin.module';
import {ForgotPasswordComponent} from './forgotPassword.component';
import {ResetPasswordComponent} from './resetPassword.component';
import {LoginServiceComponent} from './loginService.component';
@NgModule({
  declarations: [ForgotPasswordComponent, ResetPasswordComponent
  ],
  imports: [
    BrowserModule, FormsModule, HttpModule, ReactiveFormsModule, RouterModule, AdminModule
  ],
  providers: [LoginServiceComponent],
  bootstrap: []
})
export class LoginModule { }
