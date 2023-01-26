export interface ICoordinate{
  longitude?: number;
  latitude?: number;

}
export class Coordinate implements ICoordinate {
  longitude?: number;
  latitude?: number;

  constructor(data?: ICoordinate){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
