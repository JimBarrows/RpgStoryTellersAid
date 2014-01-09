App.Router.map(function() {

	this.resource('stories', {
		path : '/'
	});

	this.route('story.new', {
		path : '/story/new'
	});

	this.resource('story', {
		path : '/story/:story_id'
	}, function() {

		this.route('edit');
		this.route('delete');

		this.resource('chapter.new', {
			path : '/chapter/new'
		});

		this.resource('chapter', {
			path : '/chapter/:chapter_id'
		}, function() {

			this.route('edit');
			this.route('delete');

			this.resource('scene.new', {
				path : '/scene/new'
			});

			this.resource('scene', {
				path : '/scene/:scene_id'
			}, function() {

				this.route('edit');
				this.route('delete');

				this.resource('clue.new', {
					path : '/clue/new'
				});

				this.resource('clue', {
					path : '/clue/:clue_id'
				}, function() {
					this.route('edit');
					this.route('delete');
				});
			});

		});
	});

});

App.StoriesRoute = Ember.Route.extend({
	model : function(params) {
		return this.store.find('story');
	}
});

App.StoryNewRoute = Ember.Route.extend({

	model : function(params) {
		return this.store.createRecord('story');
	}
});

App.StoryEditRoute = Ember.Route.extend({

	model : function(params) {
		return this.modelFor('story');
	}
});

App.StoryDeleteRoute = Ember.Route.extend({

	model : function(params) {
		return this.modelFor('story');
	},
	afterModel : function(resolvedModel, transition, queryParams) {
		resolvedModel.deleteRecord();
		resolvedModel.save();
		this.transitionTo('stories');
	}
});

App.ChapterNewRoute = Ember.Route.extend({

	model : function(params) {
		var chapter = this.store.createRecord('chapter');
		var story = this.modelFor('story');
		chapter.set('story', story);
		story.get('chapters').pushObject(chapter);
		return chapter;
	}
});

App.ChapterEditRoute = Ember.Route.extend({

	model : function(params) {
		return this.modelFor('chapter');
	}
});

App.ChapterDeleteRoute = Ember.Route.extend({

	model : function(params) {
		return this.modelFor('chapter');
	},
	afterModel : function(resolvedModel, transition, queryParams) {
		var story = this.modelFor('story');
		resolvedModel.set('story', null);
		story.get('chapters').removeObject(resolvedModel);
		resolvedModel.deleteRecord();
		resolvedModel.save();
		this.transitionTo('story.edit', story);
	}
});

App.SceneNewRoute = Ember.Route.extend({

	model : function(params) {
		var scene = this.store.createRecord('scene');
		var chapter = this.modelFor('chapter');
		scene.set('chapter', chapter);
		chapter.get('scenes').pushObject(scene);
		return scene;
	}
});

App.SceneEditRoute = Ember.Route.extend({
	model : function(params) {
		return this.modelFor('scene');
	}
});

App.SceneDeleteRoute = Ember.Route.extend({

	model : function(params) {
		return this.modelFor('scene');
	},
	afterModel : function(resolvedModel, transition, queryParams) {
		var chapter = this.modelFor('chapter');
		resolvedModel.set('chapter', null);
		chapter.get('scenes').removeObject(resolvedModel);
		resolvedModel.deleteRecord();
		resolvedModel.save();
		this.transitionTo('chapter.edit', chapter);
	}
});

App.ClueNewRoute = Ember.Route.extend({

	model : function(params) {
		var clue = this.store.createRecord('clue');
		var scene = this.modelFor('scene');
		clue.set('scene', scene);
		scene.get('clues').pushObject(clue);
		return clue;
	}
});

App.ClueEditRoute = Ember.Route.extend({
	model : function(params) {
		return this.modelFor('clue');
	}
});

App.ClueDeleteRoute = Ember.Route.extend({

	model : function(params) {
		return this.modelFor('clue');
	},
	afterModel : function(resolvedModel, transition, queryParams) {
		var scene = this.modelFor('scene');
		resolvedModel.set('scene', null);
		scene.get('clues').removeObject(resolvedModel);
		resolvedModel.deleteRecord();
		resolvedModel.save();
		this.transitionTo('scene.edit', scene);
	}
});
