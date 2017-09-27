
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
  private baseUrl = 'http://';
  private domainName = 'localhost';
  private portNumber = '8080';
  private basePath = this.baseUrl + this.domainName + ':' + this.portNumber +  '/elmSystem';
  constructor(private _http: Http) {}
  _errorHandler(error: Response) {
    console.error(error);
    return Observable.throw(error || 'Server Error');
  }
  checkForSession() {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(this.basePath + '/login/checkSession', { headers: headers, withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmployees() {
    return this._http.get(this.basePath + '/login')
      .map((response: Response) => response.json())
      .catch(this._errorHandler);

  }
  postJson(postData) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(this.basePath + '/login', postData, { headers: headers, withCredentials: true })
      .map( (res: Response) => res.json())
       // console.log('in login post status : ' + res.status + 'head : ' + res.headers);)
        .catch(this._errorHandler);
  }
  getRequestedEmployees(projectName, designation) {
   const _url2 = this.basePath + '/admin/' + designation + '/' + projectName;
    console.log(_url2);
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url2, { headers: headers, withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  acceptRequestedLeave(emoployee, designation) {
    const _url3 = this.basePath + '/admin/accept/' + designation;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url3, emoployee)
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  rejectRequestedLeave(emoployee, designation) {
    const _url4 = this.basePath + '/admin/reject/' + designation;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url4, emoployee)
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmployeeInfo(emp_id) {
    const _url = this.basePath + '/admin/search/' + emp_id;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url)
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmpLeaveListService(emp_id) {
    console.log('going for rest to get the list');
    const _url = this.basePath + '/admin/search/leavelist/' + emp_id;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url)
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  addEmployee(empForm) {
    const _url = this.basePath + '/admin/addEmployee';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, empForm )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  forgotPassword(forgotPasswdData) {
    const _url = this.basePath + '/login/forgotPassword';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, forgotPasswdData )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  newPassword(newPasswordData, emp_id) {
    const _url = this.basePath + '/login/resetPassword';
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
    console.log('in destroy session going to rest....')
    const _url = this.basePath + '/login/destroySession';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url, { headers: headers, withCredentials: true } )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  getEmpProfile(emp_id) {
    console.log('going to rest for profile with id' + emp_id);
    const _url = this.basePath + '/employee/get-profile/' + emp_id;
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(_url)
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  editProfile(editProfileForm) {
    const _url = this.basePath + '/employee/edit-profile';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, editProfileForm )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  applyLeave(applyLeaveForm) {
    const _url = this.basePath + '/login/apply-leave';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, applyLeaveForm, { headers: headers, withCredentials: true })
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  checkEmpIdValidityService(empID: string) {
    const _url = this.basePath + '/admin/addEmployee/validation';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, empID )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  checkLeaveValidity(empID: string) {
    const _url = this.basePath + '/login/apply-leave/leaveValidity';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, empID, {withCredentials: true } )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
  checkUserNameValidityService(userName: string) {
    const _url = this.basePath + '/admin/addEmployee/validate-userName';
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(_url, userName )
      .map((response: Response) => response.json())
      .catch(this._errorHandler);
  }
}
