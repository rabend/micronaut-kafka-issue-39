import { TestBed, inject } from '@angular/core/testing';

import { BackendClientService } from './backend-client.service';

describe('BackendClientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BackendClientService]
    });
  });

  it('should be created', inject([BackendClientService], (service: BackendClientService) => {
    expect(service).toBeTruthy();
  }));
});
