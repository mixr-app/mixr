import { Injectable } from '@angular/core';
import { Ingredient } from '../models/Ingredient'
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { RecipeToCreate } from '../models/RecipeToCreate'

@Injectable()
export class DrinkService {

  baseUrl = "http://localhost:8080/";
  // baseUrl = "https://mixr.club/api/";


  constructor(private http: Http) { }

  findIngredients(): Promise<Ingredient[]> {
    return this.http.get(this.baseUrl + "ingredients")
      .toPromise()
      .then(response => response.json() as Ingredient[])
      .catch(this.handleError);
  }

  findRecipes() {
    return this.http.get(this.baseUrl + "recipes")
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  findRecipesWithIngredients(ingredients) {
    return this.http.get(this.baseUrl + "recipes/?ids=" + ingredients.join(",") + "&threshold=1")
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  createRecipe(recipeToCreate: RecipeToCreate){
    let headers = new Headers()
    headers.set("authorization", "Basic am9lOnBhc3N3b3Jk");
    return this.http.post(this.baseUrl + "recipes", recipeToCreate, {
      headers: headers
    })
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  createIngredient(ingredientToCreate): Promise<Ingredient> {
    let headers = new Headers()
    headers.set("authorization", "Basic am9lOnBhc3N3b3Jk");
    return this.http.post(this.baseUrl + "ingredients", ingredientToCreate, {
      headers: headers
    })
      .toPromise()
      .then(response => response.json() as Ingredient)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
