import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule, Validators} from '@angular/forms';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params, Router} from '@angular/router';
@Component({
  templateUrl: './resetPassword.component.html',
  styleUrls: ['./resetPassword.component.css'],
})
export class ResetPasswordComponent implements OnInit{
  public responseData;
  public errorMsg;
  public error;
  public emp_id;
  public resetPasswordInfo;
  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute) {}
  newPassword= new FormGroup({
    passWd: new FormControl(),
    cPassWd: new FormControl(),
  });
  ngOnInit() {
    this.resetPasswordInfo = this.route.snapshot.params['data'];
    this.route.params.subscribe((params: Params) => {
      this.emp_id = params['emp_id'];
    });
  }
  resetPassword() {
    this._employeeServive.newPassword(this.newPassword.value, this.emp_id)
      .subscribe(data => {
          if (data === true) {
            // console.log(this._employeeServive.getStatus());
            console.log('for reset password : ' + data);
            // this._employeeServive.setStatus(false);
            // console.log(this._employeeServive.getStatus());
            this.responseData = data;
          } else  {
            this.error = 'someting went wrong..!! in restet password status is :' + data;
            console.log(this.error);
          }
        },
        dataError => this.errorMsg = dataError);
  }
}
