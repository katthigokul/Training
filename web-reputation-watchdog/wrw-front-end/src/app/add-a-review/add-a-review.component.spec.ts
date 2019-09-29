import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAReviewComponent } from './add-a-review.component';

describe('AddAReviewComponent', () => {
  let component: AddAReviewComponent;
  let fixture: ComponentFixture<AddAReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
