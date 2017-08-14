import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params, Router} from '@angular/router';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit{
  title = 'Employee';
  public getEmpInfo;
  public name;
  public emp_id;
  public designation;
  public projectName;
  public myLeaveList= [];
  public myLeavesInfo= [];
  public success;
  public successForList;
  public errorForList;
  public errorMsg;
  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute, private router: Router) {}
  ngOnInit() {
    this.getEmpInfo = this.route.snapshot.params['data'];
    this.route.params.subscribe((params: Params) => {
      this.name = params['fullName'];
      this.emp_id = params['emp_id'];
      this.designation = params['designation'];
      this.projectName = params['projectName'];
    });
  }
  getMyLeavesTable() {
    this._employeeServive.getEmpLeaveListService(this.emp_id)
      .subscribe(resEmploeeData => {
          if (resEmploeeData != null) {
            this.myLeaveList = resEmploeeData;
            this.successForList = true;
            this.errorForList = false;
          }else {
            this.errorForList = true;
            this.successForList = false;
          }
        },
        // this.empLeaveList = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
  }
  getMyLeavesInfo() {
    this._employeeServive.getEmployeeInfo(this.emp_id)
      .subscribe(resEmploeeData => {
          if (resEmploeeData != null) {
            this.myLeavesInfo = resEmploeeData;
            this.success = true;
            this.errorForList = false;
          }else {
            this.errorForList = true;
            this.success = false;
          }
        },
        // this.empLeaveList = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);

  }
  editProfile() {
    console.log('in employee comp :' + this.emp_id);
    this.router.navigate(['employee/edit-profile', this.emp_id]);
  }
}
