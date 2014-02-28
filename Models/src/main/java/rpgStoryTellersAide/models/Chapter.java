package rpgStoryTellersAide.models;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import fj.F;
import fj.data.Java;

@Entity
public class Chapter implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private long version;

	@NotEmpty
	private String title = "New Title";

	@NotNull
	@Min(value = 1)
	private Long number = 1l;

	private String description = "";

	@ManyToOne
	private Story story;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "number")
	private List<Scene> scenes = new ArrayList<Scene>();

	public void add(Scene scene) {
		scene.setChapter(this);
		scenes.add(scene);
		if (scene.getNumber() == null || scene.getNumber() <= 0) {
			scene.setNumber((long) scenes.size());
		}
		if (isBlank(scene.getTitle())) {
			scene.setTitle("Scene " + scene.getNumber());
		}

	}

	public boolean isNew() {
		return (id == null) || (id <= 0);
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
		Chapter other = (Chapter) obj;
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
		return String.format("Chapter [id=%s, version=%s, title=%s, number=%s, description=%s, story=%s, scenes=%s]",
				id, version, title, number, description, "story",
				scenes != null ? scenes.subList(0, Math.min(scenes.size(), maxLen)) : null);
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

	public void remove(Scene scene) {
		scene.setChapter(null);
		scenes.remove(scene);

	}

	public fj.data.List<Scene> scenes() {

		F<ArrayList<Scene>, fj.data.List<Scene>> arrayList_List = Java.ArrayList_List();
		return arrayList_List.f( new ArrayList<Scene>( scenes));

	}

}
