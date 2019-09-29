import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendationCardComponentComponent } from './recommendation-card-component.component';

describe('RecommendationCardComponentComponent', () => {
  let component: RecommendationCardComponentComponent;
  let fixture: ComponentFixture<RecommendationCardComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommendationCardComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendationCardComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
