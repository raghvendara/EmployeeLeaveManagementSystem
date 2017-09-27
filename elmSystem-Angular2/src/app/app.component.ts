import {Component, OnInit} from '@angular/core';
import {EmployeeService} from './employeeService.component';
import {LoginServiceComponent} from '../Login/loginService.component';
import {Router} from '@angular/router';
import {RoutingModule} from './routing.module';
import { LocalStorageService } from 'angular-2-local-storage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ELM System';
  public flagtonav = true;
  isLoggedIn: boolean = false;
  loggedUser: string;
  loggedUserDesignation: string;
  userData;
  storedData: any;
  public errorMsg;
  public status;
  public data;
  public sessionDestroyToken;
  constructor(private _loginService: LoginServiceComponent,
              private route: Router,
              private _employeeServive: EmployeeService,
              private _localStorage: LocalStorageService) {}
  ngOnInit() {
    this._loginService.loggedUserUpdated.subscribe(
      (loggedUser) => {
        this.loggedUser = this._loginService.getLoggedUserName();
      }
    );
    this._loginService.isLoggedInUpdated.subscribe(
      (isLoggedIn) => {
        this.isLoggedIn = this._loginService.getIsLogStatus();
      }
    );
    this._loginService.userDesignationUpdated.subscribe(
      (userDesignation) => {
        this.loggedUserDesignation = this._loginService.getUserDesignation();
        console.log('in app component : ' + this._loginService.getUserDesignation());

      }
    );
    this._loginService.userDataUpdated.subscribe(
      (userData) => {
        this.userData = this._loginService.getUserData();
      }
    );
  }
  routeToLogin() {
    this.flagtonav = false;
    console.log(this.flagtonav);
    this.route.navigate(['home/login']);
  }
}
