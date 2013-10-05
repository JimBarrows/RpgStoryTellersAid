'use strict';

/* Controllers */

angular.module('storyTellersAid.controllers', [])

	.controller('StoryController', ['$scope', 
																	'storyService', 
		function($scope, storySvc) {

			$scope.stories =  storySvc.list();
			$scope.delete = function( story) {
				storySvc.delete( story);
				$scope.stories =  storySvc.list();
			}

		}])

	.controller('StoryFormController', ['$scope', '$routeParams', 'storyService',

		function($scope, $routeParams,storySvc) {

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

		}])

  .controller('ClueTable', ['$scope', function($scope) {
		$scope.clues = [
			{ "number" : "1",
				"description" : "New Description",
				"location" : "Somewhere"},
			{ "number" : "2",
				"description" : "Another Description",
				"location" : "Somewhere"}
		];
  }]);
