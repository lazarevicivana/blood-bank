import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {ScheduleAppointmentService} from "../../services/schedule-appointment.service";
import {QuestionnaireService} from "../../services/customer-form.service";
import {ScheduleAppointmentEx} from "../../model/examination/ScheduleAppointmentEx";
import {ToastrService} from "ngx-toastr";
import {PatientValidDonor} from "../../model/examination/PatientValidDonor";
import {CenterEquipmentService} from "../../services/center-equipment.service";
import {Equipment} from "../../model/Equipment";
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import {MatStepper} from "@angular/material/stepper";
import {BloodDonation} from "../../model/examination/BloodDonation";
import {CenterEquipment} from "../../model/examination/CenterEquipment";
import {Examination} from "../../model/examination/Examination";
import {ExaminationService} from "../../services/examination.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-examination',
  templateUrl: './examination.component.html',
  styleUrls: ['./examination.component.css']
})
export class ExaminationComponent implements OnInit {
  private scheduledAppointmentId = 'cb8d11b8-62a9-11ed-9b6a-0242ac120002'
  formControlAppeared =  new FormControl<string>('',Validators.required);
  formControlValidDonor =  new FormControl<string>('',Validators.required);
  formControlEquipments =  new FormControl<string[]>([],Validators.required);
  scheduleApp:ScheduleAppointmentEx = new ScheduleAppointmentEx();
  formAnswers:PatientValidDonor[] = []
  equipments:Equipment[] = []
  submitedEquipment:boolean = false
  selectedEquipments:string[] = []
  formEquipmentQuantity: FormGroup;
  formBloodDonation: FormGroup;
  stepper: MatStepper | undefined;
  validDonor:boolean = false
  isAppeared:boolean = false;
  isSuitable:boolean = false;
  centerEquipments:CenterEquipment[] = []
  bloodDonation:BloodDonation = new BloodDonation("","",0)
  constructor(private readonly scheduleAppointmentService:ScheduleAppointmentService
              ,private readonly questionnaireService:QuestionnaireService
              ,private toast: ToastrService,private readonly centerEquipmentService:CenterEquipmentService
              ,private fb: FormBuilder,private readonly examinationService:ExaminationService,private router:Router)
  {
    this.formEquipmentQuantity = this.fb.group(
      {inputs: this.fb.array([],Validators.required)
    });
    this.formBloodDonation = this.fb.group(
      {
        bloodType: new FormControl<string>('',Validators.required),
        noteForDoctor:new FormControl<string>('',Validators.required),
        bloodUnit:new FormControl<number>(0,Validators.required)
      }
    )
  }

  ngOnInit(): void {
    this.GetAppointmentAndPatientForm()
  }
  get inputs(): FormArray {
    return this.formEquipmentQuantity.get('inputs') as FormArray;
  }
  private GetAppointmentAndPatientForm(){
    this.scheduleAppointmentService
      .getAppointmentForExamination(this.scheduledAppointmentId)
      .subscribe({
        next: response =>{
          this.scheduleApp = response
          this.CheckDonation(this.scheduleApp.customerId!)
          this.GetCenterEquipment(this.scheduleApp.centerId!)
          console.log(this.scheduleApp)
        },
        error: err => {
          this.toast.error(err.error.message,"Error")
        }
      })
  }
  IsValidDonor():string{
    let message = "Our system think that patient is valid blood donor."
    this.formAnswers.forEach((x)=>{
      if(!x.isValidDonor){
        message = "Our system think that patient is not valid blood donor."
        this.validDonor = false
      }
    })
    if(Array.isArray(this.formAnswers) && this.formAnswers.length === 0){
      message = "Patient doesnt have questionnaire"
    }
    return message
  }
  private GetCenterEquipment(centerId:string){
    this.centerEquipmentService.getCentersEquipment(centerId)
      .subscribe({
        next: response =>{
          this.equipments = response
          console.log(this.equipments)
        }
      })
  }
  private CheckDonation(customerId:string){
    this.questionnaireService
      .checkIfPatientSuitableBloodDonor(customerId)
      .subscribe({
        next: response =>{
          this.formAnswers = response
          console.log(this.formAnswers)
        },
        error: err => {
          this.toast.error(err.error.message,"Error")
        }
      })
  }
  showQuantity() {
    this.submitedEquipment = true
    this.selectedEquipments = this.formControlEquipments.value!
    console.log(this.selectedEquipments)
    this.selectedEquipments.forEach(()=>{
      const inputs = this.formEquipmentQuantity.get('inputs') as FormArray;
      inputs.push(this.fb.control(''));
    })
  }
  submitEquipment() {
    let eqQuantity= this.inputs.controls.map(control => control.value);
    console.log(eqQuantity);
    eqQuantity.forEach((val,index)=>{
      let eq = new CenterEquipment(this.selectedEquipments[index],val)
      this.centerEquipments.push(eq)
    })
  }
  nextBloodDonation(stepper:MatStepper) {
    this.stepper = stepper
    let bloodType = this.formBloodDonation.controls['bloodType'].value!
    let noteForDoctor = this.formBloodDonation.controls['noteForDoctor'].value!
    let bloodUnit = this.formBloodDonation.controls['bloodUnit'].value!
    this.bloodDonation = new BloodDonation(bloodType,noteForDoctor,bloodUnit)
    this.stepper.next()
  }
  nextEquipmentQuantity(stepper:MatStepper){
    this.stepper = stepper;
    this.stepper.next();
    this.submitEquipment()
  }
  deleteInputs(event:any) {
    event.preventDefault()
    this.submitedEquipment = false
    this.formEquipmentQuantity = this.fb.group(
      {inputs: this.fb.array([],Validators.required)
      });
  }
  stringToBoolean(answer : string){
    return answer === 'yes';
  }
  appearedNext(stepper:MatStepper) {
    this.stepper = stepper;
    this.isAppeared = this.stringToBoolean(this.formControlAppeared.value!)
    this.stepper.next()
  }
  isSuitableNext(stepper:MatStepper){
    this.stepper = stepper;
    this.isSuitable = this.stringToBoolean(this.formControlValidDonor.value!)
    this.stepper.next()
  }
  private validateBooleans(){
    if(this.formControlAppeared.value!){
      return true
    }
    return !!(this.formControlValidDonor.value!);
  }
  finishExamination() {
    if(!this.validateBooleans()){
      this.toast.error("Please fullyfill fields","Error")
      return
    }
    const ex = new Examination(this.bloodDonation,this.centerEquipments,this.isSuitable,this.isAppeared,this.scheduledAppointmentId)
    console.log(ex);
    this.examinationService.createExamination(ex).subscribe({
      next: () =>{
        console.log("Success")
        this.router.navigate(['admin-center-profile']).then(()=>{
          this.toast.success("You successfully examined appointment","Success")
        })
      },
      error: err => {
        this.toast.error(err.error.message,"Error")
      }
    })
    console.log(this.centerEquipments)

  }
}
