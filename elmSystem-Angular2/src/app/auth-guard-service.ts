import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {EmployeeService} from './employeeService.component';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class  AuthGuardService implements CanActivate {
  private errorMsg;
  private flag;
  constructor(private _empService: EmployeeService, private route: Router) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return new Promise<boolean>((resolve, reject) => this._empService.checkForSession()
      .subscribe(data => {
          this.flag = data;
          if (this.flag === true) {
          } else if (this.flag === false) {
            alert('please login to continue');
            this.route.navigate(['/login']);
          }resolve(true);
        },
        dataError => {
          this.errorMsg = dataError;
          resolve(false);
        }
      )
    );
    // this._empService.checkForSession()
    //   .subscribe(data => this.flag = data,
    //   dataError => this.errorMsg = dataError);
    // if (this.flag === true) {
    //   console.log(this.flag);
    // } else if (this.flag === false) {
    //   console.log('this is from authguard : ' + this.flag);
    //   this.route.navigate(['/login']);
    // }
    // return this.flag;
}
}
  // canActivate(route: ActivatedRouteSnapshot,
  //             state: RouterStateSnapshot): Observable <boolean> {
  //   return this._empService.authInfo$
  //     .map(authInfo => authInfo.isLoggedIn())
  //     .take(1)
  //     .do(allowed => {
  //         if (!allowed) {
  //           this.route.navigate(['login']);
  //         }
  //     });
  // }

