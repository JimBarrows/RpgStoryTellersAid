package rpgStoryTellersAide.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Scene implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private long version;

	@NotEmpty
	private String title = "New Scene";

	@NotNull
	@Min(value = 1)
	private Long number;

	private String gmDescription = "";

	private String playerDescription = "";

	@ManyToOne
	private Chapter chapter;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "scene", orphanRemoval = true)
	private List<Clue> clues = new ArrayList<Clue>();

	@ManyToOne(targetEntity = Location.class)
	private List<Location> location = new ArrayList<Location>();

	@ManyToMany
	private List<Actor> actors = new ArrayList<Actor>();
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getGmDescription() {
		return gmDescription;
	}

	public void setGmDescription(String gmDescription) {
		this.gmDescription = gmDescription;
	}

	public String getPlayerDescription() {
		return playerDescription;
	}

	public void setPlayerDescription(String playerDescription) {
		this.playerDescription = playerDescription;
	}

	// public Chapter getChapter() {
	// return chapter;
	// }
	//
	//
	// public void setChapter(Chapter chapter) {
	// this.chapter = chapter;
	// }
}
