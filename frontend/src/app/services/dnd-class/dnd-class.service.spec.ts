import { TestBed } from '@angular/core/testing';

import { DndClassService } from './dnd-class.service';

describe('DndClassService', () => {
  let service: DndClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DndClassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
