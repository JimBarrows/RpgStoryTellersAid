from django.conf.urls import patterns, url

from stories.views import StoryList, StoryCreate, StoryEdit, StoryDelete, StoryDetail, ChapterCreate, ChapterEdit,ChapterDetail,ChapterDelete, SceneCreate, SceneEdit, SceneDetail, SceneDelete, ClueCreate, ClueEdit, ClueDetail, ClueDelete, CastMemberCreate, CastMemberEdit, CastMemberDetail, CastMemberDelete

urlpatterns = patterns('',
  url(r'^$',                                StoryList.as_view(),      name='story-list'),
	url(r'^create$',                          StoryCreate.as_view(),    name='story-create'), 	
	url(r"""^(?P<pk>\d+)/edit$""",            StoryEdit.as_view(),      name='story-edit'),
	url(r"""^(?P<pk>\d+)/delete$""",          StoryDelete.as_view(),    name='story-delete'),
	url(r"""^(?P<pk>\d+)$""",                 StoryDetail.as_view(),    name='story-detail'),
  url(r"""^(?P<story_pk>\d+)/chapter/create$""",              ChapterCreate.as_view(),  name='chapter-create'),
  url(r"""^chapter/(?P<pk>\d+)/edit$""",    ChapterEdit.as_view(),    name='chapter-edit'),
  url(r"""^chapter/(?P<pk>\d+)$""",         ChapterDetail.as_view(),  name='chapter-detail'),
  url(r"""^chapter/(?P<pk>\d+)/delete$""",  ChapterDelete.as_view(),  name='chapter-delete'),
  url(r"""^scene/create$""",                SceneCreate.as_view(),    name='scene-create'),
  url(r"""^scene/(?P<pk>\d+)/edit$""",      SceneEdit.as_view(),      name='scene-edit'),
  url(r"""^scene/(?P<pk>\d+)$""",           SceneDetail.as_view(),    name='scene-detail'),
  url(r"""^scene/(?P<pk>\d+)/delete$""",    SceneDelete.as_view(),    name='scene-delete'),
  url(r"""^clue/create$""",                 ClueCreate.as_view(),     name='clue-create'),
  url(r"""^clue/(?P<pk>\d+)/edit$""",       ClueEdit.as_view(),       name='clue-edit'),
  url(r"""^clue/(?P<pk>\d+)$""",            ClueDetail.as_view(),     name='clue-detail'),
  url(r"""^clue/(?P<pk>\d+)/delete$""",     ClueDelete.as_view(),     name='clue-delete'),
  url(r"""^castmember/create$""",           CastMemberCreate.as_view(),     name='castmember-create'),
  url(r"""^castmember/(?P<pk>\d+)/edit$""",   CastMemberEdit.as_view(),       name='castmember-edit'),
  url(r"""^castmember/(?P<pk>\d+)$""",        CastMemberDetail.as_view(),     name='castmember-detail'),
  url(r"""^castmember/(?P<pk>\d+)/delete$""", CastMemberDelete.as_view(),     name='castmember-delete'),

)
