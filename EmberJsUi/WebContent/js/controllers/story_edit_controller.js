App.StoryFormController = Ember.ObjectController.extend({
	actions : {
		done : function() {
			this.transitionToRoute("stories");
		},

		cancel : function() {
			this.get('model').rollback();
		}
	}
});

App.StoryNewController = App.StoryFormController.extend({
	actions : {

		save : function() {
			var story = this.get('model');
			var dis = this;
			story.save().then(function(story) {
				dis.transitionToRoute('story.edit', story);
			});
		}
	}
});

App.StoryEditController = App.StoryFormController.extend({
	actions : {

		save : function() {
			var story = this.get('model');
			story.save();
		},		

		showChapterTab : function() {
			this.set('showChapters', true);
			this.set('showCast', false);
		},

		showCastTab : function() {
			this.set('showChapters', false);
			this.set('showCast', true);
		}
	},

	showChapters : true,

	showCast : false
});