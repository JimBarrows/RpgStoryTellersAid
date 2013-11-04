'use strict';

/* Controllers */

angular.module('storyTellersAid.controllers', [])

		.controller('StoryController', 
								['$scope', 
								 'storyService', 
								 function($scope, storySvc) {

										 $scope.stories =  storySvc.list();
										 $scope.delete = function( story) {
												 storySvc.delete( story);
												 $scope.stories =  storySvc.list();
										 }
										 
								 }])

		.controller('StoryFormController', 
								['$scope', 
								 '$routeParams', 
								 '$location',
								 'storyService',

								 function($scope, $routeParams,$location,storySvc) {

										 if( typeof $routeParams.id === "undefined") {
												 $scope.story = null
										 } else {
												 $scope.story = storySvc.find( $routeParams.id)
										 }

										 $scope.saveStory = function() {
												 storySvc.save( $scope.story)
										 }

										 $scope.cancel = function() {
												 $scope.story = storySvc.find( $routeParams.id)	
										 }

										 $scope.addChapter = function() {
												 $location.path("stories/edit/" + $scope.story.id + "/chapters/new")
										 }

										 $scope.editChapter = function( chapter) {
												 $location.path("stories/edit/" + $scope.story.id + "/chapters/" + chapter.id)
										 }

										 $scope.deleteChapter = function( chapter) {

												 storySvc.deleteChapter( $scope.story, chapter)
												 
										 }
								 }])

		.controller('ChapterFormController',
								['$scope',
								 '$routeParams',
								 '$location',
								 'storyService',

								 function( $scope, $routeParams, $location, storySvc) {
										 $scope.story = storySvc.find( $routeParams.id)
										 var chapterIndex = $scope.story.chapters.map( 
												 function( s) { 
														 return s.id;})
												 .indexOf( parseInt($routeParams.chapterId))

										 if( chapterIndex < 0) {
												 $scope.chapter = null
												 $scope.title = "New Chapter for " + $scope.story.name
										 } else {
												 $scope.chapter = $scope.story.chapters[chapterIndex]
												 $scope.title = "Edit " + $scope.chapter.name + " for story " +$scope.story.name
										 }

										 $scope.saveChapter = function() {
												 storySvc.saveChapter( $scope.story, $scope.chapter)
										 }

										 $scope.done = function() {
												 $location.path("stories/edit/" + $scope.story.id )
										 }

										 $scope.addScene = function() {
												 $location.path("stories/edit/" 
																				+ $scope.story.id 
																				+ "/chapters/" 
																				+ $scope.chapter.id 
																				+ "/scenes/new" )
										 }

								 }])

		.controller('SceneFormController',
								['$scope',
								 '$routeParams',
								 '$location',
								 'storyService',

								 function( $scope, $routeParams, $location, storySvc) {
										 var story = storySvc.find( $routeParams.id)
										 var chapterIndex = story.chapters.map( 
												 function( s) {
														 return s.id;
												 }).indexOf( parseInt($routeParams.chapterId))
										 var chapter = story.chapters[ chapterIndex]
										 var sceneIndex = chapter.scenes.map(
												 function( s) {
														 return s.id
												 }).indexOf( parseInt($routeParams.sceneId))
										 var scene = null
										 if( sceneIndex < 0) {
												 $scope.title = "New Scene for Chapter " + chapter.name 
										 } else {
												 scene = chapter.scenese[sceneIndex]
												 $scope.title = "Edit Scene " + scene.name
										 }

										 $scope.save = function() {
												 storySvc.saveScene( story, chapter, $scope.scene)
										 }

										 $scope.done = function() {
												 $location.path("stories/edit/" + story.id + "/chapters/" + chapter.id )
										 }

										 $scope.scene = scene
								 }])

		.controller('ClueTable', 
								['$scope', 
								 function($scope) {
										 $scope.clues = [
												 { "number" : "1",
													 "description" : "New Description",
													 "location" : "Somewhere"},
												 { "number" : "2",
													 "description" : "Another Description",
													 "location" : "Somewhere"}
										 ];
								 }]);
