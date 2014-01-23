package storyTellersAide.ui.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import storyTellersAide.services.Stories;
import storytellersaid.models.Story;

@ManagedBean
@RequestScoped
public class StoryView {

	@EJB
	private Stories stories;
	
	private Story story;

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}	
	
}
