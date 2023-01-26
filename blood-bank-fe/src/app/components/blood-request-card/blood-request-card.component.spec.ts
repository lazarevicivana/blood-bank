import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodRequestCardComponent } from './blood-request-card.component';

describe('BloodRequestCardComponent', () => {
  let component: BloodRequestCardComponent;
  let fixture: ComponentFixture<BloodRequestCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodRequestCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodRequestCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
