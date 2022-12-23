export class CenterEquipment {
  equipmentName?: string;
  quantity?: boolean;

  constructor(equipmentName: string, quantity: boolean) {
    this.equipmentName = equipmentName;
    this.quantity = quantity;
  }
}
