from django.forms import ModelForm
from django.forms.models import modelformset_factory, inlineformset_factory
from stories.models import Story, Chapter

ChapterFormSet = inlineformset_factory( Story, Chapter)

#,exclude=('story',)