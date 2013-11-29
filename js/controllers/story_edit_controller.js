App.StoryEditController = Ember.ObjectController.extend({
		actions:{

				saveStory: function( story) {
						story.save()
						if( this.get('isNew') == true) {
								this.transitionToRoute("story.edit", story)
						}
				},

				doneStory: function() {
						this.transitionToRoute("stories")
				},

				cancelStory: function( story) {
						story.rollback();
				},

				addChapter: function( story) {
						this.transitionToRoute("chapter.new", this.store.createRecord('chapter', {story: story}))
				},

				editChapter: function( story, chapter) {
						this.transitionToRoute("chapter.edit", story, chapter)
				},

				deleteChapter: function( story, chapter) {
						story.get('chapters').removeObject(chapter)
						story.save()
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
				return false
		}.property(),

		showChapters: true, 

		showCast: false 
})