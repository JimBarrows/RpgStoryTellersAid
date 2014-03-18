from django.shortcuts import render
from django import forms
from django.views.generic.list import ListView
from django.views.generic.edit import CreateView
from django.views.generic.detail import DetailView
from django.views.generic.edit import UpdateView, DeleteView
from django.core.urlresolvers import reverse_lazy
from django.http import HttpResponseRedirect


from stories.models import Story
from stories.forms import ChapterFormSet

class StoryList(ListView):
	model = Story

class StoryCreate(CreateView):
	model = Story

	def get( self, request, *args, **kwargs):
		self.object = None
		form_class = self.get_form_class()
		form = self.get_form(form_class)
		chapter_formset = ChapterFormSet()
		return self.render_to_response(
			self.get_context_data(	form=form,
				chapter_formset = chapter_formset))

	def post( self, request, *args, **kwargs):
		self.object = None
		chapter_formset = ChapterFormSet( self.request.POST)
		form_class = self.get_form_class()
		form = self.get_form( form_class)
		if( form.is_valid() and chapter_formset.is_valid()):
			return self.form_valid(form, chapter_formset)
		else:
			return self.form_invalid(form, chapter_formset)

	def form_valid(self, form, chapter_formset):
		self.object = form.save()
		chapter_formset.instance = self.object
		chapter_forms = chapter_formset.save()		
		return HttpResponseRedirect(self.get_success_url())

	def form_invalid(self, form, chapter_formset):		
		return self.render_to_response(
			self.get_context_data(form = form,
				chapter_formset = chapter_formset))

class StoryDetail(DetailView):
	model = Story

class StoryEdit(UpdateView):
	model = Story

	def get( self, request, *args, **kwargs):
		self.object = self.get_object()
		form_class = self.get_form_class()
		form = self.get_form(form_class)			
		chapter_formset = ChapterFormSet(initial=self.object.chapter_set.values())
		return self.render_to_response(
			self.get_context_data(	form=form,
				chapter_formset = chapter_formset))

	def post( self, request, *args, **kwargs):
		self.object = None
		chapter_formset = ChapterFormSet( self.request.POST)
		form_class = self.get_form_class()
		form = self.get_form( form_class)
		if( form.is_valid() and chapter_formset.is_valid()):
			return self.form_valid(form, chapter_formset)
		else:
			return self.form_invalid(form, chapter_formset)

			def form_valid(self, form, chapter_formset):
				self.object = form.save()
				chapter_formset.instance = self.object
				chapter_forms = chapter_formset.save()
				return HttpResponseRedirect(self.get_success_url())

				def form_invalid(self, form, chapter_formset):		
					return self.render_to_response(
						self.get_context_data(form = form,
							chapter_formset = chapter_formset))

class StoryDelete(DeleteView):
	model = Story
	success_url = reverse_lazy('story-list')