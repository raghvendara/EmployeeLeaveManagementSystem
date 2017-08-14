import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {EmployeeService} from '../app/employeeService.component';

@Component({
  selector: 'app-addepm',
  templateUrl: './addEmp.component.html',
  styleUrls: ['./addEmp.component.css']
})
export class AddEmpComponent {
  title = 'Add Employee';
  public responseToAdd;
  public errorMsg;
  constructor(private _empService: EmployeeService) {}
  empForm= new FormGroup({
    firstName: new FormControl(),
    lastName: new FormControl(),
    empID: new FormControl(),
    designation: new FormControl(),
    project: new FormControl(),
    email: new FormControl(),
    userName: new FormControl(),
    password: new FormControl(),
    // password2: new FormControl(),
    mobileNumber: new FormControl()
  });
  addEmp() {
    console.log(this.empForm.value)
    this._empService.addEmployee(this.empForm.value)
      .subscribe(resEmploeeData => this.responseToAdd = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
     console.log(this.responseToAdd);
  }
}
