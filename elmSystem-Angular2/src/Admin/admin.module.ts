import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';


@NgModule({
  declarations: [],
  imports: [
    BrowserModule, FormsModule, HttpModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: []
})
export class AdminModule { }
