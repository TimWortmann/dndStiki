import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArmorClassPopupComponent } from './armor-class-popup.component';

describe('ArmorClassPopupComponent', () => {
  let component: ArmorClassPopupComponent;
  let fixture: ComponentFixture<ArmorClassPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArmorClassPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArmorClassPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
