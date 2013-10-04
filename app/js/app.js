'use strict';


// Declare app level module which depends on filters, and services
angular.module('storyTellersAid', [
		'storyTellersAid.filters', 
		'storyTellersAid.services', 
		'storyTellersAid.directives', 
		'storyTellersAid.controllers'])

	.config(['$routeProvider', function($routeProvider) {
    $routeProvider
    	.when('/stories', 
    		{templateUrl: 'partials/stories.html', 
    		controller: 'StoryController'});
    $routeProvider.when('/stories/new', {templateUrl: 'partials/newStory.html', 
    																			controller: 'StoryFormController'});
    $routeProvider.when('/stories/edit/:id', {templateUrl: 'partials/newStory.html', 
    																			controller: 'StoryFormController'});
    $routeProvider.when('/stories/new/chapters/new', {templateUrl: 'partials/newChapter.html', 
    																									controller: 'StoryController'});
    $routeProvider.when('/stories/new/chapters/new/scenes/new', {templateUrl: 'partials/newScene.html', 
    																															controller: 'ClueTable'});
    $routeProvider.otherwise({redirectTo: '/stories'});
  }]);
