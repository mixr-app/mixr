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

    @Column(name = "recipe_id")
    private Long recipeId;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "unit")
    private String unit;


    public RecipeIngredientEntity() {

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

    public Float getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public RecipeIngredient toRecipeIngredient() {
        return new RecipeIngredient(id, recipeId, ingredient.toIngredient(), amount, unit);
    }
}