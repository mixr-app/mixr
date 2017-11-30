import { Component, OnInit } from '@angular/core';
import { DrinkService } from '../services/drink.service';
import { Ingredient } from '../models/Ingredient';

@Component({
  selector: 'app-create-ingredient',
  templateUrl: './create-ingredient.component.html'
})
export class CreateIngredientComponent implements OnInit {

  ingredient = this.getEmptyIngredient();
  ingredientNameTaken = false;

  existingIngredients: Ingredient[];

  ingredientTypes = [
    {value: "LIQUOR", name: "Liquor"},
    {value: "MIXER", name: "Mixer"},
    {value: "OTHER", name: "Other"},
  ]

  createdIngredient: Ingredient = null;

  constructor(private drinkService: DrinkService) { }

  ngOnInit() {
    this.drinkService.findIngredients().then(ingredients => this.existingIngredients = ingredients);
  }

  doCreateIngredient() {
    this.createdIngredient = null
    this.drinkService.createIngredient(this.ingredient).then(newIngredient => {
      this.createdIngredient = newIngredient
      this.ingredient = this.getEmptyIngredient();
    });
  }

  doNameChange() {
    this.ingredientNameTaken = this.existingIngredients.filter(ing => ing.name.toLowerCase() == this.ingredient.name.toLowerCase()).length > 0;
  }

  getEmptyIngredient() {
    return {
      name: "",
      description: "",
      ingredientType: ""
    }
  }
}
