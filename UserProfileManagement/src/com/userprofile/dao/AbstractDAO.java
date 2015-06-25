/**
 * 
 */
package com.userprofile.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author analian
 *
 */
public abstract class AbstractDAO<T extends Serializable> {

	private Class<T> theEntityClass;
	
	@PersistenceContext
	protected EntityManager entityManager;

	public final void setClass(Class<T> classToSet) {
		this.theEntityClass = classToSet;
	}

	public T findOne(long id) {
		return entityManager.find(theEntityClass, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("from " + theEntityClass.getName())
				.getResultList();
	}

	public void create(T entity) {
		entityManager.persist(entity);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}
