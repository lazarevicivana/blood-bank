import {ICenter} from "./Center";

export interface IUserResponse{
  id?: string,
  username?: string,
  name?: string,
  surname?: string,
  phone?: string,
  email?: string,
  address?: {
    id: string,
    city: string,
    street: string,
    country: string,
    streetNumber: string
  },
  gender?: string
  role?:string
}
export class UserResponse implements IUserResponse {
  id?: string;
  username?: string;
  name?: string;
  surname?: string;
  phone?: string;
  email?: string;
  address!: {
    id: string,
    city: string,
    street: string,
    country: string,
    streetNumber: string
  };
  gender?: string;
  role?:string;
  constructor(data?: IUserResponse){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }

  }

}
