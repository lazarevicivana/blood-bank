import {BloodUnit} from "../BloodUnit";

export class BloodRequest{
  public id?:number;
  public bloodUnits?:BloodUnit[];
  public deliveryDate?:Date;
  public hospitalName?:String;
  public price?:Number;
}
