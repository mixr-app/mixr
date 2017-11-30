import { Component, OnInit } from '@angular/core';
import { Ingredient } from "../models/Ingredient";
import { RecipeToCreate, RecipeIngredientsToAdd } from "../models/RecipeToCreate";
import { DrinkService } from '../services/drink.service';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html'
})
export class AddRecipeComponent implements OnInit {

  recipeToCreate = this.createBlankNewRecipe();
  createdRecipe = null;

  ingredients: Ingredient[] = [];

  constructor(private drinkService: DrinkService) { }

  ngOnInit() {
    this.drinkService.findIngredients().then(ingredients => this.ingredients = ingredients);
  }

  doAddRow() {
    this.recipeToCreate.ingredients.push({ ingredientId: -1, amount: 0, unit: "oz" });
  }

  doDeleteRow(index: number){
    this.recipeToCreate.ingredients.splice(index, 1);
  }

  doCreateRecipe() {
    this.createdRecipe = null;
    this.drinkService.createRecipe(this.recipeToCreate).then(recipe => {
      this.createdRecipe = recipe;
      this.recipeToCreate = this.createBlankNewRecipe();
    });
  }

  createBlankNewRecipe(): RecipeToCreate {
    return {
        name: "",
        description: "",
        instructions: "",
        ingredients: [
            { ingredientId: -1, amount: 0, unit: "oz" },
            { ingredientId: -1, amount: 0, unit: "oz" },
            { ingredientId: -1, amount: 0, unit: "oz" },
        ]
    }
    
}

}
