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
  loggedUserDesignation: string;
  userData: any;
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
    this._loginService.userDesignationUpdated.subscribe(
      (userDesignation) => {
        this.loggedUserDesignation = this._loginService.getUserDesignation();
      }
    );
    this._loginService.userDataUpdated.subscribe(
      (userData) => {
        this.userData = this._loginService.getUserData();
      }
    );
  }
  onLogOutClick() {
    if ( confirm('are you sure to log out ...!')) {
      this.isLoggedIn = false;
      this.loggedUser = null;
      this.loggedUserDesignation = null;
      this._employeeServive.destroySession()
        .subscribe(resEmploeeData => {
            this.sessionDestroyToken = resEmploeeData,
              console.log('destroyed session: =====>' + resEmploeeData);
          },
          resEmployeeError => this.errorMsg = resEmployeeError,
        );
      this.route.navigate(['/home']);
    }
  }
    routeToLogin() {
    this.flagtonav = false;
    console.log(this.flagtonav);
    this.route.navigate(['home/login']);
  }
  goToEmpDashBoard() {
    this.route.navigate(['/employee', this.userData]);
  }
  goToEditProfile() {
    this.route.navigate(['employee/edit-profile', this.userData.emp_id]);
  }
  applyLeave() {
    this.route.navigate(['employee/apply-leave', this.userData.emp_id]);
  }
  getLeavesInfo() {
    this.route.navigate(['employee/leaves-info']);
  }
  goToAdminDashBoard() {
    this.route.navigate(['/admin/admin-page', this.userData]);
  }
  searchEmployee() {
    this.route.navigate(['/admin/search']);
  }
  addEmployee() {
    this.route.navigate(['/admin/addEmployee']);
  }

  // isLogged = this._loginService.isLoggedIn.subscribe(value => this.isLogged);
}
