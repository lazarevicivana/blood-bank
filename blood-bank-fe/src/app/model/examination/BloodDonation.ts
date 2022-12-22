export class BloodDonation {
  bloodType?: string;
  noteForDoctor?: string;
  bloodUnit?: number;

  constructor(bloodType: string, noteForDoctor: string, bloodUnit: number) {
    this.bloodType = bloodType;
    this.noteForDoctor = noteForDoctor;
    this.bloodUnit = bloodUnit;
  }
}
