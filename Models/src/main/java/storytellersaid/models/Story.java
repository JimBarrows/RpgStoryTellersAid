package storytellersaid.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Story implements Serializable{

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private long version;

	@NotEmpty
	private String name;
	
	@Lob
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="story", orphanRemoval=true)
	@OrderBy(value="number")
	private List<Chapter> chapters = new ArrayList<Chapter>();
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Story other = (Story) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return String
				.format("Story [id=%s, version=%s, name=%s, description=%s, chapters=%s]",
						id,
						version,
						name,
						description,
						chapters != null ? chapters.subList(0,
								Math.min(chapters.size(), maxLen)) : null);
	}
}
