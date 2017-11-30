import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseIngredientComponent } from './browse-ingredient.component';

describe('BrowseIngredientComponent', () => {
  let component: BrowseIngredientComponent;
  let fixture: ComponentFixture<BrowseIngredientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrowseIngredientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseIngredientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
