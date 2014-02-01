package rpgStoryTellersAid.ui.web;

import static java.lang.String.format;
import static rpgStoryTellersAid.ui.web.FacesContextUtils.successMessage;

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

	private int currentLevel = 1;
	
	private Story story;
	
	@PostConstruct
	public void init() {
		allStories = stories.all();
	}

	public Story createStory(Object context) {		
		story = new Story();		
		return story;
	}
	
	public String save(Story story) {
		if( story.isNew()) {
			stories.add(story);
			allStories = stories.all();
		} else {
			stories.update(story);
		}
		successMessage(format("%s was succcesfully saved!", story.getName()));
		return null;
	}
	
	public String delete(Story story) {
		stories.remove(story);
		successMessage(format("%s was succcesfully deleted!", story.getName()));
		return "";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Story> getAllStories() {
		return allStories;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Story getStory() {
		return story;
	}
	
}
