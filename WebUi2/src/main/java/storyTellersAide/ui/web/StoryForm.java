package storyTellersAide.ui.web;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;

import storyTellersAide.services.Stories;
import storytellersaid.models.Story;

@ManagedBean
@RequestScoped
public class StoryForm {

	@EJB
	private Stories stories;

	private Story story = new Story();

	public String save() {
		stories.add(story);
		return "storyList";
	}

	public String cancel() {
		if (story.getId() == null) {
			story = new Story();
		} else {
			story = stories.findBy( story.getId());

		}
		return "storyList";
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}
}
