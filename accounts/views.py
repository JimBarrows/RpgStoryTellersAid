from django.views.generic.edit import CreateView

from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm

class Registration(CreateView):
  model = User
  form_class = UserCreationForm   
  success_url = '/stories'