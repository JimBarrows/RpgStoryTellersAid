'use strict';

/* Services */

var stories = [
		{ "id" : "1",
			"name" : "Story 1",
			"description" : "Story 1 Description",
			"setting" : "Story 1 setting",
			"status" : "Story 1 status",
			"chapters" : []},
		{ "id" : "2",
			"name" : "Story 2",
			"description" : "Story 2 Description",
			"setting" : "Story 2 setting",
			"status" : "Story 2 status"},
];

var chapter = {
		"id" : "1",
		"name" : "some name",
		"description" : "description"
}

var nextStoryId=3


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
  							if( this.find( story.id)) {
  									var index =stories.map(function( s) {return s.id;}).indexOf(story.id)
										stories[index] = story
  							} else {
  									story.id = nextStoryId
  									nextStoryId += 1
  									stories.push( story)
  							}
  							_.sortBy( stories, function( story) { return story.id})
  					},

						saveChapter: function( story, chapter) {
								var foundAt = story.chapters.map( 
										function(s) {
												return s.id;})
										.indexOf( chapter.id)
								if( foundAt < 0) {
										var nextId = story.chapters.length
										chapter.id = nextId
										chapter.scenes = []
										story.chapters.push( chapter)
								} else {
										story.chapters[foundAt] = chapter
								}
								this.save( story)
						},
						
  					delete: function( story) {
								var index =	stories.map(function (s) {return s.id;}).indexOf( story.id)
  							if( index > -1) {
  									stories.splice( index, 1)
  							}
  					}
  			}
		});
