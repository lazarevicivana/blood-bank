export interface IMedicalStaffResponse{
  id?: string,
  username?: string,
  name?: string,
  surname?: string,
  email?: string,
}
export class MedicalStaffResponse implements IMedicalStaffResponse {
  email?: string;
  id?: string;
  name?: string;
  surname?: string;
  username?: string;

  constructor() {
  }
}
