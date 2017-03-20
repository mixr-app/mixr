import Html exposing (..)
import Html.Attributes exposing (style, href, class, for, type_, id)
import Html.Events exposing (..)
import Http
import Json.Decode exposing (..)
import Task
import Set
import Shared.Styles as Styles

main =
  Html.program
    { init = init 
    , view = view
    , update = update
    , subscriptions = \_ -> Sub.none
    }

-- MODEL


type alias Model =
  { ingredients : List Ingredient
  , recipes : List Recipe
  , selectedIngredients : Set.Set Int
  }

type alias Ingredient = 
  {
    id : Int
  , name : String
  , description : String
  }

type alias Recipe = 
  {
    id : Int
  , name : String
  , ingredients : List Ingredient
  }

init : (Model, Cmd Msg)
init =
  ( Model [] [] Set.empty
  , getIngredients
  )



-- UPDATE


type Msg
  = RefreshJson
  | CheckIngredient Bool Int
  | LoadedIngredients (Result Http.Error (List Ingredient))
  | LoadedRecipes (Result Http.Error (List Recipe))


update : Msg -> Model -> (Model, Cmd Msg)
update msg model =
  case msg of
    RefreshJson ->
      (model, getIngredients)

    CheckIngredient True index ->
      let 
        newModel = {model | selectedIngredients = Set.insert index model.selectedIngredients}
        debug1 = Debug.log "selected: " newModel.selectedIngredients
      in 
        (newModel, Cmd.none)
        
    CheckIngredient False index ->
      let 
        newModel = {model | selectedIngredients = Set.remove index model.selectedIngredients}
        debug1 = Debug.log "selected: " newModel.selectedIngredients
      in 
        (newModel, Cmd.none)

    LoadedIngredients (Ok ingredients) ->
      ({model | ingredients = ingredients}, getRecipes)

    LoadedRecipes (Ok recipes) ->
      ({model | recipes = recipes}, Cmd.none)
      
    LoadedIngredients (Err a) ->
      let 
        debug = Debug.log "error: " a
      in 
        (model, Cmd.none)
        
    LoadedRecipes (Err a) ->
      let 
        debug = Debug.log "error: " a
      in 
        (model, Cmd.none)



-- VIEW


view : Model -> Html Msg
view model =
  div [class "container-fluid"] [
      navbar
    -- , hr [] []
    -- , button [ onClick RefreshJson ] [ text "Refresh" ]
    , hr [] []
    , div [class "row"] [ 
        div [class "col-sm-3"] ([h2 [] [text "Ingredients"]] ++ (List.map ingredientToHtml model.ingredients))
      , div [class "col-sm-9"] [
          h2 [] [text "Browse All Recipes"]
        , div [class "row"] (List.map recipeToHtml model.recipes)
        ]
      ]
    ]

navbar =
  nav [class "navbar navbar-light bg-faded"] [
    a [class "navbar-brand", href "#"] [
      text "miXr"
    ]
  ]

ingredientToHtml : Ingredient -> Html Msg
ingredientToHtml ingredient =
  div [class "card"] [
    label [class "card-header", for ("cbox" ++ toString ingredient.id)] [
      div [class "row"] [
        div [class "col-sm-10"] [
          text ingredient.name
        ]
      , div [class "col-sm-2"] [
          input [type_ "checkbox", id ("cbox" ++ toString ingredient.id), onCheck (\checked -> CheckIngredient checked ingredient.id )] []
        ]
      ]
    ]
  ]

recipeToHtml recipe =
  let
    ingredientString = 
      recipe.ingredients
        |> List.map (\ingredient -> ingredient.name)
        |> String.join ", "
  in 
    div [class "col-sm-3 card"] [
      div [class "card-block"] [
        h4 [class "card-title"] [text recipe.name]
      , h6 [class "card-subtitle mb-2 text-muted"] [text ingredientString]
      ]
  ]


divWidth width attributes =
  div ([style [("float", "left"), ("width", width)]] ++ attributes)



-- HTTP


getIngredients : Cmd Msg
getIngredients =
  let
    url =
      "http://localhost:8080/ingredients/"
    request =
      Http.get url decodeIngredients
  in
    Http.send LoadedIngredients request

decodeIngredient : Decoder Ingredient
decodeIngredient =
  (map3 Ingredient (field "id" int) (field "name" string) (field "description" string))

decodeIngredients : Decoder (List Ingredient)
decodeIngredients =
  list decodeIngredient
  

getRecipes : Cmd Msg
getRecipes =
  let
    url =
      "http://localhost:8080/recipesWithIngredients/"
    request =
      Http.get url decodeRecipes
  in
    Http.send LoadedRecipes request

decodeRecipes : Decoder (List Recipe)
decodeRecipes =
  list (map3 Recipe (field "id" int) (field "name" string) (field "ingredients" decodeRecipeIngredients))

decodeRecipeIngredients : Decoder (List Ingredient)
decodeRecipeIngredients =
  list (field "ingredient" decodeIngredient)