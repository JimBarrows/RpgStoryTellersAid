package storyTellersAide.ui.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import storyTellersAide.services.Stories;
import storytellersaid.models.Chapter;
import storytellersaid.models.Story;

@ManagedBean
@RequestScoped
public class StoryForm {

	@EJB
	private Stories stories;
	
	private Story story;	

	public String save() {
		if ((story.getId() == null) || (story.getId() <= 0)){
			stories.add(story);
		} else {
			stories.update(story);
		}
		return "storyList";
	}	

	public String cancel() {
		if (story.getId() == null) {
			story = new Story();
		} else {
			story = stories.findBy(story.getId()).toNull();

		}
		return "storyList";
	}

	public String delete( Chapter chapter) {
		stories.removeChapterFrom(story, chapter);
		return null;
	}
	
	public Story getStory() {
		if( story == null) {
			story = new Story();
		}
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

}
