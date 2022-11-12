export interface ApplicationUser {
  id?: string,
  username?: string,
  password?: string,
  name?: string,
  surname?: string,
  phone?: string,
  jmbg?: string,
  email?: string,
  userRole?: string,
  street?: string;
  country?: string;
  city?:string;
  streetNumber?: string;
  enabled?: boolean,
  deleted?: boolean,
  profession?: {
    id?: string,
    professionStatus?: string,
    professionDescription?: string
  },
  gender?: string
}
export class ApplicationUserImp implements ApplicationUser {
  id?: string;
  username?: string;
  password?: string;
  name?: string;
  surname?: string;
  phone?: string;
  jmbg?: string;
  email?: string;
  userRole?: string;
  street?: string;
  country?: string;
  city?:string;
  streetNumber?: string;
  enabled?: boolean;
  deleted?: boolean;
  profession?: {
    id: string;
    professionStatus: string;
    professionDescription: string;
  };
  gender?: string;

  constructor(data?: ApplicationUser){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
