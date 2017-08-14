import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {EmployeeService} from '../app/employeeService.component';

@Component({
  selector: 'app-searchemp',
  templateUrl: './employeeSearch.component.html',
  styleUrls: ['./employeeSearch.component.css']
})
export class SearchComponent {
  title = 'Search Employee';
  public errorMsg;
  public empId;
  public error;
  public success;
  public emp_id= [];
  public empLeaveList= [];
  public successForList;
  public errorForList;
  // public availed_leaves;
  // public bal_in_sick;
  // public bal_in_casual;
  // public bal_in_privilege;
  // public total_bal_leaves;
  constructor(private route: Router, private _employeeServive: EmployeeService) {}
  goToAdminPage() {
    this.route.navigate(['/admin']);
  }
  searchEmployee(emp_id) {
    this._employeeServive.getEmployeeInfo(emp_id)
      .subscribe(resEmploeeData => {
        if (resEmploeeData != null) {
        this.emp_id = resEmploeeData;
        this.success = true;
        this.error = false;
        }else {
          this.error = true;
          this.success = false;
        }
        },
        resEmployeeError => this.errorMsg = resEmployeeError);
  }
  getEmpLeaveList(emp_id) {
    this._employeeServive.getEmpLeaveListService(emp_id)
      .subscribe(resEmploeeData => {
          if (resEmploeeData != null) {
            this.empLeaveList = resEmploeeData;
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
}

