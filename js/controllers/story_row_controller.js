App.StoryRowController = Ember.ObjectController.extend({
		actions: {
				deleteStory: function() {
						var story = this.get('model')
						story.deleteRecord()
						story.save()
				},

				editStory: function() {
						this.get('target').transitionTo("/story/" + this.get('model').id + "/edit")
				}
		}
})