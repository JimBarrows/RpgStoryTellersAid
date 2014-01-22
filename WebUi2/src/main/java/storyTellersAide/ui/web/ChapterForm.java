package storyTellersAide.ui.web;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;

import storyTellersAide.services.Stories;
import storytellersaid.models.Chapter;

@ManagedBean
@RequestScoped
public class ChapterForm {

	@EJB
	private Stories stories;
	
	private Long storyId;
	
	private Chapter chapter = new Chapter();

	public String save() {
		if ((chapter.getId() == null) || (chapter.getId() <= 0)){			
			stories.add(storyId, chapter);
		} else {
			stories.update(storyId, chapter);
		}
		return "chapterList";
	}	

	public String cancel() {
		if (chapter.getId() == null) {
			chapter = new Chapter();
		} else {
			//chapter = stories.findBy(storyId).getChapters().;

		}
		return "chapterList";
	}
	
	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	
	
}
