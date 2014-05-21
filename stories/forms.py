from django.forms import ModelForm
from django.forms.models import modelformset_factory, inlineformset_factory
from stories.models import Scene, SceneCastMember

SceneCastMemberFormset = inlineformset_factory( Scene, SceneCastMember, extra=3, fields=('cast_member', 'description'))
