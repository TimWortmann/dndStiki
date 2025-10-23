import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DownloadsPopupComponent } from './downloads-popup.component';

describe('DownloadsPopupComponent', () => {
  let component: DownloadsPopupComponent;
  let fixture: ComponentFixture<DownloadsPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DownloadsPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DownloadsPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
