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
												 $location.path("stories/edit/" + $scope.story.id)
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
										 var chapterIndex = -1
										 if( $scope.story.chapters){
												 chapterIndex = $scope.story.chapters.map( 
														 function( s) { 
																 return s.id;})
														 .indexOf( parseInt($routeParams.chapterId))
										 }

										 if( chapterIndex < 0) {
												 $scope.chapter = null
												 $scope.title = "New Chapter for " + $scope.story.name
										 } else {
												 $scope.chapter = $scope.story.chapters[chapterIndex]
												 $scope.title = "Edit " + $scope.chapter.name + " for story " +$scope.story.name
										 }

										 $scope.saveChapter = function() {
												 storySvc.saveChapter( $scope.story, $scope.chapter)
												 $location.path("stories/edit/" + $scope.story.id + "/chapters/" +$scope.chapter.id)
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

										 $scope.editScene = function( scene) {
												 $location.path("stories/edit/" 
																				+ $scope.story.id 
																				+ "/chapters/" 
																				+ $scope.chapter.id 
																				+ "/scenes/"
																				+ scene.id)
										 }

										 $scope.deleteScene = function( scene) {
												 storySvc.deleteScene( $scope.story, $scope.chapter, scene)
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
												 scene = chapter.scenes[sceneIndex]
												 $scope.title = "Edit Scene " + scene.name
										 }

										 $scope.save = function() {
												 storySvc.saveScene( story, chapter, $scope.scene)
												 $location.path("stories/edit/" + story.id + "/chapters/" + chapter.id + "/scenes/" + $scope.scene.id)
										 }

										 $scope.done = function() {
												 $location.path("stories/edit/" + story.id + "/chapters/" + chapter.id )
										 }

										 $scope.addClue = function() {
												 $location.path("stories/edit/" 
																				+ story.id 
																				+ "/chapters/" 
																				+ chapter.id
																				+ "/scenes/"
																				+ $scope.scene.id
																				+ "/clues/new")
										 }

										 $scope.editClue = function( clue) {
												 $location.path("stories/edit/" 
																				+ story.id 
																				+ "/chapters/" 
																				+ chapter.id
																				+ "/scenes/"
																				+ $scope.scene.id
																				+ "/clues/"
																				+ clue.id)
										 }
										 
										 $scope.deleteClue = function( clue) {
												 storySvc.deleteClue( story, chapter, scene, clue)
										 }

										 $scope.scene = scene
								 }])

		.controller('ClueFormController', 
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
										 var scene = chapter.scenes[ sceneIndex]
										 var clueIndex = -1
										 if( scene.clues) {
												 clueIndex = scene.clues.map(
														 function( s) {
																 return s.id;
														 }).indexOf( parseInt( $routeParams.clueId))
										 }
										 var clue = null
										 if( clueIndex < 0) {
												 $scope.title = "New Clue for Scene " + scene.name 
										 } else {
												 clue = scene.clues[clueIndex]
												 $scope.title = "Edit clue " + clue.name
										 }

										 $scope.save = function() {
												 storySvc.saveClue( story, chapter, scene, $scope.clue)
												 $location.path("stories/edit/" 
																				+ story.id 
																				+ "/chapters/" 
																				+ chapter.id 
																				+ "/scenes/" 
																				+ scene.id
																				+ "/clues/"
																				+ $scope.clue.id)
										 }

										 $scope.done = function() {
												 $location.path("stories/edit/" 
																				+ story.id 
																				+ "/chapters/" 
																				+ chapter.id
																				+ "/scenes/"
																				+ scene.id)
										 }
										 $scope.clue = clue
								 }])
