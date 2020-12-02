import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllentriesComponent } from './allentries.component';

describe('AllentriesComponent', () => {
  let component: AllentriesComponent;
  let fixture: ComponentFixture<AllentriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllentriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllentriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
