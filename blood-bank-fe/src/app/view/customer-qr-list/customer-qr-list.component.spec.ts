import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerQrListComponent } from './customer-qr-list.component';

describe('CustomerQrListComponent', () => {
  let component: CustomerQrListComponent;
  let fixture: ComponentFixture<CustomerQrListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerQrListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerQrListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
