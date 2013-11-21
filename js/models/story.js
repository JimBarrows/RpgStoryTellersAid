App.Story = DS.Model.extend({
		name: DS.attr('string'),
		description: DS.attr('string'),
		setting: DS.attr('string'),
		status: DS.attr('string')
});

App.Story.FIXTURES =  [
		{ "id" : 1,
			"name" : "Story 1",
			"description" : "Story 1 Description",
			"setting" : "Story 1 setting",
			"status" : "Story 1 status",
			"chapters" : [{
					"id" : 0,
					"name" : "Ch 1 St 1",
					"description" : "Ch 1 St 1 Descrip",
					"scenes" : [{
							"id": 0,
							"name" : "Scene 1",
							"location" : "location 1",
							"playerdescription" : "player description 1",
							"gmdescription" : "gm description 1"
					}]
			}]},
		{ "id" : 2,
			"name" : "Story 2",
			"description" : "Story 2 Description",
			"setting" : "Story 2 setting",
			"status" : "Story 2 status"},
];
