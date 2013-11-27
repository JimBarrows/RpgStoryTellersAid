App.StoryFormController = Ember.ObjectController.extend({
		actions:{
				saveStory: function( story) {
						story.save()
						this.transitionToRoute("story.edit", story)
				},
				doneStory: function() {
						this.transitionToRoute("stories")
				},
				cancelStory: function( story) {
						story.rollback();
				},
				addChapter: function( story) {
						this.transitionToRoute("story.chapter.new", story)
				},
				showChapterTab: function() {
						this.set('showChapters', true)
						this.set('showCast',false)
				},
				
				showCastTab: function() {
						this.set('showChapters', false)
						this.set('showCast',true)
				}
		},

		isNew: function() {
				return this.get('content').get('id');
		}.property(),

		showChapters: true, 

		showCast: false 
})