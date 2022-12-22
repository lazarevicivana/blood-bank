export class PatientValidDonor {
  reason?: string;
  isValidDonor?: boolean;

  constructor(data?: PatientValidDonor) {
    if (data) {
      for (const property in data) {
        if (data.hasOwnProperty(property))
          (<any>this)[property] = (<any>data)[property];
      }
    }
  }
}
