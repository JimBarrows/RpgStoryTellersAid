from django.views.generic.list import ListView
from django.views.generic.edit import CreateView
from django.views.generic.detail import DetailView
from django.views.generic.edit import UpdateView, DeleteView
from django.contrib.auth.decorators import login_required
from django.utils.decorators import method_decorator
from django.core.urlresolvers import reverse, reverse_lazy
from django.http import HttpResponseRedirect

from stories.models import Story, Chapter, Scene, Clue, CastMember, Location
from stories.forms import SceneCastMemberFormset

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
		return initial
	
	def get_context_data(self, **kwargs) :
		context = super(ChapterCreate, self).get_context_data(**kwargs)
		story_pk = int(self.kwargs['story_pk'])
		parent_story = Story.objects.get(pk=story_pk)
		context['story'] = parent_story
		return context

	def form_valid(self, form):
		form.instance.story = self.get_context_data()['story']
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
	
	def get_context_data(self, **kwargs) :
		context = super(ChapterEdit, self).get_context_data(**kwargs)		
		story_pk = int(self.kwargs['story_pk'])
		parent_story = Story.objects.get(pk=story_pk)
		context['story'] = parent_story
		return context	
		

class ChapterDelete(DeleteView):
	model = Chapter
	success_url = reverse_lazy('story-list')
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ChapterDelete, self).dispatch(*args, **kwargs)
	
	def get_success_url(self):
		return reverse('story-edit', kwargs={ 'pk' : self.object.story_id})
	
	def get_context_data(self, **kwargs) :
		context = super(ChapterDelete, self).get_context_data(**kwargs)
		story_pk = int(self.kwargs['story_pk'])
		parent_story = Story.objects.get(pk=story_pk)
		context['story'] = parent_story
		return context
		
		
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
		return initial
	
	def form_valid(self, form):
		form.instance.chapter = self.get_context_data()['chapter']
		return super(SceneCreate, self).form_valid(form)
	
	def get_context_data(self, **kwargs) :
		context = super(SceneCreate, self).get_context_data(**kwargs)		
		chapter_pk = int(self.kwargs['chapter_pk'])
		chapter = Chapter.objects.get(pk=chapter_pk)
		context['chapter'] = chapter
		context['story'] = chapter.story
		return context
		
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

	def get_context_data(self, **kwargs) :
		context = super(SceneEdit, self).get_context_data(**kwargs)		
		chapter_pk = int(self.kwargs['chapter_pk'])
		chapter = Chapter.objects.get(pk=chapter_pk)
		context['chapter'] = chapter
		context['story'] = chapter.story
		return context
	
	def get(self, request, *args, **kwargs):
		scene_pk = int(self.kwargs['pk'])
		self.object = Scene.objects.get(pk=scene_pk)
		form_class = self.get_form_class()
		form = self.get_form(form_class)
		scene_cast_members = self.object.scenecastmember_set.all().values()
		scenecastmember_formset = SceneCastMemberFormset(initial= scene_cast_members, instance=self.object)
		return self.render_to_response(
            self.get_context_data(form=form,
                                  scenecastmember_formset=scenecastmember_formset))

	def post(self, request, *args, **kwargs):		
		self.object = None
		form_class = self.get_form_class()
		form = self.get_form(form_class)
		scenecastmember_formset = SceneCastMemberFormset(request.POST,)
		if (form.is_valid() and scenecastmember_formset.is_valid() ):
			return self.form_valid(form, scenecastmember_formset)
		else:
			return self.form_invalid(form, scenecastmember_formset)
	
	def form_valid(self, form, scenecastmember_formset):        
		self.object = form.save( commit=False)
		self.object.pk = self.kwargs['pk']
		self.object.chapter = self.get_context_data()['chapter'] 
		self.object.save()		
		scenecastmember_formset.instance = self.object
		scenecastmember_formset.save()		
		return HttpResponseRedirect(self.get_success_url())

	def form_invalid(self, form, scenecastmember_formset):
		return self.render_to_response(
																	self.get_context_data(form=form,
																											scenecastmember_formset=scenecastmember_formset))

	
	
