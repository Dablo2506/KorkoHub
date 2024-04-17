import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddedTutorAdsComponent } from './added-tutor-ads.component';

describe('AddedTutorAdsComponent', () => {
  let component: AddedTutorAdsComponent;
  let fixture: ComponentFixture<AddedTutorAdsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddedTutorAdsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddedTutorAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
