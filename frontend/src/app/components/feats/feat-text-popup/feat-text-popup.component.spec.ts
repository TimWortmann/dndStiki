import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeatTextPopupComponent } from './feat-text-popup.component';

describe('FeatTextPopupComponent', () => {
  let component: FeatTextPopupComponent;
  let fixture: ComponentFixture<FeatTextPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FeatTextPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeatTextPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
