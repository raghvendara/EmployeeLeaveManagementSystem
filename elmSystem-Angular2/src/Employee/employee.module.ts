import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EditProfileComponent} from './edit-profile.component';
import {ApplyLeaveComponent} from './apply-leave.component';
import {LoginServiceComponent} from "../Login/loginService.component";
@NgModule({
  declarations: [EditProfileComponent, ApplyLeaveComponent],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule
  ],
  providers: [LoginServiceComponent],
  bootstrap: []
})
export class EmployeeModule { }
