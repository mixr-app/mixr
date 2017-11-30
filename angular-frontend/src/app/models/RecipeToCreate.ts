export class RecipeToCreate {
    name: String;
    description: String;
    instructions: String;
    ingredients: RecipeIngredientsToAdd[]
}

export class RecipeIngredientsToAdd {
    ingredientId: number;
    amount: number;
    unit: string;
}

