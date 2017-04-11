var Ingredient = React.createClass({

 getInitialState: function () {
    return {
      selected: this.props.selected
    }
  },

toggle: function(){
        //TODO tell people about what happened here
        this.setState({selected: !this.setState.selected})

    },

render: function(){


    var id = this.props.id;
    var name = this.props.name;
    var description = this.props.description;
    var ingredientType = this.props.type; //May not be needed here
    var selected = this.state.selected;

    if(selected === "true"){
    return <tr><h3>{name}<input type="checkbox" onClick={this.toggle} checked /> </h3> </tr>
    }else{
     return  <tr><h3>{name}<input type="checkbox" onClick={this.toggle} /> </h3> </tr>
    }
}
});

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
          selected={ingredient.selected} //TODO Is in pantry?
        />
      );
    });

    return <div>
             <h1>{categoryName}</h1>
             <table>{ingredientsObjects}</table>
           </div>
     }
});


/**
 * Copy pasta'd from https://gist.github.com/danawoodman/9cfddb1a0c934a35f31a
 */
(function() {
  "use strict";

  var ingredients = [{"id":"1", "name":"rum", "description":"rum description", "ingredientType":"liquor", "selected":"false"},{"id":"2", "name":"vodka", "description":"vodka description", "ingredientType":"liquor", "selected":"true"}];
  ReactDOM.render(<IngredientCategory categoryName="liquor" ingredients={ingredients} />, document.getElementById('root'));

}());
