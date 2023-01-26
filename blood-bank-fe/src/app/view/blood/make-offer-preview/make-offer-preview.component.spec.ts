import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeOfferPreviewComponent } from './make-offer-preview.component';

describe('MakeOfferPreviewComponent', () => {
  let component: MakeOfferPreviewComponent;
  let fixture: ComponentFixture<MakeOfferPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeOfferPreviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MakeOfferPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
