App.StoryFormController = Ember.ObjectController.extend({
		actions:{
				saveStory: function( story) {
						story.save();
						console.log("story.name: " + story.name)
						this.get("target").transitionTo("/")
				}},

		isNew: function() {
				return this.get('content').get('id');
		}.property()
})