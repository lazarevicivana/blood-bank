import {ICustomerRequest} from "./CustomerRequest";


export interface IDonorSearchRequest {
  searchName?: string;
  searchSurname?: string;
  centerId?: string;
}
export class DonorSearchRequest implements IDonorSearchRequest {
  searchName?: string;
  searchSurname?: string;
  centerId?: string;
  constructor(data?: IDonorSearchRequest){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
