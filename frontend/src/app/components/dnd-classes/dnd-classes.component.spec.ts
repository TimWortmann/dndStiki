import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DndClassesComponent } from './dnd-classes.component';

describe('DnDClassesComponent', () => {
  let component: DndClassesComponent;
  let fixture: ComponentFixture<DndClassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DndClassesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DndClassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
