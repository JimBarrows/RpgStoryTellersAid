package rpgStoryTellersAid.ui.web;

import static rpgStoryTellersAid.ui.web.FacesContextUtils.return404;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import rpgStoryTellersAide.models.Story;
import rpgStoryTellersAide.services.Stories;
import fj.data.Option;

@ManagedBean
@RequestScoped
public class StoryView implements Serializable {

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
			return404();
		} else {
			Option<Story> option = stories.findBy(storyId);
			if(option.isSome()) {
				story = option.some();
			} else {
				return404();
			}
		}
	}
	public Story getStory() {
		return story;
	}
	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	

}
