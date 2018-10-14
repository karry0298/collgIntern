import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorPatientviewComponent } from './doctor-patientview.component';

describe('DoctorPatientviewComponent', () => {
  let component: DoctorPatientviewComponent;
  let fixture: ComponentFixture<DoctorPatientviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorPatientviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorPatientviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
