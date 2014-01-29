package storyTellersAide.ui.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import storyTellersAide.services.Stories;
import storytellersaid.models.Chapter;
import storytellersaid.models.Story;

@ManagedBean
@ViewScoped
public class ChapterForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private Stories stories;

	@Inject
	private Story story;

	@Inject
	private Chapter chapter;

	public String save() {
		if ((chapter.getId() == null) || (chapter.getId() <= 0)) {
			stories.add(story.getId(), chapter);
		} else {
			stories.update(story.getId(), chapter);
		}
		return "";
	}

	public Chapter getChapter() {
		if (chapter == null) {
			chapter = new Chapter();
		}
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

}
