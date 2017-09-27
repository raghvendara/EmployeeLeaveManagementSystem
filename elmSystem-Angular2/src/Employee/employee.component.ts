import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {LoginServiceComponent} from "../Login/loginService.component";
import {LocalStorageService} from "angular-2-local-storage/dist";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  title = 'Employee';
  public sessionDestroyToken;
  public getEmpInfo;
  public name;
  public emp_id;
  public designation;
  public projectName;
  public myLeaveList= [];
  public leavesInfo= [];
  public success;
  public successForList;
  public errorForList;
  public errorMsg;
  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute,
              private router: Router, private localStorageService: LocalStorageService,
              private  _loginService: LoginServiceComponent) {}
  ngOnInit() {
    this.emp_id = this.localStorageService.get('emp_id');
    this.name = this.localStorageService.get('fullName');
    this.designation  = this.localStorageService.get('designation');
    this.projectName = this.localStorageService.get('projectName');
  }
  editProfile() {
    console.log('in employee comp :' + this.emp_id);
    this.router.navigate(['employee/edit-profile', this.emp_id]);
  }
  applyLeave() {
    console.log('in employee comp going to apply leave:' + this.emp_id);
    this.router.navigate(['employee/apply-leave', this.emp_id]);
  }
  goToEmpDashBoard() {
    console.log('in employee comp going to dash board:' + this.emp_id);
    this.router.navigate(['employee/dash-board']);
  }
  logout() {
    this._loginService.setIsLogStatus(false);
    this.localStorageService.clearAll();
    this._employeeServive.destroySession()
      .subscribe(resEmploeeData => this.sessionDestroyToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log('destroyed session: =====>' + this.sessionDestroyToken);
    this.router.navigate(['/home']);
  }
}
