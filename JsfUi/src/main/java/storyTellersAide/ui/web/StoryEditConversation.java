package storyTellersAide.ui.web;

import static org.apache.commons.lang3.StringUtils.*;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import storytellersaid.models.Chapter;
import storytellersaid.models.Story;

@Named
@ConversationScoped
public class StoryEditConversation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Story story;

	private Chapter chapter;

	@Inject
	private Conversation conversation;

	public void start() {
		if (isBlank(conversation.getId())) {
			conversation.begin();
		}
	}

	public void end() {
		conversation.end();
	}

	@Named
	@Produces
	public Story getStory() {
		if (story == null) {
			story = new Story();
		}
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	@Named
	@Produces
	public Chapter getChapter() {
		if( chapter == null) {
			chapter = new Chapter();
		}
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
}
