import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeeSubmissionComponent } from './see-submission.component';

describe('SeeSubmissionComponent', () => {
  let component: SeeSubmissionComponent;
  let fixture: ComponentFixture<SeeSubmissionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeeSubmissionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeeSubmissionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
