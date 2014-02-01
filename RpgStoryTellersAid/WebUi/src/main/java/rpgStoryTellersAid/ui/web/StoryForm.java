package rpgStoryTellersAid.ui.web;

import static rpgStoryTellersAid.ui.web.FacesContextUtils.return404;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import rpgStoryTellersAide.models.Chapter;
import rpgStoryTellersAide.models.Story;
import rpgStoryTellersAide.services.Stories;
import fj.data.Option;

@ManagedBean
@RequestScoped
public class StoryForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private Stories stories;
	
	private Story story;	

	@ManagedProperty(value="#{param.storyId}")
	private Long storyId;
	
	@PostConstruct
	public void init() {
		if( storyId == null) {
			story = new Story();
		} else {
			Option<Story> option = stories.findBy(storyId);
			if(option.isSome()) {
				story = option.some();
			} else {
				return404();
			}
		}
	}
	
	public String save() {
		if ((story.getId() == null) || (story.getId() <= 0)){
			stories.add(story);
		} else {
			stories.update(story);
		}
		return "index";
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
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

}
