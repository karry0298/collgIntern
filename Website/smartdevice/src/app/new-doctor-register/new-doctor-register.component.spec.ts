import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDoctorRegisterComponent } from './new-doctor-register.component';

describe('NewDoctorRegisterComponent', () => {
  let component: NewDoctorRegisterComponent;
  let fixture: ComponentFixture<NewDoctorRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewDoctorRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDoctorRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
