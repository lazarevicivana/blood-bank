import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnotherAdminDialogComponent } from './another-admin-dialog.component';

describe('AnotherAdminDialogComponent', () => {
  let component: AnotherAdminDialogComponent;
  let fixture: ComponentFixture<AnotherAdminDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnotherAdminDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnotherAdminDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
