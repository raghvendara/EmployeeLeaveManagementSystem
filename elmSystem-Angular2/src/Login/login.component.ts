import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {EmployeeService} from '../app/employeeService.component';
import {Router} from '@angular/router';
import {LoginServiceComponent} from './loginService.component';
import { LocalStorageService } from 'angular-2-local-storage';
import {passBoolean} from "protractor/built/util";
import {isBoolean} from "util";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public postData;
  public errorMsg;
  public error;
  public status;
  public data;
  public flag= false;
  userForm= new FormGroup({
    userName: new FormControl(),
    password: new FormControl()
  });
  ngOnInit() {
  }
  /*
  onSubmit(value: any) {
    console.log(this.userForm.value);
  }*/
  constructor(private _employeeServive: EmployeeService,
              private router: Router,
              private _loginService: LoginServiceComponent,
              private _localStorage: LocalStorageService) {}
  onTestPost() {
    console.log(this.userForm.value);
    this._employeeServive.postJson(this.userForm.value)
      .subscribe(data => {
          this._localStorage.set('flag', data.flag);
          this._localStorage.set('emp_id', data.emp_id);
          this._localStorage.set('fullName', data.fullName);
          this._localStorage.set('designation', data.designation);
          this._localStorage.set('projectName', data.projectName);



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
            this.router.navigate(['/admin/dash-board']);
          }else if (data.flag === true && data.designation === 'employee') {
            console.log(data.designation);
            this._loginService.setIsLogStatus(true);
            console.log('in employee log status :' + this._loginService.getIsLogStatus())

            this._loginService.setLoggesUser(data.fullName);
            this._loginService.setUserDesignation(data.designation);
            this._loginService.setUserData(data);
            this.router.navigate(['/employee/dash-board']);
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
