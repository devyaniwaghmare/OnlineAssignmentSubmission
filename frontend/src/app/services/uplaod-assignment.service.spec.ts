import { TestBed } from '@angular/core/testing';

import { UplaodAssignmentService } from './uplaod-assignment.service';

describe('UplaodAssignmentService', () => {
  let service: UplaodAssignmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UplaodAssignmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
