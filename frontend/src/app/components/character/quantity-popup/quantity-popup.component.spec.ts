import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuantityPopupComponent } from './quantity-popup.component';

describe('QuantityPopupComponent', () => {
  let component: QuantityPopupComponent;
  let fixture: ComponentFixture<QuantityPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuantityPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuantityPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
