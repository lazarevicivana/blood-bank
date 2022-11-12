import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterDonorsComponent } from './center-donors.component';

describe('CenterDonorsComponent', () => {
  let component: CenterDonorsComponent;
  let fixture: ComponentFixture<CenterDonorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CenterDonorsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CenterDonorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
