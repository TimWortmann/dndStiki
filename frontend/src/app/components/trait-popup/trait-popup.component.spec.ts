import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TraitPopupComponent } from './trait-popup.component';

describe('TraitPopupComponent', () => {
  let component: TraitPopupComponent;
  let fixture: ComponentFixture<TraitPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TraitPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TraitPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
