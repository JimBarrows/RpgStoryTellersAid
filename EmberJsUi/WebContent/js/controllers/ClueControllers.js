App.ClueFormController = Ember.ObjectController.extend({
	actions : {
		done : function() {
			this.transitionToRoute("scene.edit");
		},

		cancel : function() {
			this.get('model').rollback();
		}
	}
});

App.ClueNewController = App.ClueFormController.extend({
	actions : {

		save : function() {
			var clue = this.get('model');
			var dis = this;
			clue.save().then(function(clue) {
				dis.transitionToRoute('clue.edit', clue);
			});
		}
	}
});

App.ClueEditController = App.ClueFormController.extend({
	actions : {

		save : function() {
			var Clue = this.get('model');
			Clue.save();
		},

	}
});