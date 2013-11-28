App.ChapterEditController = Ember.ObjectController.extend({

		actions: {
				saveChapter: function( chapter) {
						chapter.save()
				},
				doneChapter: function(chapter) {
						this.transitionToRoute("story.edit", chapter.story)
				},
				cancelChapter: function( chapter) {
						chapter.rollback();
				}
		}
})
