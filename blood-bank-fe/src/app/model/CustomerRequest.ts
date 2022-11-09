import {AddressRequest} from "./AddressRequest";
import {ProfessionRequest} from "./ProfessionRequest";

export interface ICustomerRequest {
  name?: string;
  username?: string;
  password?: string;
  surname?: string,
  phone?: string,
  jmbg?: string,
  email?: string,
  role?: string,
  address?: AddressRequest,
  profession? : ProfessionRequest
}
export class CustomerRequest implements ICustomerRequest {
  address?: AddressRequest;
  email?: string;
  jmbg?: string;
  name?: string;
  password?: string;
  phone?: string;
  surname?: string;
  role?: string;
  username?: string;
  profession? : ProfessionRequest;
  constructor(data?: ICustomerRequest){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