class SceneDelete(DeleteView):
	model = Scene
	
	def get_success_url(self):
		return reverse('chapter-edit', kwargs={ 'story_pk' : self.object.chapter.story.id,
																							'pk' : self.object.chapter_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(SceneDelete, self).dispatch(*args, **kwargs)
	
	def get_context_data(self, **kwargs) :
		context = super(SceneDelete, self).get_context_data(**kwargs)		
		chapter_pk = int(self.kwargs['chapter_pk'])
		chapter = Chapter.objects.get(pk=chapter_pk)
		context['chapter'] = chapter
		context['story'] = chapter.story
		return context
	
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
		
	def form_valid(self, form):
		form.instance.scene = form.initial['scene']
		return super(ClueCreate, self).form_valid(form)	

	def get_context_data(self, **kwargs) :
		context = super(ClueCreate, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context


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


	def get_context_data(self, **kwargs) :
		context = super(ClueEdit, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context

class ClueDelete(DeleteView):
	model = Clue
	
	def get_success_url(self):
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(ClueDelete, self).dispatch(*args, **kwargs)
	
	def get_context_data(self, **kwargs) :
		context = super(ClueDelete, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context
	
class CastMemberCreate(CreateView):
	model = CastMember		
	fields = ['title', 'description']
	
	def get_success_url(self):		
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.id})
			
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
	
	def get_context_data(self, **kwargs) :
		context = super(CastMemberCreate, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story	
		return context
	
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
	
	def get_context_data(self, **kwargs) :
		context = super(CastMemberEdit, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context

class CastMemberDelete(DeleteView):
	model = CastMember
	
	def get_success_url(self):
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(CastMemberDelete, self).dispatch(*args, **kwargs)
	
	def get_context_data(self, **kwargs) :
		context = super(CastMemberDelete, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context
	
	
class LocationCreate(CreateView):
	model = Location		
	fields = ['name', 'description']
	def get_success_url(self):		
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
		
		
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(LocationCreate, self).dispatch(*args, **kwargs)
	
	def get_initial(self):
		initial = super(LocationCreate, self).get_initial()
		scene_pk = int(self.kwargs['scene_pk'])
		parent_scene = Scene.objects.get(pk=scene_pk)		
		initial['scene'] = parent_scene
		return initial
	
	def form_valid(self, form):
		form.instance.scene = form.initial['scene']
		return super(LocationCreate, self).form_valid(form)
	
	def get_context_data(self, **kwargs) :
		context = super(LocationCreate, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context


class LocationDetail(DetailView):
	model = Location
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(LocationDetail, self).dispatch(*args, **kwargs)
			
			
class LocationEdit(UpdateView):
	model = Location
	
	fields = ['name', 'description']
	
	def get_success_url(self):		
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
		
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(LocationEdit, self).dispatch(*args, **kwargs)
	
	def get_context_data(self, **kwargs) :
		context = super(LocationEdit, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context


class LocationDelete(DeleteView):
	model = Location
	
	def get_success_url(self):
		return reverse('scene-edit', kwargs={ 'story_pk' : self.object.scene.chapter.story.id,
																					'chapter_pk' : self.object.scene.chapter.story.id,
																					'pk' : self.object.scene_id})
	
	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(LocationDelete, self).dispatch(*args, **kwargs)
	
	def get_context_data(self, **kwargs) :
		context = super(LocationDelete, self).get_context_data(**kwargs)		
		scene_pk = int(self.kwargs['scene_pk'])
		scene = Scene.objects.get(pk=scene_pk)
		context['scene'] = scene
		context['chapter'] = scene.chapter
		context['story'] = scene.chapter.story
		return context

