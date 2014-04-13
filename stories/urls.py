from django.conf.urls import patterns, url

from stories.views import StoryList, StoryCreate, StoryEdit, StoryDelete, StoryDetail, ChapterCreate, ChapterEdit,ChapterDetail,ChapterDelete, SceneCreate, SceneEdit, SceneDetail, SceneDelete

urlpatterns = patterns('',
  url(r'^$',                                StoryList.as_view(),      name='story-list'),
	url(r'^create$',                          StoryCreate.as_view(),    name='story-create'), 	
	url(r"""^(?P<pk>\d+)/edit$""",            StoryEdit.as_view(),      name='story-edit'),
	url(r"""^(?P<pk>\d+)/delete$""",          StoryDelete.as_view(),    name='story-delete'),
	url(r"""^(?P<pk>\d+)$""",                 StoryDetail.as_view(),    name='story-detail'),
  url(r"""^chapter/create$""",              ChapterCreate.as_view(),  name='chapter-create'),
  url(r"""^chapter/(?P<pk>\d+)/edit$""",    ChapterEdit.as_view(),    name='chapter-edit'),
  url(r"""^chapter/(?P<pk>\d+)$""",         ChapterDetail.as_view(),  name='chapter-detail'),
  url(r"""^chapter/(?P<pk>\d+)/delete$""",  ChapterDelete.as_view(),  name='chapter-delete'),
  url(r"""^scene/create$""",                SceneCreate.as_view(),    name='scene-create'),
  url(r"""^scene/(?P<pk>\d+)/edit$""",      SceneEdit.as_view(),      name='scene-edit'),
  url(r"""^scene/(?P<pk>\d+)$""",           SceneDetail.as_view(),    name='scene-detail'),
  url(r"""^scene/(?P<pk>\d+)/delete$""",    SceneDelete.as_view(),    name='scene-delete'),

)
