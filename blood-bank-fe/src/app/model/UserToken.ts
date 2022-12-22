import {ApplicationUser, ApplicationUserImp} from "./ApplicationUser";

export class UserToken {
  jwt:string ='';

  constructor(jwt: string,us:ApplicationUserImp ) {
    this.jwt = jwt;
  }
}
