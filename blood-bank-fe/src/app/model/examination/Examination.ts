import {BloodDonation} from "./BloodDonation";
import {CenterEquipment} from "./CenterEquipment";

export class Examination {
  bloodDonation:BloodDonation;
  centerEquipments:CenterEquipment[];
  isSuitableBloodDonor:boolean;
  isAppeared:boolean;
  appointmentId:string;
  constructor(bloodDonation: BloodDonation, centerEquipments: CenterEquipment[], isSuitableBloodDonor: boolean, isAppeared: boolean, appointmentId: string) {
    this.bloodDonation = bloodDonation;
    this.centerEquipments = centerEquipments;
    this.isSuitableBloodDonor = isSuitableBloodDonor;
    this.isAppeared = isAppeared;
    this.appointmentId = appointmentId;
  }
}
