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
  public userDesignation: string;
  public userData: any;

  userDesignationUpdated: EventEmitter<string> = new EventEmitter<string>();
  loggedUserUpdated: EventEmitter<string> = new EventEmitter<string>();
  isLoggedInUpdated: EventEmitter<boolean> = new EventEmitter<boolean>();
  userDataUpdated: EventEmitter<any> = new EventEmitter<any>();

  setLoggesUser(lang) {
    this.loggedUser = lang;
    this.loggedUserUpdated.emit(this.loggedUser);
  }
  setUserDesignation(lang) {
    this.userDesignation = lang;
    this.userDesignationUpdated.emit(this.userDesignation);
  }

  setIsLogStatus(value: boolean) {
    this.isLoggeIn = value;
    this.isLoggedInUpdated.emit(this.isLoggeIn);

  }
  setUserData(value: any) {
    this.userData = value;
    this.userDataUpdated.emit(this.userData);
  }

  getIsLogStatus() {
    return this.isLoggeIn;
  }

  getLoggedUserName() {
    return this.loggedUser;
  }
  getUserDesignation() {
    return this.userDesignation;
  }
  getUserData() {
    return this.userData;
  }

}
