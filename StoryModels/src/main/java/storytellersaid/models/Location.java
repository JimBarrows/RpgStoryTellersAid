package storytellersaid.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Location implements Serializable{	

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private long version;	
	
	@NotEmpty
	private String name = "New Location";
	
	@Lob
	private String description="";
	
	@OneToMany(mappedBy="location")
	private List<Scene> scenes = new ArrayList<Scene>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
