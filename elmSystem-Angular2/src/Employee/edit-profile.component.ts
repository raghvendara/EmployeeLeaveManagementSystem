import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../app/employeeService.component';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-employee',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  title = 'Employee';
  public getEmpInfo;
  public name;
  public employye_id;
  public designation;
  public profile= [];
  public success;
  public successForList;
  public errorMsg;
  public editProfileToken;
  constructor(private _employeeServive: EmployeeService, private route: ActivatedRoute, private router: Router) {}
  editProfileForm = new FormGroup({
    fullName:  new FormControl(),
    emp_id: new FormControl(),
    email: new FormControl(),
    mobile: new FormControl(),
    project: new FormControl(),
    designation: new FormControl()
  });
  ngOnInit() {
    this.getEmpInfo = this.route.snapshot.params['emp_id'];
    this.route.params.subscribe((params: Params) => {
      this.employye_id = params['emp_id'];
    });
    this._employeeServive.getEmpProfile(this.employye_id)
      .subscribe(resEmploeeData => this.profile = resEmploeeData,
  resEmployeeError => this.errorMsg = resEmployeeError);
  console.log(this.profile);
  }
  editProfile() {
    this._employeeServive.editProfile(this.editProfileForm.value)
      .subscribe(resEmploeeData => this.editProfileToken = resEmploeeData,
        resEmployeeError => this.errorMsg = resEmployeeError);
    console.log(this.editProfileToken);
  }
}
