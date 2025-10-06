import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassDetailsPopupComponent } from './class-details-popup.component';

describe('ClassDetailsPopupComponent', () => {
  let component: ClassDetailsPopupComponent;
  let fixture: ComponentFixture<ClassDetailsPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClassDetailsPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClassDetailsPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
