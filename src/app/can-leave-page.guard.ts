import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CheckBeforeLeavingPage } from './CheckBeforeLeavingPage';

@Injectable({
  providedIn: 'root'
})
export class CanLeavePageGuard implements CanDeactivate<CheckBeforeLeavingPage> {
  canDeactivate(
    component: CheckBeforeLeavingPage,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return component.canClosePage();
  }

}
