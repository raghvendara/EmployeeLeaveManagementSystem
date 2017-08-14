import {Component, OnInit} from '@angular/core';
import {EmployeeService} from './employeeService.component';
import {LoginServiceComponent} from '../Login/loginService.component';
import {Router} from '@angular/router';
import {RoutingModule} from './routing.module';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ELM System';
  public flagtonav = true;
  isLoggedIn: boolean = false;
  loggedUser:string;
  public errorMsg;
  public sessionDestroyToken;
  constructor(private _loginService: LoginServiceComponent, private route: Router,
              private _employeeServive: EmployeeService) {}
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
  }
  onLogOutClick() {
    this.isLoggedIn = false;
    this._employeeServive.destroySession()
      .subscribe(resEmploeeData => this.sessionDestroyToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log('destroyed session: =====>' + this.sessionDestroyToken);
    this.route.navigate(['/home']);
  }
    routeToLogin() {
    this.flagtonav = false;
    console.log(this.flagtonav);
    this.route.navigate(['home/login']);
  }
  // isLogged = this._loginService.isLoggedIn.subscribe(value => this.isLogged);
}
