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
  address: {
    id: string,
    city: string,
    street: string,
    country: string,
    streetNumber: string
  },
  enabled: boolean,
  deleted: boolean,
  profession: {
    id: string,
    professionStatus: string,
    professionDescription: string
  },
  gender: string
}
