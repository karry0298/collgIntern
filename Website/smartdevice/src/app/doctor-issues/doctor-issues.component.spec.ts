import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorIssuesComponent } from './doctor-issues.component';

describe('DoctorIssuesComponent', () => {
  let component: DoctorIssuesComponent;
  let fixture: ComponentFixture<DoctorIssuesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorIssuesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorIssuesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
