import {EventEmitter, Injectable, OnChanges, Output} from "@angular/core";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Observable} from "rxjs/Observable";
import {Observer} from "rxjs/Observer";

@Injectable()

export class LoginServiceComponent {
  public loggedUser: string;
  public isLoggeIn: boolean;

  loggedUserUpdated: EventEmitter<string> = new EventEmitter<string>();
  isLoggedInUpdated: EventEmitter<boolean> = new EventEmitter<boolean>();

  setLoggesUser(lang) {
    this.loggedUser = lang;
    this.loggedUserUpdated.emit(this.loggedUser);
  }

  setIsLogStatus(value: boolean) {
    this.isLoggeIn = value;
    this.isLoggedInUpdated.emit(this.isLoggeIn);

  }

  getIsLogStatus() {
    return this.isLoggeIn;
  }

  getLoggedUserName() {
    return this.loggedUser;
  }

}
