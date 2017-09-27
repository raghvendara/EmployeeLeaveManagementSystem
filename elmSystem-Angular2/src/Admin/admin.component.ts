import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params} from '@angular/router';
import {Router} from '@angular/router';
import {LoginServiceComponent} from '../Login/loginService.component';
import { LocalStorageService } from 'angular-2-local-storage';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
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
    // this.getAdminInfo = this.route.snapshot.params['data'];
    // this.route.params.subscribe((params: Params) => {
    //   this.name = params['fullName'];
    //   this.emp_id = params['emp_id'];
    //   this.designation = params['designation'];
    //   this.projectName = params['projectName'];
    //   this._loginService.setIsLogStatus(true);
    //   this._loginService.setUserDesignation(this.designation);
    // });
    this.emp_id = this.localStorageService.get('emp_id');
    this.name = this.localStorageService.get('fullName');
    this.designation  = this.localStorageService.get('designation');
    this.projectName = this.localStorageService.get('projectName');

  }
  // acceptLeave(emoployee, designation) {
  //   this._employeeServive.acceptRequestedLeave(emoployee, designation)
  //     .subscribe(resEmploeeData => { this.leaveAcceptToken = resEmploeeData;
  //         console.log('leave accept token :' + resEmploeeData);
  //         if ( resEmploeeData === true) {
  //           this._employeeServive.getRequestedEmployees(this.projectName, this.designation)
  //             .subscribe(resEmploeeData => this.employees = resEmploeeData,
  //               resEmployeeError => this.errorMsg = resEmployeeError);      }
  //       },
  //       resEmployeeError => this.errorMsg = resEmployeeError);
  // }
  // rejectLeave(emoployee, designation) {
  //   this._employeeServive.rejectRequestedLeave(emoployee, designation)
  //     .subscribe(resEmploeeData => {this.leaveRejectToken = resEmploeeData;
  //         console.log('leave reject token :' + resEmploeeData);
  //         if (resEmploeeData === true) {
  //           this._employeeServive.getRequestedEmployees(this.projectName, this.designation)
  //             .subscribe(resEmploeeData => this.employees = resEmploeeData,
  //               resEmployeeError => this.errorMsg = resEmployeeError);       }
  //       },
  //       resEmployeeError => this.errorMsg = resEmployeeError);
  //   // this.router.navigate(['/admin/admin-page', this._loginService.getUserData()]);
  // }
  logout() {
    this._loginService.setIsLogStatus(false);
    this.localStorageService.clearAll();
    this._employeeServive.destroySession()
      .subscribe(resEmploeeData => this.sessionDestroyToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log('destroyed session: =====>' + this.sessionDestroyToken);
    this.router.navigate(['/home']);
  }
  goToEditProfile() {
    this.router.navigate(['admin/edit-profile', this.emp_id]);
  }
  applyLeave() {
    this.router.navigate(['employee/apply-leave', this.emp_id]);
  }
  getLeavesInfo() {
    this.router.navigate(['employee/leaves-info']);
  }
  goToAdminDashBoard() {
    this.router.navigate(['/admin/dash-board']);
  }
  searchEmployee() {
    this.router.navigate(['/admin/search']);
  }
  addEmployee() {
    this.router.navigate(['/admin/addEmployee']);
  }
}
