export interface IProfessionRequest {
  professionStatus?: string,
  professionDescription?: string
}

export class ProfessionRequest implements IProfessionRequest {
  professionStatus?: string;
  professionDescription?: string
  constructor(data?: IProfessionRequest) {
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }

  }
}
