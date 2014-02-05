package rpgStoryTellersAide.models;

import static java.lang.String.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import fj.F;
import fj.data.Java;
import fj.data.Option;

@Entity
public class Story implements Serializable {

	public Story() {
	};

	public Story(Long id, Long version, String name, String description) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.description = description;
	}

	public Story(Long id, Long version, String name, String description, List<Chapter> chapters) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.description = description;
		this.chapters = chapters;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@NotEmpty
	private String name;

	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "number")
	private List<Chapter> chapters = new ArrayList<Chapter>();

	public Option<Chapter> findChapterById(final Long chapterId) {
		return chapters().find(new F<Chapter, Boolean>() {

			@Override
			public Boolean f(Chapter curChapter) {
				return curChapter.getId() == chapterId;
			}
		});

	}

	public fj.data.List<Chapter> chapters() {
		F<ArrayList<Chapter>, fj.data.List<Chapter>> arrayList_List = Java.ArrayList_List();
		return arrayList_List.f((ArrayList<Chapter>) chapters);
	}

	public boolean isNew() {
		return (id == null) || (id <= 0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
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
		return String.format("Story [id=%s, version=%s, name=%s, description=%s, chapters=%s]", id, version, name,
				description,
				chapters != null ? Arrays.asList(chapters.toArray()).subList(0, Math.min(chapters.size(), maxLen))
						: null);
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void add(Chapter chapter) {
		chapter.setStory(this);
		chapters.add(chapter);
		chapter.setNumber((long) chapters.size());
		chapter.setTitle(format("Chapter %d", chapter.getNumber()));
	}

	public void remove(Chapter chapter) {
		chapter.setStory(null);
		chapters.remove(chapter);

	}
}
