import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovierecommendComponent } from './movierecommend.component';

describe('MovierecommendComponent', () => {
  let component: MovierecommendComponent;
  let fixture: ComponentFixture<MovierecommendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovierecommendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovierecommendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
