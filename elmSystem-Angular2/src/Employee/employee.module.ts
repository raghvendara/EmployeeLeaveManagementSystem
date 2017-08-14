import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EditProfileComponent} from './edit-profile.component';
@NgModule({
  declarations: [EditProfileComponent],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: []
})
export class EmployeeModule { }
