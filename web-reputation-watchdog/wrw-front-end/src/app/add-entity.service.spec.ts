import { TestBed } from '@angular/core/testing';

import { AddEntityService } from './add-entity.service';

describe('AddEntityService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddEntityService = TestBed.get(AddEntityService);
    expect(service).toBeTruthy();
  });
});
