App.SceneFormController = Ember.ObjectController.extend({
	actions : {
		done : function() {
			this.transitionToRoute("chapter.edit");
		},

		cancel : function() {
			this.get('model').rollback();
		}
	}
});

App.SceneNewController = App.SceneFormController.extend({
	actions : {

		save : function() {
			var scene = this.get('model');
			var dis = this;
			scene.save().then(function(scene) {
				dis.transitionToRoute('scene.edit', scene);
			});
		}
	}
});

App.SceneEditController = App.SceneFormController.extend({
	actions : {

		save : function() {
			var Scene = this.get('model');
			Scene.save();
		},		

		showClueTab : function() {
			this.set('showClues', true);
			this.set('showCast', false);
		},

		showCastTab : function() {
			this.set('showClues', false);
			this.set('showCast', true);
		}
	},

	showClues : true,

	showCast : false
});