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
	
}
