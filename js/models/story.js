App.Story = DS.Model.extend({
		name: DS.attr('string'),
		description: DS.attr('string'),
		setting: DS.attr('string'),
		status: DS.attr('string'),
		chapters: DS.hasMany('chapter',{async: true}),
		cast: DS.hasMany('actor', {async: true})
});

App.Chapter = DS.Model.extend({
		name: DS.attr('string'),
		number: DS.attr('number'),
		description: DS.attr('string'),
		story: DS.belongsTo('story'),
		scenes: DS.hasMany('scene',{async: true})
});

App.Scene = DS.Model.extend({
		name: DS.attr('string'),
		number: DS.attr('number'),
		location: DS.attr('string'),
		playerDescription: DS.attr('string'),
		gmDescription: DS.attr('string'),
		chapter: DS.belongsTo('chapter'),
		cast: DS.hasMany('actor', {async: true})
})

App.Actor = DS.Model.extend({
		name: DS.attr('string'),
		role: DS.attr('string'),
		highConcept: DS.attr('string'),
		trouble: DS.attr('string'),
		scenes: DS.hasMany('scene'),
		story: DS.belongsTo('story')
});

App.Story.FIXTURES =  [
		{ id : 1,
			name : "Story 1",
			description : "Story 1 Description",
			setting : "Story 1 setting",
			status : "Story 1 status",
			chapters : [1],
			cast: [1, 2, 3, 4]
		},
		{ "id" : 2,
			"name" : "Story 2",
			"description" : "Story 2 Description",
			"setting" : "Story 2 setting",
			"status" : "Story 2 status",
			cast: [5,6,7,8]
		},
];

App.Chapter.FIXTURES =  [
		{
				id : 1,
				number: 1,
				name : "Ch 1 St 1",
				description : "Ch 1 St 1 Descrip",
				scenes : [0, 1],
				story:1
		}
]

App.Scene.FIXTURES =  [
		{
				id: 0,
				name : "Scene 1",
				number : 1,
				location : "location 1",
				playerdescription : "player description 1",
				gmdescription : "gm description 1",
				cast: [1,2]
		},
		{
				id: 1,
				name : "Scene 2",
				number: 2,
				location : "location 2",
				playerdescription : "player description 2",
				gmdescription : "gm description 2",
				cast: [3,4]
		}
]

App.Actor.FIXTURES = [
		{
				id: 1,
				name: 'Actor 1',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		},
		{
				id: 2,
				name: 'Actor 2',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		},
		{
				id: 3,
				name: 'Actor 3',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		},
		{
				id: 4,
				name: 'Actor 4',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		},
		{
				id: 5,
				name: 'Actor 5',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		},
		{
				id: 6,
				name: 'Actor 6',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		},
		{
				id: 7,
				name: 'Actor 7',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		},
		{
				id: 8,
				name: 'Actor 8',
				role: 'Role',
				highConcept:'High Concept',
				trouble: 'trouble'
		}
]