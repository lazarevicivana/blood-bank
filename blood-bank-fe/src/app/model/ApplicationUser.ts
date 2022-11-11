export interface ApplicationUser {
  id: string,
  username: string,
  password: string,
  name: string,
  surname: string,
  phone: string,
  jmbg: string,
  email: string,
  userRole: string,
  street: string;
  country: string;
  city:string;
  streetNumber: string;
  enabled: boolean,
  deleted: boolean,
  profession: {
    id: string,
    professionStatus: string,
    professionDescription: string
  },
  gender: string
}
