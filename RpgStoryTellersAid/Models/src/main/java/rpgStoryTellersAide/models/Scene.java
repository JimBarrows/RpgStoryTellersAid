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

import fj.F;
import fj.data.Java;

@Entity
public class Scene implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private long version;

	@NotEmpty
	private String title = "";

	@NotNull
	@Min(value = 1)
	private Long number;

	private String gmDescription = "";

	private String playerDescription = "";

	@ManyToOne
	private Chapter chapter;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "scene", orphanRemoval = true)
	private List<Clue> clues = new ArrayList<Clue>();

	@ManyToOne(targetEntity = Location.class, cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
	private Location location = null;

	@ManyToMany(targetEntity = Actor.class, cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
	private List<Actor> actors = new ArrayList<Actor>();

	public fj.data.List<Actor> actors() {
		F<ArrayList<Actor>, fj.data.List<Actor>> arrayList_List = Java.ArrayList_List();
		return arrayList_List.f((ArrayList<Actor>) actors);
	}

	public void add(Actor actor) {
		actor.getScenes().add(this);
		actors.add(actor);

	}

	public void add(Location location) {
		location.getScenes().add(this);
		this.location = location;

	}

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

	public boolean isNew() {
		return (id == null) || (id <= 0);
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public List<Clue> getClues() {
		return clues;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chapter == null) ? 0 : chapter.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Scene other = (Scene) obj;
		if (chapter == null) {
			if (other.chapter != null)
				return false;
		} else if (!chapter.equals(other.chapter))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return String
				.format("Scene [id=%s, version=%s, title=%s, number=%s, gmDescription=%s, playerDescription=%s, chapter=%s, clues=%s, location=%s, actors=%s]",
						id, version, title, number, gmDescription, playerDescription, chapter.getId(),
						clues != null ? clues.subList(0, Math.min(clues.size(), maxLen)) : null, location,
						actors != null ? actors.subList(0, Math.min(actors.size(), maxLen)) : null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
