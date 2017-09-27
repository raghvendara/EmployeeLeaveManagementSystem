import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {LoginServiceComponent} from "../Login/loginService.component";
import {LocalStorageService} from "angular-2-local-storage/dist";

@Component({
  selector: 'app-employeedashboard',
  templateUrl: './employeeDashboard.html',
  styleUrls: ['./employeeDashboard.css']
})
export class EmployeeDashboardComponent implements OnInit {
  title = 'Employee';
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
    // this.getEmpInfo = this.route.snapshot.params['data'];
    // this.route.params.subscribe((params: Params) => {
    //   this.name = params['fullName'];
    //   this.emp_id = params['emp_id'];
    //   this.designation = params['designation'];
    //   this.projectName = params['projectName'];
    //   this._loginService.setIsLogStatus(true);
    //   this._loginService.setUserDesignation('employee');
    // });
    this.emp_id = this.localStorageService.get('emp_id');
    this.name = this.localStorageService.get('fullName');
    this.designation  = this.localStorageService.get('designation');
    this.projectName = this.localStorageService.get('projectName');
    this._employeeServive.getEmpLeaveListService(this.emp_id)
      .subscribe(resEmploeeData => {
          if (resEmploeeData != null) {
            this.myLeaveList = resEmploeeData;
            this.successForList = true;
            console.log('got emp leave list' , this.successForList);
            this.errorForList = false;
          }else {
            console.log(' emp leave list not returned');
            this.errorForList = true;
            this.successForList = false;
          }
        },
        // this.empLeaveList = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);

    this._employeeServive.getEmployeeInfo(this.emp_id)
      .subscribe(resEmploeeData => {
          if (resEmploeeData != null) {
            this.leavesInfo = resEmploeeData[0];
            console.log('emp id: ', this.leavesInfo);
            this.success = true;
            this.errorForList = false;
            console.log('got leaves info to display ... ', this.success, resEmploeeData.emp_id);
          }else {
            console.log('not getting leaves info to display ... ')
            this.errorForList = true;
            this.success = false;
          }
        },
        // this.empLeaveList = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);

  }
}
