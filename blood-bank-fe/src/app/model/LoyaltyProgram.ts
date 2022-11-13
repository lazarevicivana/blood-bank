export interface LoyaltyProgram {
  id: string,
  loyaltyProgram: {
    id?: string,
    customerCategory: string,
    numberOfPoints: string,
    loyaltyConvenience: string
  },
  currentPoints: string,
  // customer: {
  //   id: string,
  //   username: string,
  //   password: string,
  //   name: string,
  //   surname: string,
  //   phone: string,
  //   jmbg: string,
  //   email: string,
  //   locked: boolean,
  //   gender: string,
  //   userRole: string,
  //   address: {
  //     id: string,
  //     city: string,
  //     street: string,
  //     country: string,
  //     streetNumber: string
  //   },
  //   enabled: boolean,
  //   deleted: boolean,
  //   profession: {
  //     id: "3efbe2a3-645a-4d69-974a-3dfdb06913eb",
  //     professionStatus: "STUDENT",
  //     professionDescription: "Thoughtbridge"
  //   },
  //   penalty: 0,
  //   complains: [],
  //   customerForms: [],
  //   accountNonExpired: true,
  //   credentialsNonExpired: true,
  //   authorities: [
  //     {
  //       "authority": "ROLE_CUSTOMER"
  //     }
  //   ],
  //   accountNonLocked: true
  //}
}
