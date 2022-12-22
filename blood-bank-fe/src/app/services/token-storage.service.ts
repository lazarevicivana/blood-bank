import { Injectable } from '@angular/core';
import {User} from "../model/User";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  signOut(): void {
    window.localStorage.clear();
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.removeItem(TOKEN_KEY);
  }
  public isLoggedIn():boolean{
    return !!window.localStorage.getItem(TOKEN_KEY);
  }
  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
  public saveUser(token: string): void {
    let user:string = atob(token.split('.')[1]);
    let userObject = JSON.parse(user)
    let userTk:User = new User(userObject.id,userObject.role);
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, JSON.stringify(userTk));
  }
  public getUser(): User {
    const user = window.localStorage.getItem(USER_KEY);
    if (user) {
      console.log(user)
      return JSON.parse(user);
    }
    return new User("","");
  }
}
