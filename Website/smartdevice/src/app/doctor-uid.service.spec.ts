import { TestBed, inject } from '@angular/core/testing';

import { DoctorUIDService } from './doctor-uid.service';

describe('DoctorUIDService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DoctorUIDService]
    });
  });

  it('should be created', inject([DoctorUIDService], (service: DoctorUIDService) => {
    expect(service).toBeTruthy();
  }));
});
