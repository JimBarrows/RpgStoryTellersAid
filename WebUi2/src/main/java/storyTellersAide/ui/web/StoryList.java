package storyTellersAide.ui.web;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
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
}
