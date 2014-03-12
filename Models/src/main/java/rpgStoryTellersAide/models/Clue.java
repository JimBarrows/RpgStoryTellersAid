package rpgStoryTellersAide.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Clue implements Serializable{	

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private long version;	
	
	@NotEmpty
	@Lob
	private String description="";
	
	@NotEmpty
	@Lob
	private String obtainBy="";
	
	@ManyToOne
	private Scene scene;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObtainBy() {
		return obtainBy;
	}

	public void setObtainBy(String obtainBy) {
		this.obtainBy = obtainBy;
	}
}
