import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params} from '@angular/router';
import {Router} from '@angular/router';
import {LoginServiceComponent} from "../Login/loginService.component";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  errorMsg: string;
  employees= [];
  public getAdminInfo;
  public name;
  public emp_id;
  public designation;
  public projectName;
  public leaveAcceptToken;
  public leaveRejectToken;
  public sessionDestroyToken;
  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute, private router: Router,
              private _loginService: LoginServiceComponent) {}
  ngOnInit() {
   this.getAdminInfo = this.route.snapshot.params['data'];
   this.route.params.subscribe((params: Params) => {
     this.name = params['fullName'];
     this.emp_id = params['emp_id'];
     this.designation = params['designation'];
     this.projectName = params['projectName'];
   });
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
      .subscribe(resEmploeeData => this.leaveAcceptToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log('leave accept token :' + this.leaveAcceptToken);
      this.router.navigate(['/admin/admin-page', this._loginService.getUserData()]);
  }
  rejectLeave(emoployee, designation) {
    this._employeeServive.rejectRequestedLeave(emoployee, designation)
      .subscribe(resEmploeeData => this.leaveRejectToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
      this.router.navigate(['/admin/admin-page', this._loginService.getUserData()]);
  }
  // routeToEmpSearch() {
  //   this.router.navigate(['/admin/search']);
  // }
  // addEmp() {
  //   this.router.navigate(['/admin/addEmployee']);
  // }
  logout() {
    this._employeeServive.destroySession()
      .subscribe(resEmploeeData => this.sessionDestroyToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log('destroyed session: =====>' + this.sessionDestroyToken);
    this.router.navigate(['/home']);
  }
}
