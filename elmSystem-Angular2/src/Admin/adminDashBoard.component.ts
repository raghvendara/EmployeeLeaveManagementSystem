import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params} from '@angular/router';
import {Router} from '@angular/router';
import {LoginServiceComponent} from '../Login/loginService.component';
import { LocalStorageService } from 'angular-2-local-storage';

@Component({
  selector: 'app-admindashboard',
  templateUrl: './adminDashboard.html',
  styleUrls: ['./adminDashboard.css']
})
export class AdminDashBoardComponent implements OnInit {
  errorMsg: string;
  employees= [];
  public getAdminInfo= [];
  public name;
  public emp_id;
  public designation;
  public projectName;
  public leaveAcceptToken;
  public leaveRejectToken;
  public sessionDestroyToken;
  public fromLocalStorage;
  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute, private router: Router,
              private _loginService: LoginServiceComponent,
              private localStorageService: LocalStorageService) {}
  ngOnInit() {
    this.emp_id = this.localStorageService.get('emp_id');
    this.name = this.localStorageService.get('fullName');
    this.designation  = this.localStorageService.get('designation');
    this.projectName = this.localStorageService.get('projectName');
    this._employeeServive.getRequestedEmployees(this.projectName, this.designation)
      .subscribe(resEmploeeData => this.employees = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log('in admin component while refreshing : ' + this.designation);
    console.log('login status :' + this._loginService.getIsLogStatus());
    console.log('designation :' + this._loginService.getUserDesignation());
    console.log('user data:' + this._loginService.getUserData());
    console.log('user name :' + this._loginService.getLoggedUserName());


  }
  acceptLeave(emoployee, designation) {
    this._employeeServive.acceptRequestedLeave(emoployee, designation)
      .subscribe(resEmploeeData => { this.leaveAcceptToken = resEmploeeData;
          console.log('leave accept token :' + resEmploeeData);
          if ( resEmploeeData === true) {
            this._employeeServive.getRequestedEmployees(this.projectName, this.designation)
              .subscribe(resEmploeeData => this.employees = resEmploeeData,
                resEmployeeError => this.errorMsg = resEmployeeError);      }
        },
        resEmployeeError => this.errorMsg = resEmployeeError);
  }
  rejectLeave(emoployee, designation) {
    this._employeeServive.rejectRequestedLeave(emoployee, designation)
      .subscribe(resEmploeeData => {this.leaveRejectToken = resEmploeeData;
          console.log('leave reject token :' + resEmploeeData);
          if (resEmploeeData === true) {
            this._employeeServive.getRequestedEmployees(this.projectName, this.designation)
              .subscribe(resEmploeeData => this.employees = resEmploeeData,
                resEmployeeError => this.errorMsg = resEmployeeError);       }
        },
        resEmployeeError => this.errorMsg = resEmployeeError);
    // this.router.navigate(['/admin/admin-page', this._loginService.getUserData()]);
  }
}
