import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { RouterModule }   from '@angular/router';
import { HttpModule }    from '@angular/http';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SampleListComponent } from './sample-list/sample-list.component';
import { DrinkFinderComponent } from './drink-finder/drink-finder.component';
import { DrinkService } from './services/drink.service';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { BrowseIngredientComponent } from './browse-ingredient/browse-ingredient.component';
import { CreateIngredientComponent } from './create-ingredient/create-ingredient.component';
import { SingleRecipeComponent } from './single-recipe/single-recipe.component';
import { BrowseRecipesComponent } from './browse-recipes/browse-recipes.component'

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SampleListComponent,
    DrinkFinderComponent,
    AddRecipeComponent,
    BrowseIngredientComponent,
    CreateIngredientComponent,
    SingleRecipeComponent,
    BrowseRecipesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      { path: 'sample', component: SampleListComponent },
      { path: 'recipes/finder', component: DrinkFinderComponent },
      { path: 'recipes/browse', component: BrowseRecipesComponent },
      { path: 'recipes/create', component: AddRecipeComponent },
      { path: 'recipes/id/:id', component: SingleRecipeComponent },
      { path: 'ingredients/browse', component: BrowseIngredientComponent },
      { path: 'ingredients/create', component: CreateIngredientComponent },
      { path: '**', redirectTo: 'recipes/finder' },
    ])
  ],
  providers: [DrinkService],
  bootstrap: [AppComponent]
})
export class AppModule { }
