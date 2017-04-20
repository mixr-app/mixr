var Ingredient = React.createClass({

  getInitialState: function () {
    return {
      selected: this.props.selected
    }
  },

  toggle: function () {
    //TODO tell people about what happened here
    this.setState({ selected: !this.setState.selected });
    this.props.toggleIngredientState(this.props.id);
  },

  render: function () {


    var id = this.props.id;
    var name = this.props.name;
    var description = this.props.description;
    var ingredientType = this.props.type; //May not be needed here
    var selected = this.state.selected;

    if (selected === "true") {
      return <tr><h3>{name}<input type="checkbox" onClick={this.toggle} checked /> </h3> </tr>
    } else {
      return <tr><h3>{name}<input type="checkbox" onClick={this.toggle} /> </h3> </tr>
    }
  }
});

var IngredientCategory = React.createClass({
  render: function () {
    var categoryName = this.props.categoryName;
    var ingredients = this.props.ingredients;
    var toggleIngredientState = this.props.toggleIngredientState;

    var ingredientsObjects = ingredients.map(function (ingredient, index) {
      return (
        <Ingredient
          id={ingredient.id}
          name={ingredient.name}
          description={ingredient.description}
          ingredientType={ingredient.ingredientType}
          selected={ingredient.selected} //TODO Is in pantry?
          toggleIngredientState={toggleIngredientState}
          />
      );
    });

    return <div>
      <h1>{categoryName}</h1>
      <table>{ingredientsObjects}</table>
    </div>
  }
});

var IngredientSidebar = React.createClass({

  render: function () {

    var categoryMap = {

    }

    this.props.ingredients.forEach(function (ingredient) {
      if (categoryMap[ingredient.ingredientType] === undefined) {
        categoryMap[ingredient.ingredientType] = [];
      }
      categoryMap[ingredient.ingredientType].push(ingredient);
    }, this);

    var categories = Object.keys(categoryMap).map(key => {
      return <IngredientCategory categoryName={key} ingredients={categoryMap[key]} toggleIngredientState={this.props.toggleIngredientState} />
    })

    return <ul>
      {categories}
    </ul>
  }
});


/**
 * Copy pasta'd from https://gist.github.com/danawoodman/9cfddb1a0c934a35f31a
 */
(function () {
  "use strict";

  function toggleIngredientState(ingredientId) {
    alert(ingredientId);
  }

  var ingredients = [{ "id": 1, "name": "Rum (clear)", "description": "Rum that's clear", "ingredientType": "LIQUOR" }, { "id": 2, "name": "Vodka", "description": "Vodka is pretty interchangable", "ingredientType": "LIQUOR" }, { "id": 3, "name": "Tequila", "description": "Need to break this into different types", "ingredientType": "LIQUOR" }, { "id": 4, "name": "Gin", "description": "Tastes like Christmas", "ingredientType": "LIQUOR" }, { "id": 5, "name": "Triple Sec", "description": "An orange-flavored liqueur", "ingredientType": "LIQUOR" }, { "id": 6, "name": "Cola", "description": "Any brand will do.  Choose diet for a lower calorie drink", "ingredientType": "MIXER" }, { "id": 7, "name": "Lemon Juice", "description": "Fresh-squeezed is much better but a lot of work", "ingredientType": "MIXER" }, { "id": 8, "name": "Simple Syrup", "description": "You can make this by desolving sugar into hot water on the stovetop in a 1:1 ratio", "ingredientType": "MIXER" }];
  
  ReactDOM.render(<IngredientSidebar ingredients={ingredients} toggleIngredientState={toggleIngredientState} />, document.getElementById('root'));

} ());
