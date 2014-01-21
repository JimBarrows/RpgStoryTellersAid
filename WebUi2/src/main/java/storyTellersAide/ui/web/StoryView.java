package storyTellersAide.ui.web;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;

import storyTellersAide.services.Stories;
import storytellersaid.models.Story;

@ManagedBean
@RequestScoped
public class StoryView {

	@EJB
	private Stories stories;
	
	private Long id;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Story getStory() {
		return stories.findBy(getId());
	}
	
}
