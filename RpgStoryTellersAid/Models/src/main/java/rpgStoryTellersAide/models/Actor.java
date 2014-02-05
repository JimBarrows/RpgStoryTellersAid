package rpgStoryTellersAide.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Actor implements Serializable{	

		@Id
		@GeneratedValue
		private Long id;
		
		@Version
		private long version;	
		
		@NotEmpty
		private String name = "John Smith";
		
		@Lob
		private String physicalDescription="";
		
		@Lob
		private String description="";
		
		@ManyToMany
		private List<Scene> scenes = new ArrayList<Scene>();
		
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhysicalDescription() {
			return physicalDescription;
		}

		public void setPhysicalDescription(String physicalDescription) {
			this.physicalDescription = physicalDescription;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<Scene> getScenes() {
			return scenes;
		}

		public void setScenes(List<Scene> scenes) {
			this.scenes = scenes;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			Actor other = (Actor) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return String.format("Actor [id=%s, version=%s, name=%s, physicalDescription=%s, description=%s]", id,
					version, name, physicalDescription, description);
		}
	
}
