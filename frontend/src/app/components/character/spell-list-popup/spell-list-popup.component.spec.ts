import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellListPopupComponent } from './spell-list-popup.component';

describe('SpellListPopupComponent', () => {
  let component: SpellListPopupComponent;
  let fixture: ComponentFixture<SpellListPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SpellListPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpellListPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
