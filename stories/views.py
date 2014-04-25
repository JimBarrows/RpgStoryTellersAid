from django.views.generic.list import ListView
from django.views.generic.edit import CreateView
from django.views.generic.detail import DetailView
from django.views.generic.edit import UpdateView, DeleteView
from django.contrib.auth.decorators import login_required
from django.utils.decorators import method_decorator
from django.core.urlresolvers import reverse, reverse_lazy


from stories.models import Story, Chapter, Scene, Clue, CastMember

class StoryList(ListView):
	model = Story
	
	def get_queryset(self):
		return Story.objects.filter(created_by=self.request.user)
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(StoryList, self).dispatch(*args, **kwargs)	

class StoryCreate(CreateView):
	model = Story
	fields = ['name', 'description']

	def form_valid(self, form):
		form.instance.created_by = self.request.user
		return super(StoryCreate, self).form_valid(form)
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(StoryCreate, self).dispatch(*args, **kwargs)

class StoryDetail(DetailView):
	model = Story
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(StoryDetail, self).dispatch(*args, **kwargs)

class StoryEdit(UpdateView):
	model = Story
	fields = ['name', 'description']
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(StoryEdit, self).dispatch(*args, **kwargs)

class StoryDelete(DeleteView):
	model = Story
	success_url = reverse_lazy('story-list')
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(StoryDelete, self).dispatch(*args, **kwargs)
	
class ChapterCreate(CreateView):
	model = Chapter	
	fields = ['number', 'title', 'description']
	
	def get_success_url(self):
		return reverse('story-edit', kwargs={ 'pk' : self.object.story_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ChapterCreate, self).dispatch(*args, **kwargs)
	
	def get_initial(self):
		initial = super(ChapterCreate, self).get_initial()
		story_pk = int(self.kwargs['story_pk'])
		parent_story = Story.objects.get(pk=story_pk)
		initial['number'] = parent_story.chapter_set.count() + 1
		initial['story'] = parent_story		
		return initial

	def form_valid(self, form):
		form.instance.story = form.initial['story']
		return super(ChapterCreate, self).form_valid(form)
	
class ChapterDetail(DetailView):
	model = Chapter
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ChapterDetail, self).dispatch(*args, **kwargs)
			
class ChapterEdit(UpdateView):
	model = Chapter
	fields = ['number', 'title', 'description']
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ChapterEdit, self).dispatch(*args, **kwargs)	
	
	def get_success_url(self):
		return reverse('story-edit', kwargs={ 'pk' : self.object.story_id})

class ChapterDelete(DeleteView):
	model = Chapter
	success_url = reverse_lazy('story-list')
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ChapterDelete, self).dispatch(*args, **kwargs)
	
	def get_success_url(self):
		return reverse('story-edit', kwargs={ 'pk' : self.object.story_id})
		
class SceneCreate(CreateView):
	model = Scene	
	fields = ['number', 'title', 'description']
	
	def get_success_url(self):
		return reverse('chapter-edit', kwargs={ 'story_pk' : self.object.chapter.story.id,
																							'pk' : self.object.chapter_id})
		
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(SceneCreate, self).dispatch(*args, **kwargs)
	
	def get_initial(self):
		initial = super(SceneCreate, self).get_initial()
		chapter_pk = int(self.kwargs['chapter_pk'])
		parent_chapter = Chapter.objects.get(pk=chapter_pk)
		initial['number'] = parent_chapter.scene_set.count() + 1
		initial['chapter'] = parent_chapter
		return initial
	
	def form_valid(self, form):
		form.instance.chapter = form.initial['chapter']
		return super(SceneCreate, self).form_valid(form)
		
class SceneDetail(DetailView):
	model = Scene
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(SceneDetail, self).dispatch(*args, **kwargs)
			
class SceneEdit(UpdateView):
	model = Scene
	fields = ['number', 'title', 'description']
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(SceneEdit, self).dispatch(*args, **kwargs)
	
	def get_success_url(self):
		return reverse('chapter-edit', kwargs={ 'story_pk' : self.object.chapter.story.id,
																							'pk' : self.object.chapter_id})

class SceneDelete(DeleteView):
	model = Scene
	
	def get_success_url(self):
		return reverse('chapter-edit', kwargs={ 'story_pk' : self.object.chapter.story.id,
																							'pk' : self.object.chapter_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(SceneDelete, self).dispatch(*args, **kwargs)
	
class ClueCreate(CreateView):
	model = Clue		
	fields = ['title', 'description']
	
	def get_success_url(self):
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
		
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ClueCreate, self).dispatch(*args, **kwargs)
	
	def get_initial(self):
		initial = super(ClueCreate, self).get_initial()
		scene_pk = int(self.kwargs['scene_pk'])
		parent_scene = Scene.objects.get(pk=scene_pk)		
		initial['scene'] = parent_scene
		return initial
	
	def form_valid(self, form):
		form.instance.scene = form.initial['scene']
		return super(ClueCreate, self).form_valid(form)	

class ClueDetail(DetailView):
	model = Clue
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ClueDetail, self).dispatch(*args, **kwargs)
			
class ClueEdit(UpdateView):
	model = Clue
	fields = ['title', 'description']
	
	def get_success_url(self):
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ClueEdit, self).dispatch(*args, **kwargs)

class ClueDelete(DeleteView):
	model = Clue
	
	def get_success_url(self):
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ClueDelete, self).dispatch(*args, **kwargs)
	
class CastMemberCreate(CreateView):
	model = CastMember		
	fields = ['title', 'description']
	def get_success_url(self):		
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
		
		
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(CastMemberCreate, self).dispatch(*args, **kwargs)
	
	def get_initial(self):
		initial = super(CastMemberCreate, self).get_initial()
		scene_pk = int(self.kwargs['scene_pk'])
		parent_scene = Scene.objects.get(pk=scene_pk)		
		initial['scene'] = parent_scene
		return initial
	
	def form_valid(self, form):
		form.instance.scene = form.initial['scene']
		return super(CastMemberCreate, self).form_valid(form)

class CastMemberDetail(DetailView):
	model = CastMember
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(CastMemberDetail, self).dispatch(*args, **kwargs)
			
class CastMemberEdit(UpdateView):
	model = CastMember
	
	fields = ['title', 'description']
	
	def get_success_url(self):		
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
		
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(CastMemberEdit, self).dispatch(*args, **kwargs)

class CastMemberDelete(DeleteView):
	model = CastMember
	
	def get_success_url(self):
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(CastMemberDelete, self).dispatch(*args, **kwargs)

