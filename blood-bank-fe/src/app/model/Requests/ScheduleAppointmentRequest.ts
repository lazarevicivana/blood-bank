export interface IScheduleAppointmentRequest {
  appointment_id?: string;
  customer_id?: string;
}
export class ScheduleAppointmentRequest implements IScheduleAppointmentRequest {
  appointment_id?: string;
  customer_id?: string;
  constructor(data?: IScheduleAppointmentRequest){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
