import {ApplicationUser, ApplicationUserImp} from "./ApplicationUser";

export class UserToken {
  jwt:string ='';
  user: ApplicationUserImp | undefined

  constructor(jwt: string,us:ApplicationUserImp ) {
    this.jwt = jwt;
    this.user = us
  }
}
