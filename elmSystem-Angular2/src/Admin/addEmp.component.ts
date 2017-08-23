import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {EmployeeService} from '../app/employeeService.component';
import {Router} from "@angular/router";

@Component({
  selector: 'app-addepm',
  templateUrl: './addEmp.component.html',
  styleUrls: ['./addEmp.component.css']
})
export class AddEmpComponent implements OnInit{
  title = 'Add Employee';
  public responseToAdd;
  public errorMsg;
  public empId: string;
  public isEmpIdValid: boolean;
  public isUserNameValid: boolean;
  constructor(private _empService: EmployeeService, private router: Router) {}
  empForm= new FormGroup({
    firstName: new FormControl(),
    lastName: new FormControl(),
    empID: new FormControl(),
    designation: new FormControl(),
    project: new FormControl(),
    email: new FormControl(),
    userName: new FormControl(),
    password: new FormControl(),
    mobileNumber: new FormControl()
  });
  ngOnInit() {
    this.empForm.reset();
  }
  addEmp() {
    console.log(this.empForm.value)
    this._empService.addEmployee(this.empForm.value)
      .subscribe(resEmploeeData => { this.responseToAdd = resEmploeeData;
          if (resEmploeeData === true) {
            this.empForm.reset()
            console.log(resEmploeeData);
            this.router.navigate(['/admin/addEmployee']);
          }
          },
        resEmployeeError => this.errorMsg = resEmployeeError);
     console.log(this.responseToAdd);
  }
  checkEmpIdValidity(empId) {
    console.log('in checkEmpIdValidity going to service');
    this._empService.checkEmpIdValidityService(empId)
      .subscribe(resEmploeeData => {
          console.log(resEmploeeData);
        this.isEmpIdValid = resEmploeeData;
          console.log(this.isEmpIdValid);
        },
        resEmployeeError => this.errorMsg = resEmployeeError);
  }
  checkUserNameValidity(userName) {
    console.log('in checkUserNameValidity going to service');
    this._empService.checkUserNameValidityService(userName)
      .subscribe(resEmploeeData => {
          console.log(resEmploeeData);
          this.isUserNameValid = resEmploeeData;
          console.log(this.isUserNameValid);
        },
        resEmployeeError => this.errorMsg = resEmployeeError);
  }
}
