App.StoryRowController = Ember.ObjectController.extend({
		actions: {
				deleteStory: function(story) {
						story.deleteRecord()
						story.save()
				},

				editStory: function( story) {
						this.transitionToRoute('story.edit', story)
				}
		}
})