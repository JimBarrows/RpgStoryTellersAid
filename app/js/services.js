'use strict';

/* Services */

var stories = [
	{ "id" : "1",
		"name" : "Blah Storye",
		"description" : "Foo New Description",
		"setting" : "A setting",
		"status" : "Somewhere"},
	{ "id" : "2",
		"name" : "Blah Storye",
		"description" : "Foo New Description",
		"setting" : "A setting",
		"status" : "Somewhere"},
	];


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('storyTellersAid.services', []).
  value('version', '0.1')
  .factory('storyService', function() {
  	return {

  		list: function() { return stories;},

  		find: function( id) {
  			for( var i=0; i< stories.length;i++) {
  				if( stories[i].id == id) return stories[i]
  			}
  			return null;
  		},

  		save: function( story) {
  			if( find( story.id)) {
  				find( story.id) = story
  			} else {
  				stories.push( story)
  			}
  			_.sortBy( stories, function( story) { return story.id})
  		},

  		delete: function( story) {
  			var index = _.indexOf( stories, story, true)
  			if( index > -1) {
  				stories.splice( index, 1)
  			}
  		}
  	}
  });
