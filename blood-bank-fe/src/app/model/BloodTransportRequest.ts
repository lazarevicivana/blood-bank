import {AddressRequest} from "./Requests/AddressRequest";
import {ProfessionRequest} from "./Requests/ProfessionRequest";

export interface IBloodTransportRequest {
  id?: string;

}
export class BloodTransportRequest implements IBloodTransportRequest {
  id?: string;

  constructor(data?: IBloodTransportRequest){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
