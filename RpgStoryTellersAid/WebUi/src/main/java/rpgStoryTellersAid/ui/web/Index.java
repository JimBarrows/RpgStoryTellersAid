package rpgStoryTellersAid.ui.web;

import static java.lang.String.format;
import static rpgStoryTellersAid.ui.web.FacesContextUtils.successMessage;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import rpgStoryTellersAide.models.Chapter;
import rpgStoryTellersAide.models.Scene;
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

	private Chapter chapter;
	
	private Scene scene;
	
	@PostConstruct
	public void init() {
		allStories = stories.all();
	}

	public Story createStory(Object context) {
		story = new Story();
		return story;
	}

	public Chapter createChapter(Story story) {
		chapter = new Chapter();
		if( story.isNew()) {
			story.add(chapter);
		} else {
			stories.add(story, chapter);
		}
		return chapter;
	}
	
	public Scene createScene(Chapter chapter) {
		scene = new Scene();
		if( scene.isNew()) {
			chapter.add(scene);
		} else {
			stories.add(chapter, scene);
		}
		return scene;
	}

	public String save(Story story) {

		stories.save(story);

		successMessage(format("%s was succcesfully saved!", story.getName()));
		return null;
	}

	public String save(Chapter chapter) {
		stories.save( chapter.getStory());
		successMessage(format("%s was succcesfully saved!", chapter.getTitle()));
		return null;
	}

	public String delete(Story story) {
		stories.delete(story);
		successMessage(format("%s was succcesfully deleted!", story.getName()));
		allStories = stories.all();
		return "";
	}

	public String delete(Chapter chapter) {
		Story story = chapter.getStory();
		stories.removeChapterFrom(story, chapter);
		successMessage(format("%s was succcesfully removed from %s!", chapter.getTitle(), story.getName()));
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

	public Chapter getChapter() {
		return chapter;
	}

}
