package rpgStoryTellersAid.ui.web;

import static rpgStoryTellersAid.ui.web.FacesContextUtils.return404;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import fj.data.Option;
import rpgStoryTellersAide.models.Chapter;
import rpgStoryTellersAide.models.Story;
import rpgStoryTellersAide.services.Stories;

@ManagedBean
@RequestScoped
public class ChapterForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private Stories stories;

	private Chapter chapter;

	@ManagedProperty(value = "#{param.chapterId}")
	private Long chapterId;

	@ManagedProperty(value = "#{storyForm}")
	private StoryForm storyForm;

	@PostConstruct
	public void init() {
		if (chapterId == null) {
			chapter = new Chapter();			
		} else {
			Option<Chapter> option = storyForm.getStory().findChapterById(chapterId);
			if (option.isSome()) {
				chapter = option.some();
			} else {
				return404();
			}
		}
	}

	public String save() {

		stories.add(storyForm.getStory(), chapter);
		return "";
	}

	public Chapter getChapter() {	
		return chapter;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public void setStoryForm(StoryForm storyForm) {
		this.storyForm = storyForm;
	}

}
