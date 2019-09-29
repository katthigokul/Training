import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityAnalyticsComponent } from './entity-analytics.component';

describe('EntityAnalyticsComponent', () => {
  let component: EntityAnalyticsComponent;
  let fixture: ComponentFixture<EntityAnalyticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EntityAnalyticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EntityAnalyticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
