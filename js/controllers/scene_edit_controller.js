App.SceneEditController = Ember.ObjectController.extend({

		actions: {

				saveScene: function( scene) {
						if (this.get('isNew')) {
								scene.get('chapter').get('scenes').pushObject( scene)
						}
						scene.save()
				},

				doneScene: function(scene) {
						this.transitionToRoute("scene.edit", scene.get('chapter'))
				},

				cancelScene: function( scene) {
						if( ! this.get('isNew')) {
								scene.rollback();
						}
				}
		},

		isNew: function() {
				return false
		}.property()

})
