export interface IAddressRequest {
    city?: string,
    street?: string,
    country?: string,
    streetNumber?: string
  }

export class AddressRequest implements IAddressRequest {
  city?: string;
  street?: string;
  country?: string;
  streetNumber?: string;
  constructor(data?: IAddressRequest) {
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }

  }
}
