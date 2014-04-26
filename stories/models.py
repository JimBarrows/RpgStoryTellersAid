from django.core.urlresolvers import reverse
from django.contrib.auth.models import User
from django.db import models

class Story(models.Model):
	name = models.CharField(max_length=200)
	description = models.TextField()
	created_by = models.ForeignKey(User)
	
	def get_absolute_url(self):
		return reverse('story-detail', kwargs={'pk': self.pk})
	
	def __str__(self):
		return self.name


class Chapter(models.Model):
	number = models.IntegerField()
	title = models.CharField(max_length=200)
	description = models.TextField()
	story = models.ForeignKey('Story')
	
	def get_absolute_url (self):
		return reverse('chapter-detail', kwargs={'pk': self.pk})
	
	def __str__(self):
		return self.title

	class Meta:
		ordering = ['number', 'title']


class Scene(models.Model):
	number = models.IntegerField()
	title = models.CharField(max_length=200)
	description = models.TextField()
	chapter = models.ForeignKey('Chapter')
	
	def get_absolute_url(self):
		return reverse('scene-detail', kwargs={'pk': self.pk})
	
	def __str__(self):
		return self.title

	class Meta:
		ordering = ['number', 'title']

class Clue(models.Model):
	title = models.CharField(max_length=200)
	description = models.TextField()
	scene = models.ForeignKey('Scene')
	
	def get_absolute_url(self):
		return reverse('clue-detail', kwargs={'pk': self.pk})
	
	def __str__(self):
		return self.title

	class Meta:
		ordering = ['title', ]

class CastMember(models.Model):
	title = models.CharField(max_length=200)
	description = models.TextField()
	scene = models.ForeignKey('Scene')
	
	def get_absolute_url(self):
		return reverse('castmember-detail', kwargs={'pk': self.pk})
	
	def __str__(self):
		return self.title

	class Meta:
		ordering = ['title', ]


class Location(models.Model):
	name = models.CharField(max_length=200)
	description = models.TextField()
	scene = models.ForeignKey('Scene')
	map  = models.FileField(blank=True, upload_to="location_maps")
	
	def get_absolute_url(self):
		return reverse('location-detail', kwargs={'pk': self.pk})
	
	def __str__(self):
		return self.name

	class Meta:
		ordering = ['name', ]
