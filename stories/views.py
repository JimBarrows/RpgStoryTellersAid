from django.views.generic.list import ListView
from django.views.generic.edit import CreateView
from django.views.generic.detail import DetailView
from django.views.generic.edit import UpdateView, DeleteView
from django.core.urlresolvers import reverse_lazy


from stories.models import Story, Chapter, Scene, Clue, CastMember

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
	
class SceneCreate(CreateView):
	model = Scene	

class SceneDetail(DetailView):
	model = Scene
			
class SceneEdit(UpdateView):
	model = Scene

class SceneDelete(DeleteView):
	model = Scene
	success_url = reverse_lazy('story-list')
	
class ClueCreate(CreateView):
	model = Clue	

class ClueDetail(DetailView):
	model = Clue
			
class ClueEdit(UpdateView):
	model = Clue

class ClueDelete(DeleteView):
	model = Clue
	success_url = reverse_lazy('story-list')
	
class CastMemberCreate(CreateView):
	model = CastMember	

class CastMemberDetail(DetailView):
	model = CastMember
			
class CastMemberEdit(UpdateView):
	model = CastMember

class CastMemberDelete(DeleteView):
	model = CastMember
	success_url = reverse_lazy('story-list')

