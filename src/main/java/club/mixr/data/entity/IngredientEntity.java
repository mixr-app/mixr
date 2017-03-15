package club.mixr.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

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

    @Version
    private long version;

    public IngredientEntity() {

    }

    public IngredientEntity(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public Ingredient toIngredient(){
        return new Ingredient(id, name, description);
    }
}