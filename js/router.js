App.Router.map(function() {

		this.resource('stories', {path: '/'})

		this.resource('story', {path: '/story'}, function() {
				this.route('edit', {path: '/:story_id'})
				this.route('new')
		})

		this.resource('chapter', {path:"/story/:story_id/chapter"}, function() {
				this.route('edit', {path: '/:chapter_id/edit'})
				this.resource('scene', {path:":chapter_id/scene"}, function() {
						this.route('edit', {path: '/:scene_id/edit'})
				})
		})

		this.resource('chapter.new', {path:'/story/:story_id/chapter/new'})
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

App.ChapterEditRoute = Ember.Route.extend({
		model: function(params) {
				return this.store.find('chapter',params.chapter_id)
		}
})

App.ChapterNewRoute = Ember.Route.extend({

		setupController: function( controller, model) {
				this.controllerFor('chapter.edit').setProperties({isNew:true, content:model})
		},

		model: function(params) {
				var chapter = this.store.createRecord('chapter')
				this.store.find('story', params.story_id).then(function( story) {
						chapter.set('story', story)
						story.get('chapters').push(chapter)
				})
						
				return chapter
		},

		renderTemplate: function() {
				this.render('chapter.edit')
		}
})

App.SceneEditRoute = Ember.Route.extend({
		model: function(params) {
				return this.store.find('scene',params.scene_id)
		}
})