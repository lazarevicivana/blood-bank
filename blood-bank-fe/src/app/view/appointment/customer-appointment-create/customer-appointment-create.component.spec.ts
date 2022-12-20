import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAppointmentCreateComponent } from './customer-appointment-create.component';

describe('CustomerAppointmentCreateComponent', () => {
  let component: CustomerAppointmentCreateComponent;
  let fixture: ComponentFixture<CustomerAppointmentCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAppointmentCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerAppointmentCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
