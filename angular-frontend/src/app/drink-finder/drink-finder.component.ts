import { Component, OnInit } from '@angular/core';

import { DrinkService } from '../services/drink.service';

@Component({
  templateUrl: './drink-finder.component.html'
})
export class DrinkFinderComponent implements OnInit {

  // maps ingredient id to either true or false
  checkedIngredients = {};

  ingredients = []
  ingredientMap = {};

  recipes = [];

  constructor(private drinkService: DrinkService) { }

  ngOnInit() {
    this.drinkService.findIngredients().then(ingredients => {
      this.ingredients = ingredients;
      ingredients.forEach(ingredient => this.checkedIngredients[ingredient.id] = false);
      ingredients.forEach(ingredient => this.ingredientMap[ingredient.id] = ingredient);
    });
    let selected = Object.keys(this.checkedIngredients).filter(key => this.checkedIngredients[key]);
    this.fetchRecipes(selected);
  }

  doCheck(ingredientId: Number) {
    this.checkedIngredients[String(ingredientId)] = !this.checkedIngredients[String(ingredientId)];
    // keep only the selected IDs
    let selected = Object.keys(this.checkedIngredients).filter(key => this.checkedIngredients[key]);
    this.fetchRecipes(selected);
  }

  fetchRecipes(selectedIngredients){
    if (selectedIngredients.length > 0){
      this.drinkService.findRecipesWithIngredients(selectedIngredients).then(recipes => this.recipes = recipes);
    } else {
      this.drinkService.findRecipes().then(recipes => this.recipes = recipes);
    }
  }

}
