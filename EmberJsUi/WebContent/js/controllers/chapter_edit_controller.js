App.ChapterNewController = Ember.ObjectController.extend({
	actions : {
		save : function() {
			var chapter = this.get('model');
			var dis = this;
			chapter.save().then(function(chapter) {
				dis.transitionToRoute('chapter.edit', chapter.get('story'), chapter);
			});
		}
	}
});

App.ChapterEditController = Ember.ObjectController.extend({

	actions : {

		save : function() {
			this.get('model').save();
		},

		done : function() {
			this.transitionToRoute("story.edit", this.get('model').get('story'));
		},

		cancel : function(chapter) {
			
			this.get('model').rollback();
			
		},

		editScene : function(story, chapter, scene) {
			this.transitionToRoute("scene.edit", story, chapter, scene);
		},

		addScene : function(chapter) {
			this.transitionToRoute("scene.new", chapter);
		},

		deleteScene : function(chapter, scene) {
			this.get('model').get('scenes').removeObject(scene);
			this.get('model').save();
		}
	}


});
