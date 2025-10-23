import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemListPopupComponent } from './item-list-popup.component';

describe('ItemListPopupComponent', () => {
  let component: ItemListPopupComponent;
  let fixture: ComponentFixture<ItemListPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ItemListPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemListPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
