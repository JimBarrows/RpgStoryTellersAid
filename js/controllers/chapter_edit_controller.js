App.ChapterEditController = Ember.ObjectController.extend({

		actions: {

				saveChapter: function( chapter) {
						if (this.get('isNew')) {
								chapter.get('story').get('chapters').pushObject( chapter)
						}
						chapter.save()
				},

				doneChapter: function(chapter) {
						this.transitionToRoute("story.edit", chapter.get('story'))
				},

				cancelChapter: function( chapter) {
						if( ! this.get('isNew')) {
								chapter.rollback();
						}
				}
		},

		isNew: function() {
				return false
		}.property()

})
