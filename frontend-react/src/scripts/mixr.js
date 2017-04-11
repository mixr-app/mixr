var Ingredient = React.createClass({

render: function(){
    var checked = this.props.checked;
    var name = this.props.name;
    if(checked === "true"){
    return <tr><h3>{name}<input type="checkbox" checked /> </h3> </tr>
    }else{
     return  <tr><h3>{name}<input type="checkbox" /> </h3> </tr>
     }

}
})

var IngredientsSidebar = React.createClass({


  render: function () {

  var ingredients = [<Ingredient name = "rum" checked="true"/>, <Ingredient name = "vodka" checked="false"/>, <Ingredient name = "whiskey"  checked="true"/>];
  var ingredient = new Ingredient();

    return<table>{ingredients}</table>
  }
});

/**
 * Copy pasta'd from https://gist.github.com/danawoodman/9cfddb1a0c934a35f31a
 */
(function() {
  "use strict";
  ReactDOM.render(<IngredientsSidebar />, document.getElementById('root'));
}());
