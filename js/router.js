App.Router.map(function() {

		this.resource('stories', {path: '/'})

		this.resource('story', function() {
				this.route('new', {path: '/new'})
				this.route('view', {path: '/:story_id'})
				this.route('edit', {path: '/:story_id/edit'})
		})
});

App.StoriesIndexRoute = Ember.Route.extend({
		model: function() {
				return this.modelfFor('stories')
		}
});

App.StoriesRoute = Ember.Route.extend({
		actions: {
				addStory: function() {
						this.transitionTo('/story/new')
				}	
		},

		model: function(params) {
				return this.store.find('story')
		}
});


App.StoryNewRoute = Ember.Route.extend({
		setupController: function(controller, model) {
        this.controllerFor('story.form').setProperties({
						isNew: true,
						content: this.store.createRecord('story')})
		},
		renderTemplate: function() {
				this.render('storyForm',{into:'application'});
		}
})
