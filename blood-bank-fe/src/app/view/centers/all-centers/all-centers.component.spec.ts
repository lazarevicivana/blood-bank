import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCentersComponent } from './all-centers.component';

describe('AllCentersComponent', () => {
  let component: AllCentersComponent;
  let fixture: ComponentFixture<AllCentersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllCentersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllCentersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
