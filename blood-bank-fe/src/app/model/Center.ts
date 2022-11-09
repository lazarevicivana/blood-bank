export interface ICenter {
  id?: string;
  name?: string;
  description?: string;
  avgGrade?: number;
  street?: string;
  country?: string;
  streetNumber?: string;
  longitude?: number;
  latitude?: number;

}
export class Center implements ICenter {
  id?: string;
  name?: string;
  description?: string;
  avgGrade?: number;
  street?: string;
  country?: string;
  streetNumber?: string;
  longitude!: number;
  latitude!: number;
  constructor(data?: ICenter){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }

  }

}
