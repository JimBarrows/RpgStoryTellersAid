package storyTellersAide.ui.web;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;

import storyTellersAide.services.Stories;
import storytellersaid.models.Chapter;
import storytellersaid.models.Story;
import fj.F;

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
		return "";
	}	

	public String cancel() {
		if ((chapter.getId() == null) || (chapter.getId() <= 0)) {
			chapter = new Chapter();
		} else {
			final Long chapterId = getChapter().getId();
			chapter = stories.findBy(storyId).map(new F<Story, Chapter>() {

				@Override
				public Chapter f(Story story) {
					return story.findChapterById(chapterId).toNull();
				}

				
			}).toNull();

		}
		return "storyForm";
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
