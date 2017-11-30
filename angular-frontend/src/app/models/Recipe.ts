import { Ingredient } from "./Ingredient";

export class Recipe {
    id: number;
    name: String;
    description: String;
    instructions: String;
    ingredients: Ingredient[];
}