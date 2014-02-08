package rpgStoryTellersAid.ui.web;

import static org.apache.commons.lang3.StringUtils.*;
import static java.lang.String.format;
import static rpgStoryTellersAid.ui.web.FacesContextUtils.successMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import rpgStoryTellersAide.models.Actor;
import rpgStoryTellersAide.models.Chapter;
import rpgStoryTellersAide.models.Location;
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
	
	private Location location;

	private Actor actor;	

	@PostConstruct
	public void init() {
		allStories = stories.all();
	}

	public List<Location> locationComplete(String query) {
		List<Location> results = new ArrayList<Location>();
		ListIterator<Location> listIterator = scene.getChapter().getStory().getLocations().listIterator();
		while (listIterator.hasNext()) {
			Location next = listIterator.next();
			if (isBlank(query) || next.getName().startsWith(query)) {
				results.add(next);
			}
		}

		return results;
	}

	public Story createStory(Object context) {
		story = new Story();
		return story;
	}

	public Chapter createChapter(Story story) {
		chapter = new Chapter();
		story.add(chapter);
		return chapter;
	}

	public Scene createScene(Chapter chapter) {
		scene = new Scene();
		chapter.add(scene);
		return scene;
	}
	
	public Location createLocation(Scene scene) {
		location = new Location();
		scene.add( location);
		return location;
	}

	public Actor createActor(Scene scene) {
		actor = new Actor();
		scene.add(actor);
		return actor;
	}

	public String save(Story story) {
		stories.save(story);
		allStories = stories.all();
		successMessage(format("%s was succcesfully saved!", story.getName()));
		return null;
	}

	public String save(Chapter chapter) {
		stories.save(chapter.getStory());
		successMessage(format("%s was succcesfully saved!", chapter.getTitle()));
		return null;
	}

	public String save(Scene scene) {
		stories.save(scene.getChapter().getStory());
		successMessage(format("%s was succcesfully saved!", scene.getTitle()));
		return null;
	}
	
	public String save(Location location) {
		stories.save(location.scenes().last().getChapter().getStory());
		successMessage(format("%s was succcesfully saved!", location.getName()));
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
		story.remove(chapter);
		stories.update(story);
		successMessage(format("%s was succcesfully removed from %s!", chapter.getTitle(), story.getName()));
		return "";
	}

	public String delete(Scene scene) {
		Chapter chapter = scene.getChapter();
		chapter.remove(scene);
		stories.update(chapter.getStory());
		successMessage(format("%s was succcesfully removed from %s!", scene.getTitle(), chapter.getTitle()));
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

	public Scene getScene() {
		return scene;
	}

	public Actor getActor() {
		return actor;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Location getLocation() {
		return location;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
