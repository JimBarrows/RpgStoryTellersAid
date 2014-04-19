from django.conf.urls import patterns, include, url

from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'RpgStoryTellersAid.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
	url(r'^stories/', include('stories.urls')),
  url(r'^accounts/', include('accounts.urls')),  
  url(r'^admin/', include(admin.site.urls)),    
)
