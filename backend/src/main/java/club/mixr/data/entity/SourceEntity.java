package club.mixr.data.entity;

import club.mixr.dto.Ingredient;
import club.mixr.dto.Source;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sources")
public class SourceEntity extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

    @Column(name = "description", length = 500)
    private String description;

    public SourceEntity() {

    }

    public SourceEntity(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public Source toSource(){
        return new Source(id, name, description);
    }
}