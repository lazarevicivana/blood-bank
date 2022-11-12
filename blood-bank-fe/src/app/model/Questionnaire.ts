import {ICustomerRequest} from "./CustomerRequest";
import {ApplicationUser} from "./ApplicationUser";

export interface IQuestionnaireRequest{
  isAge?: boolean,
  isWeight?: boolean,
  isSexual?: boolean,
  isPregnant?: boolean,
  onPeriod?: boolean,
  isSick? : boolean,
  useMedication? : boolean,
  isAllergic? : boolean,
  hadCancer? : boolean,
  hadTransfusion? : boolean,
  customerId? : string,
  submissionDate? : Date
}
export class QuestionnaireRequest implements IQuestionnaireRequest {
  isAge?: boolean;
  isSexual?: boolean;
  isWeight?: boolean;
  isPregnant?: boolean;
  onPeriod?: boolean;
  isSick? : boolean;
  useMedication? : boolean;
  isAllergic? : boolean;
  hadCancer? : boolean;
  hadTransfusion? : boolean;
  customerId? : string;
  submissionDate? : Date;


  constructor(data?: ICustomerRequest){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
