from django.core.urlresolvers import reverse
from django.db import models

class Story( models.Model):
	name = models.CharField(max_length=200)
	description = models.TextField()

	def get_absolute_url(self):
		return reverse('story-detail', kwargs={'pk': self.pk})

class Chapter( models.Model):
	number = models.IntegerField()
	title = models.CharField(max_length=200)
	description = models.TextField()
	story = models.ForeignKey('Story')

	class Meta:
		ordering = ['number',]

class Scene( models.Model):
	number = models.IntegerField()
	title = models.CharField(max_length=200)
	description = models.TextField()
	chapter = models.ForeignKey('Chapter')

	class Meta:
		ordering = ['number',]

class Clue( models.Model):
	title = models.CharField(max_length=200)
	description = models.TextField()
	scene = models.ForeignKey('Scene')

	class Meta:
		ordering = ['title',]

class CastMember( models.Model):
	title = models.CharField(max_length=200)
	description = models.TextField()
	scene = models.ForeignKey('Scene')

	
	class Meta:
		ordering = ['title',]