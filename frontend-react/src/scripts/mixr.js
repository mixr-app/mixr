var Ingredient = React.createClass({

render: function(){


    var id = this.props.id;
    var name = this.props.name;
    var description = this.props.description;
    var ingredientType = this.props.type; //May not be needed here
    var selected = this.props.selected;

    if(selected === "true"){
    return <tr><h3>{name}<input type="checkbox" checked /> </h3> </tr>
    }else{
     return  <tr><h3>{name}<input type="checkbox" /> </h3> </tr>
     }

}
})

var IngredientCategory = React.createClass({
  render: function () {
  var categoryName = this.props.categoryName;
  var ingredients = this.props.ingredients;

  var ingredientsObjects = ingredients.map(function (ingredient, index) {
      return (
       <Ingredient
          id={ingredient.id}
          name={ingredient.name}
          description={ingredient.description}
          ingredientType={ingredient.ingredientType}
          checked="false"
        />
      );
    });


//    return<table>{ingredientsObjects}</table>
<h1>hello</h1>
  }

})


/**
 * Copy pasta'd from https://gist.github.com/danawoodman/9cfddb1a0c934a35f31a
 */
(function() {
  "use strict";

  var ingredients = [{"id":"1", "name":"rum", "description":"rum description", "ingredientType":"liquor"},{"id":"2", "name":"vodka", "description":"vodka description", "ingredientType":"liquor"}];
  ReactDOM.render(<IngredientCategory categoryName="liquor" ingredients={ingredients} />, document.getElementById('root'));

}());
