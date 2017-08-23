import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {LoginServiceComponent} from "../Login/loginService.component";

@Component({
  selector: 'app-applyleave',
  templateUrl: './apply-leave.component.html',
  styleUrls: ['./apply-leave.component.css']
})
export class ApplyLeaveComponent implements OnInit {
  public getEmpInfo;
  public employye_id;
  public successForList;
  public empLeaveInfo = [];
  public leaveType;
  public balInSick;
  public balInCasual;
  public balInPrev;
  public errorMsg;
  public applyLeaveToken;
  public validationFlag = true;
  public noOfDays;
  public today;
  public dummyToday;
  public maxDate;

  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute,
              private router: Router, private _loginService: LoginServiceComponent) {
  }

  applyLeaveForm = new FormGroup({
    emp_id: new FormControl(),
    leave_type: new FormControl(),
    date_of_leave: new FormControl(),
    no_of_days: new FormControl(),
    reason: new FormControl(),
    // applied_date: new FormControl()
  });

  ngOnInit() {
    this.today = new Date().toJSON().split('T')[0];
    this.dummyToday = new Date();
    this.maxDate = new Date(this.dummyToday.setDate(this.dummyToday.getDate() + 30)).toJSON().split('T')[0];
    console.log('todays date : ' + this.today);
    console.log('max date :' + new Date(this.maxDate).toJSON().split('T')[0]);
    console.log('in apply leave : ' + this._loginService.getIsLogStatus());
    this._loginService.setUserDesignation('employee');
    console.log('in apply leave : ' + this._loginService.getUserDesignation());
    this.getEmpInfo = this.route.snapshot.params['emp_id'];
    this.route.params.subscribe((params: Params) => {
      this.employye_id = params['emp_id'];
      this.applyLeaveForm.reset();
    });
  }

  applyLeave() {
    this._employeeServive.applyLeave(this.applyLeaveForm.value)
      .subscribe(resEmploeeData => {
          console.log('apply leave token : ' + resEmploeeData);
          if (resEmploeeData === true) {
            this.applyLeaveForm.reset();
            this.router.navigate(['/employee', this._loginService.getUserData()]);
          }
          this.applyLeaveToken = resEmploeeData;
        },
        resEmployeeError => this.errorMsg = resEmployeeError);
  }

  checkLeaveValidity() {
    this._employeeServive.checkLeaveValidity(this.employye_id)
      .subscribe(resEmploeeData => {
          this.balInSick = resEmploeeData.bal_in_sick;
          this.balInPrev = resEmploeeData.bal_in_privilege;
          this.balInCasual = resEmploeeData.bal_in_casual;
          console.log('apply leave token : ' + resEmploeeData);
          this.empLeaveInfo = resEmploeeData;
        },
        resEmployeeError => this.errorMsg = resEmployeeError);
  }

  setValidationFlag() {
    this.validationFlag = true;
    if (this.leaveType === 'casual') {
      if (this.noOfDays > this.balInCasual) {
        this.validationFlag = false;
      }
    }
    if (this.leaveType === 'sick') {
      if (this.noOfDays > this.balInSick) {
        this.validationFlag = false;
      }
    }
    if (this.leaveType === 'previlege') {
      if (this.noOfDays > this.balInPrev) {
        this.validationFlag = false;
      }
    }
    console.log('validation flag :' + this.validationFlag);
  }
}
