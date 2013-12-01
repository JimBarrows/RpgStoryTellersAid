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
				},

				editScene: function( story, chapter, scene) {
						this.transitionToRoute("scene.edit", story, chapter, scene)
				},

				addScene: function(  chapter) {
						this.transitionToRoute("scene.new", chapter)
				}
		},

		isNew: function() {
				return false
		}.property()

})
