from django.conf.urls import patterns, url

from accounts.views import Registration

urlpatterns = patterns('',
  url(r'^$',        Registration.as_view(),      name='register'),	  
  url(r'^login/$', 'django.contrib.auth.views.login', name='login'),
  url(r'^logout$', 'django.contrib.auth.views.logout', {'next_page': '/stories'}, name="logout"),

)
