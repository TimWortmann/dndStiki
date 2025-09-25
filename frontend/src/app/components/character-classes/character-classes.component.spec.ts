import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CharacterClassesComponent } from './character-classes.component';

describe('CharacterClassesComponent', () => {
  let component: CharacterClassesComponent;
  let fixture: ComponentFixture<CharacterClassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CharacterClassesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CharacterClassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
