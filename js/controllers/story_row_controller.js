App.StoryRowController = Ember.ObjectController.extend({
		actions: {
				deleteStory: function() {
						var story = this.get('model')
						story.deleteRecord()
						story.save()
				}
		}
})