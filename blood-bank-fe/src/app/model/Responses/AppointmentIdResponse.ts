export interface IAppointmentIdResponse{
  id?: string,

}
export class AppointmentIdResponse implements IAppointmentIdResponse {
  id?: string;

  constructor(data?: IAppointmentIdResponse){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }

  }

}
