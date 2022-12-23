export interface ISystemAdministrator {
  id?: string;
  username?: string;
  password?:string;
  name?: string;
  surname?: string;
  phone?: string;
  jmbg?: string;
  email?: string;
  city?: string;
  street?: string;
  country?: string;
  streetNumber?: string;
  enabled?: boolean;
  deleted?: boolean;
  role?: string;
  gender?: string;
  firstLogIn?:boolean;
}
export class SystemAdministrator implements ISystemAdministrator {
  id?: string;
  username?: string;
  name?: string;
  surname?: string;
  phone?: string;
  jmbg?: string;
  email?: string;
  gender?: string;
  city?: string;
  street?: string;
  country?: string;
  streetNumber?: string;
  enabled?: boolean = true;
  deleted?: boolean = false;
  role?: string = "ROLE_SYSTEM_ADMIN";
  password?:string;
  firstLogIn?:boolean;
  constructor(data?: ISystemAdministrator){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }

  }

}
