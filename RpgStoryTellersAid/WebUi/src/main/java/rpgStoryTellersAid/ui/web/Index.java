package rpgStoryTellersAid.ui.web;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import rpgStoryTellersAide.models.Story;
import rpgStoryTellersAide.services.Stories;

@ManagedBean
@ViewScoped
public class Index implements Serializable {	

	@EJB
	private Stories stories;

	private List<Story> allStories;
	
	@PostConstruct
	public void init() {
		allStories = stories.all();
	}
	
	public String delete(Story story) {
		stories.remove(story);
		allStories = stories.all();
		return "";
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Story> getAllStories() {
		return allStories;
	}
}
