package rpgStoryTellersAide.services;

import static fj.Unit.unit;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import rpgStoryTellersAide.models.Chapter;
import rpgStoryTellersAide.models.Location;
import rpgStoryTellersAide.models.Scene;
import rpgStoryTellersAide.models.Story;
import fj.F;
import fj.Unit;
import fj.data.Option;

@Stateful
public class Stories {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	EntityManager em;

	public List<Story> all() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Story> cq = cb.createQuery(Story.class);
		Root<Story> story = cq.from(Story.class);
		cq.select(story);
		cq.orderBy(cb.asc(story.get(("name"))));
		TypedQuery<Story> q = em.createQuery(cq);
		List<Story> allStorys = q.getResultList();
		return allStorys;
	}
	
	public void save(Story story) {
		if(story.isNew()) {
			create( story);
		}else {
			update( story);
		}
	}
	public void create(Story story) {

		em.persist(story);

	}
	

	public Option<Story> update(final Story updatedStory) {
		return findBy(updatedStory.getId()).map(new F<Story, Story>() {

			@Override
			public Story f(Story oldStory) {
				oldStory.setDescription(updatedStory.getDescription());
				oldStory.setName(updatedStory.getName());
				return oldStory;
			}
		});

	}

	public Option<Story> findBy(Long id) {
		return Option.fromNull(em.find(Story.class, id));
	}
	
	public void delete(final Story story) {
		findBy(story.getId()).map(new F<Story, Unit>() {

			@Override
			public Unit f(Story story) {
				em.remove(story);
				return unit();
			}
		});

	}	

	public void removeChapterFrom(Story story, Chapter chapter) {
		story.getChapters().remove(chapter);
		em.remove(chapter);
	}

	public void add(Story story, Chapter chapter) {

		story.add( chapter);
		em.persist(chapter);
		
	}

	public void add(Chapter chapter, Scene scene) {
		chapter.add(scene);
		em.persist(scene);
		
	}

	public Option<Location> findLocationBy(long id) {
		return Option.fromNull(em.find(Location.class, id));
		
	}
}
