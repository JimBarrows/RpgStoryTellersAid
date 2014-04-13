from django.views.generic.list import ListView
from django.views.generic.edit import CreateView
from django.views.generic.detail import DetailView
from django.views.generic.edit import UpdateView, DeleteView
from django.core.urlresolvers import reverse_lazy
from django.http import HttpResponseRedirect


from stories.models import Story, Chapter
from stories.forms import ChapterFormSet

class StoryList(ListView):
	model = Story

class StoryCreate(CreateView):
	model = Story
	fields = ['name', 'description']

class StoryDetail(DetailView):
	model = Story

class StoryEdit(UpdateView):
	model = Story
	fields = ['name', 'description']

class StoryDelete(DeleteView):
	model = Story
	success_url = reverse_lazy('story-list')
	
class ChapterCreate(CreateView):
	model = Chapter	

class ChapterDetail(DetailView):
	model = Chapter
			
class ChapterEdit(UpdateView):
	model = Chapter

class ChapterDelete(DeleteView):
	model = Chapter
	success_url = reverse_lazy('story-list')
	
	
