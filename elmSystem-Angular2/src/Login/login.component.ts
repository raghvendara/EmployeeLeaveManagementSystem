import { Component } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {EmployeeService} from '../app/employeeService.component';
import {Router} from '@angular/router';
import {AdminComponent} from '../Admin/admin.component';
import {LoginServiceComponent} from './loginService.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  public postData;
  public errorMsg;
  public error;
  public flag= false;
  userForm= new FormGroup({
    userName: new FormControl(),
    password: new FormControl()
  });
  /*
  onSubmit(value: any) {
    console.log(this.userForm.value);
  }*/
  constructor(private _employeeServive: EmployeeService, private router: Router,
              private _loginService: LoginServiceComponent) {}
  onTestPost() {
    console.log(this.userForm.value);
    this._employeeServive.postJson(this.userForm.value)
      .subscribe(data => {

          if (data.flag === true && data.designation !== 'employee') {
            // console.log(this._employeeServive.getStatus());

            console.log(data.designation);
            console.log(data.flag);
             // this._loginService.isLoggedIn.subscribe();
            this.postData = data;
            this._loginService.setIsLogStatus(true);
            this._loginService.setLoggesUser(data.fullName);
            this._loginService.setUserDesignation(data.designation);
            this._loginService.setUserData(data);
            this.router.navigate(['/admin/admin-page', data]);
          }else if (data.flag === true && data.designation === 'employee') {
            console.log(data.designation);
            this._loginService.setIsLogStatus(true);
            console.log('in employee log status :' + this._loginService.getIsLogStatus())

            this._loginService.setLoggesUser(data.fullName);
            this._loginService.setUserDesignation(data.designation);
            this._loginService.setUserData(data);
            this.router.navigate(['/employee', data]);
          } else  {
            this.error = 'someting went wrong..!!';
            console.log(this.error);
          }
        },
        dataError => this.errorMsg = dataError);
  }
  goTohome() {
    this.router.navigate(['/home']);
  }
  goTologin() {
    this.router.navigate(['/home/login']);

  }
}
