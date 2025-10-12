import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointBuyPopupComponent } from './point-buy-popup.component';

describe('PointBuyPopupComponent', () => {
  let component: PointBuyPopupComponent;
  let fixture: ComponentFixture<PointBuyPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PointBuyPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PointBuyPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
