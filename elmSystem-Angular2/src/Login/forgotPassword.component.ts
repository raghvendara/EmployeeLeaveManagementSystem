import { Component } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule, Validators} from '@angular/forms';
import {EmployeeService} from '../app/employeeService.component';
import {Params, Router} from '@angular/router';
@Component({
  templateUrl: './forgotPassword.component.html',
  styleUrls: ['./forgotPassword.component.css'],
})
export class ForgotPasswordComponent {
  public responseData;
  public errorMsg;
  public error;
  constructor(private _employeeServive: EmployeeService, private router: Router) {}
  forgotPwdForm= new FormGroup({
    userName: new FormControl(),
    emp_id: new FormControl(),
    email: new FormControl(),
    mobile: new FormControl()
  });
  forgotPassword() {
    this._employeeServive.forgotPassword(this.forgotPwdForm.value)
      .subscribe(data => {
          if (data.flag === true) {
            // console.log(this._employeeServive.getStatus());
            console.log('for forgot password : ' + data.flag);
            this.forgotPwdForm.reset();
            // this._employeeServive.setStatus(false);this.router.navigate(['/admin', data]);
            // console.log(this._employeeServive.getStatus());['/admin', data]
            this.responseData = data;
            this.router.navigate(['home/login/forgotPassword/resetPassword', data]);
          } else  {
            this.error = 'someting went wrong..!! in forgot password';
            console.log(this.error);
          }
        },
        dataError => this.errorMsg = dataError);
  }
}
