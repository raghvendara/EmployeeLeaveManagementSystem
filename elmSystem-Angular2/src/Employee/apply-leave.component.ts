import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-applyleave',
  templateUrl: './apply-leave.component.html',
  styleUrls: ['./apply-leave.component.css']
})
export class ApplyLeaveComponent implements OnInit {
  public getEmpInfo;
  public employye_id;
  public successForList;
  public errorMsg;
  public applyLeaveToken;
  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute, private router: Router) {}
    applyLeaveForm = new FormGroup({
    emp_id: new FormControl(),
      leave_type: new FormControl(),
      date_of_leave: new FormControl(),
      no_of_days: new FormControl(),
      reason: new FormControl(),
      // applied_date: new FormControl()
  });
  ngOnInit() {
    this.getEmpInfo = this.route.snapshot.params['emp_id'];
    this.route.params.subscribe((params: Params) => {
      this.employye_id = params['emp_id'];
    });
    // this._employeeServive.getEmpProfile(this.employye_id)
    //   .subscribe(resEmploeeData => this.profile = resEmploeeData,
    //     resEmployeeError => this.errorMsg = resEmployeeError);
    // console.log(this.profile);
  }
  applyLeave() {
    this._employeeServive.applyLeave(this.applyLeaveForm.value)
      .subscribe(resEmploeeData => this.applyLeaveToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log(this.applyLeaveToken);
  }
}
