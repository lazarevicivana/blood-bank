import {BloodUnit} from "./BloodUnit";

export class BloodContract{
  public id?:string;
  public bloodUnits?:BloodUnit[];
  public dateOfDelivery?:Date;
  public bloodBankName?:String;
  public hospitalName?:String;
  public price?:number;
}
