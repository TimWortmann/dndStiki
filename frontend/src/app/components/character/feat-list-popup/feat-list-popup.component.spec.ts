import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeatListPopupComponent } from './feat-list-popup.component';

describe('FeatListPopupComponent', () => {
  let component: FeatListPopupComponent;
  let fixture: ComponentFixture<FeatListPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FeatListPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeatListPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
