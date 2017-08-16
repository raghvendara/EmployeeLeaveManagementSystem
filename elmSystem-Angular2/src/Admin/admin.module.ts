import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';
import {LoginServiceComponent} from "../Login/loginService.component";


@NgModule({
  declarations: [],
  imports: [
    BrowserModule, FormsModule, HttpModule, ReactiveFormsModule
  ],
  providers: [LoginServiceComponent],
  bootstrap: []
})
export class AdminModule { }
