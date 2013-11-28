App.Router.map(function() {

		this.resource('stories', {path: '/'})

		this.resource('story', {path: '/story'}, function() {
				this.route('edit', {path: '/:story_id'})
				this.route('new')
		})

		this.resource('chapter', {path:"/chapter"})
});

App.StoriesRoute = Ember.Route.extend({
		actions: {
				addStory: function() {
						this.transitionTo('story.new')
				}	
		},

		model: function(params) {
				return this.store.find('story')
		}
});

App.StoryNewRoute = Ember.Route.extend({
		setupController: function( controller, model) {
				this.controllerFor('story.edit').setProperties({isNew:true, content:model})
		},
		model: function(params) {
				return this.store.createRecord('story')
		},
		renderTemplate: function() {
				this.render('story.edit')
		}
})

App.ChapterRoute = Ember.Route.extend({
		model: function(params) {
				return this.store.find('story',params.story_id)
		}
})
