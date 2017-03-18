package club.mixr.data.entity;

import club.mixr.dto.Recipe;
import club.mixr.dto.Source;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "recipes")
public class RecipeEntity extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
	private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "image_location")
    private String imageLocation;

    @Column(name = "source")
    private Long sourceId;

    public RecipeEntity() {

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

    public String getInstructions() {
        return instructions;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public Recipe toRecipe() {
        return new Recipe(id, name, description, instructions, imageLocation, sourceId);
    }
}