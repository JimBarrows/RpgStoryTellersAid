from django.shortcuts import render
from django import forms
from django.views.generic.list import ListView
from django.views.generic.edit import CreateView
from django.views.generic.detail import DetailView
from django.views.generic.edit import UpdateView, DeleteView
from django.core.urlresolvers import reverse_lazy


from stories.models import Story

class StoryList(ListView):
	model = Story

class StoryCreate(CreateView):
	model = Story


class StoryDetail(DetailView):
    model = Story

class StoryEdit(UpdateView):
    model = Story

class StoryDelete(DeleteView):
    model = Story
    success_url = reverse_lazy('story-list')