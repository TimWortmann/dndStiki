import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellDetailsPopupComponent } from './spell-details-popup.component';

describe('SpellDetailsPopupComponent', () => {
  let component: SpellDetailsPopupComponent;
  let fixture: ComponentFixture<SpellDetailsPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SpellDetailsPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpellDetailsPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
