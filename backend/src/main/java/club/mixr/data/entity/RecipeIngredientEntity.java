package club.mixr.data.entity;

import club.mixr.dto.RecipeIngredient;
import club.mixr.dto.Source;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "recipe_ingredients")
public class RecipeIngredientEntity extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipe_id", insertable = false, updatable = false)
    private Long recipeId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    private IngredientEntity ingredient;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "unit")
    private String unit;


    public RecipeIngredientEntity() {

    }

    public RecipeIngredientEntity(RecipeEntity recipe, Long ingredientId, Double amount, String unit) {
        this.recipe = recipe;
        this.ingredientId = ingredientId;
        this.amount = amount;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public Double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public RecipeIngredient toRecipeIngredient() {
        return new RecipeIngredient(id, recipeId != null ? recipeId : recipe.getId(), ingredientId, amount, unit);
    }

    @Override
    public String toString() {
        return "RecipeIngredientEntity{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", ingredientId=" + ingredientId +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                '}';
    }
}