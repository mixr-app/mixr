package club.mixr.data.entity;

import javax.persistence.*;

import club.mixr.dto.Ingredient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

 
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ingredients")
public class IngredientEntity extends AuditingEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    public IngredientEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public IngredientEntity(String name, String description, IngredientType ingredientType) {
        super();
        this.name = name;
        this.description = description;
        this.ingredientType = ingredientType;
    }

    public Ingredient toIngredient(){
        return new Ingredient(id, name, description, ingredientType);
    }
}