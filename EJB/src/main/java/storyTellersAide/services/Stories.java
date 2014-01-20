package storyTellersAide.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import storytellersaid.models.Story;

@Stateless
public class Stories {

	@PersistenceContext
	EntityManager em;

	public List<Story> all() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Story> cq = cb.createQuery(Story.class);
		Root<Story> story = cq.from(Story.class);
		cq.select(story);
		TypedQuery<Story> q = em.createQuery(cq);
		List<Story> allStorys = q.getResultList();
		return allStorys;
	}

	public void add(Story story) {

		em.persist(story);

	}

	public Story findBy(Long id) {		
		return em.find(Story.class, id);
	}
}
