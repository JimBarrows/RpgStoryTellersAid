App.ActorFormController = Ember.ObjectController.extend({
	actions : {
		done : function() {
			this.transitionToRoute("scene.edit");
		},

		cancel : function() {
			this.get('model').rollback();
		}
	}
});

App.ActorNewController = App.ActorFormController.extend({
	actions : {

		save : function() {
			var actor = this.get('model');
			var dis = this;
			actor.save().then(function(actor) {
				dis.transitionToRoute('actor.edit', actor);
			});
		}
	}
});

App.ActorEditController = App.ActorFormController.extend({
	actions : {

		save : function() {
			var Actor = this.get('model');
			Actor.save();
		},

	}
});