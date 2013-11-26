App.StoryFormController = Ember.ObjectController.extend({
		actions:{
				saveStory: function( story) {
						story.save();
						this.get("target").transitionTo("story")
				},
				addChapter: function() {
						this.get("target").transitionTo("story.chapter.new")
				}},

		isNew: function() {
				return this.get('content').get('id');
		}.property()
})