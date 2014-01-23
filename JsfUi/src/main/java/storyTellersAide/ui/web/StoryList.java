package storyTellersAide.ui.web;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import storyTellersAide.services.Stories;
import storytellersaid.models.Story;

@ManagedBean
@RequestScoped
public class StoryList {

	@EJB
	private Stories stories;
	
	public List<Story> getAll() {
		
		return stories.all();
	}
	
	public void delete( Story story) {
		stories.remove( story);
	}
}