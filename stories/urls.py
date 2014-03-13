from django.conf.urls import patterns, url

from stories.views import StoryList, StoryCreate, StoryDetail, StoryEdit, StoryDelete

urlpatterns = patterns('',
    url(r'^$', StoryList.as_view(), name='story-list'),
	url(r'^create', StoryCreate.as_view(), name='story-create'),	
	url(r"""^(?P<pk>\d+)/edit$""", StoryEdit.as_view(), name='story-edit'),
	url(r"""^(?P<pk>\d+)/delete$""", StoryDelete.as_view(), name='story-delete'),
	url(r"""^(?P<pk>\d+)$""", StoryDetail.as_view(), name='story-detail'),

)
