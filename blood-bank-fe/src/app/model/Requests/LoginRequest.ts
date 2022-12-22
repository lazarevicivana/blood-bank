export interface ILoginRequest {
  username?: string;
  password?: string;
}
export class LoginRequest implements ILoginRequest {
  password?: string;
  username?: string;
  constructor(data?: ILoginRequest){
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
