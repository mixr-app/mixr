import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../models/Ingredient';
import { DrinkService } from '../services/drink.service';

@Component({
  selector: 'app-browse-ingredient',
  templateUrl: './browse-ingredient.component.html',
  styleUrls: ['./browse-ingredient.component.css']
})
export class BrowseIngredientComponent implements OnInit {

  ingredients: Ingredient[] = [];

  constructor(private drinkService: DrinkService) { }

  ngOnInit() {
    this.drinkService.findIngredients().then(ingredients => {
      this.ingredients = ingredients;
    });
  }

}
