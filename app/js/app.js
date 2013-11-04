'use strict';


// Declare app level module which depends on filters, and services
angular.module('storyTellersAid', [
		'ui.bootstrap',
		'storyTellersAid.filters', 
		'storyTellersAid.services', 
		'storyTellersAid.directives', 
		'storyTellersAid.controllers'])

	.config(['$routeProvider', function($routeProvider) {
    $routeProvider
    	.when('/stories', 
    		{templateUrl: 'partials/stories.html', 
    		controller: 'StoryController'});
    $routeProvider.when('/stories/new', {templateUrl: 'partials/newStory.html'}),
    $routeProvider.when('/stories/edit/:id', {templateUrl: 'partials/editStory.html'}),
    $routeProvider.when('/stories/edit/:id/chapters/new', {templateUrl: 'partials/chapterForm.html'}),
    $routeProvider.when('/stories/edit/:id/chapters/:chapterId', {templateUrl: 'partials/chapterForm.html'}),
    $routeProvider.when('/stories/new/chapters/new', {templateUrl: 'partials/newChapter.html'}),
    $routeProvider.when('/stories/edit/:id/chapters/:chapterId/scenes/new', {templateUrl: 'partials/sceneForm.html'}),
    $routeProvider.otherwise({redirectTo: '/stories'});
  }]);
