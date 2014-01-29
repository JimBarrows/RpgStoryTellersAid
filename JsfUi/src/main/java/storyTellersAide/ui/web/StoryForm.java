package storyTellersAide.ui.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import storyTellersAide.services.Stories;
import storytellersaid.models.Chapter;
import storytellersaid.models.Story;

@Named
@ViewScoped
public class StoryForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StoryForm() {
		super();
		System.out.println("Creating new story form");
	}

	@EJB
	private Stories stories;
	
	//@Inject
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
		System.out.println("Getting story: " + story.toString());
		return story;
	}

	public void setStory(Story story) {
		System.out.println("Setting story: " + story.toString());
		this.story = story;
	}

}
