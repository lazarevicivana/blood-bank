export interface ICenterAdministrator {
  id?: string;
  username?: string;
  name?: string;
  surname?: string;
  phone?: string;
  jmbg?: string;
  email?: string;
  gender?: string;
  address?: {
    id?: string;
    city?: string;
    street?: string;
    country?: string;
    streetNumber?: string;
  };
  enabled?: boolean;
  deleted?: boolean;
  userRole?: string;
}
export class CenterAdministrator implements ICenterAdministrator {
  id?: string;
  username?: string;
  name?: string;
  surname?: string;
  phone?: string;
  jmbg?: string;
  email?: string;
  gender?: string;
  address?: {
    id?: string;
    city?: string;
    street?: string;
    country?: string;
    streetNumber?: string;
  }
  enabled?: boolean;
  deleted?: boolean;
  userRole?: string;
  constructor(data?: ICenterAdministrator){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }

  }

}
