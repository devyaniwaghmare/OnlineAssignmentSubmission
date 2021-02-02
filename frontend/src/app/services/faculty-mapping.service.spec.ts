import { TestBed } from '@angular/core/testing';

import { FacultyMappingService } from './faculty-mapping.service';

describe('FacultyMappingService', () => {
  let service: FacultyMappingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FacultyMappingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
