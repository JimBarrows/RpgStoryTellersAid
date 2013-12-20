App.Router.map(function() {

		this.resource('stories', {path: '/'})

		this.resource('story', {path: '/story'}, function() {
				this.route('edit', {path: '/:story_id/edit'})
				this.route('new')
				this.resource('chapter', 
											{path: '/:story_id/chapter'}, 
											function() {
													this.route('new')
													this.route('edit', {path: '/:chapter_id/edit'})
													this.resource('scene',
																				{path: '/:chapter_id/scene'}, 
																				function() {
																						this.route('edit', {path: '/:scene_id/edit'})
																						this.route('new')
																				})
											})
		})
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
				var story = this.modelFor('chapter')
				var chapter = this.store.createRecord('chapter')
				story.get('chapters').pushObject( chapter)
				chapter.set('story', story)
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

App.SceneNewRoute = Ember.Route.extend({

		setupController: function( controller, model) {
				this.controllerFor('scene.edit').setProperties({isNew:true, content:model})
		},

		model: function(params) {
				var chapter = this.modelFor('scene')
				var scene = this.store.createRecord('scene')
				chapter.get('scenes').pushObject( scene)
				scene.set('chapter', chapter)
				return scene
		},

		renderTemplate: function() {
				this.render('scene.edit')
		}
})
