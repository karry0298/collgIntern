import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorMedicationsComponent } from './doctor-medications.component';

describe('DoctorMedicationsComponent', () => {
  let component: DoctorMedicationsComponent;
  let fixture: ComponentFixture<DoctorMedicationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorMedicationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorMedicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
