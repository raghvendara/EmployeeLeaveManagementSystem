
import {Injectable} from '@angular/core';
import {Http, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Headers} from '@angular/http';
// import {EmployeeListComponent} from './employee.list.component';
@Injectable()
export class EmployeeService {
  private _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/login';
  // isLoggedIn= true;
  // getStatus() { return this.isLoggedIn; }
  // setStatus(status) { this.isLoggedIn = status; }
  // authInfo$: EmployeeService;
  constructor(private _http: Http) {}
  _errorHandler(error: Response) {
    console.error(error);
    return Observable.throw(error || 'Server Error');
  }
  checkForSession() {
    const _url2 = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/login/checkSession';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url2, { headers: headers, withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmployees() {
    return this._http.get(this._url, {withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);

  }
  postJson(postData) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(this._url, postData, { headers: headers, withCredentials: true })
      .map((res: Response) => res.json())
        .catch(this._errorHandler);
  }
  getRequestedEmployees(projectName, designation) {
   const _url2 = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/admin/' + designation + '/' + projectName;
    console.log(_url2);
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url2, {withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  acceptRequestedLeave(emoployee, designation) {
    const _url3 = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/admin/accept/' + designation;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url3, emoployee, {withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  rejectRequestedLeave(emoployee, designation) {
    const _url4 = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/admin/reject/' + designation;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url4, emoployee, {withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmployeeInfo(emp_id) {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/admin/search/' + emp_id;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url, {withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmpLeaveListService(emp_id) {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/admin/search/leavelist/' + emp_id;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url, {withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  addEmployee(empForm) {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/admin/addEmployee';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, empForm, {withCredentials: true } )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  forgotPassword(forgotPasswdData) {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/login/forgotPassword';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, forgotPasswdData )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  newPassword(newPasswordData, emp_id) {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/login/resetPassword';
    const postData = {
      'passWd' : newPasswordData.passWd,
      'cPassWd' : newPasswordData.cPassWd,
      'empID' : emp_id
    }
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, postData )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  destroySession() {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/login/destroySession';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmpProfile(emp_id) {
    console.log('going to rest for profile with id' + emp_id);
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/employee/get-profile/' + emp_id;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  editProfile(editProfileForm) {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/employee/edit-profile';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, editProfileForm )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  applyLeave(applyLeaveForm) {
    const _url = 'http://localhost:8080/elmSystem-1.0-SNAPSHOT/login/apply-leave';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, applyLeaveForm )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
}
