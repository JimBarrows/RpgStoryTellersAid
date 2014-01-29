package storyTellersAide.ui.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import storyTellersAide.services.Stories;
import storytellersaid.models.Story;

@Named
@ViewScoped
public class StoryList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private Stories stories;

	public List<Story> getAll() {

		return stories.all();
	}

	public void delete(Story story) {
		stories.remove(story);
	}
}
