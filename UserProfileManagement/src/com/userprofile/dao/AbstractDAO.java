/**
 * 
 */
package com.userprofile.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

/**
 * Abstract DAO Class extended by all IMPL classes 
 * to implement the base methods of JPA
 * 
 * @author analian
 *
 */
public abstract class AbstractDAO<T extends Serializable> {

	private static final Logger logger = Logger.getLogger(AbstractDAO.class);
	
	private Class<T> theEntityClass;
	
	@PersistenceContext
	protected EntityManager entityManager;

	public final void setClass(Class<T> classToSet) {
		this.theEntityClass = classToSet;
	}

	public T findOne(long id) {
		logger.debug("finding one object" + id);
		return entityManager.find(theEntityClass, id);
	}

	public List<T> findAll() {
		logger.debug("finding all objects");
		return entityManager.createQuery("from " + theEntityClass.getName())
				.getResultList();
	}

	public void create(T entity) {
		logger.debug("creating a new object");
		entityManager.persist(entity);
	}

	public T update(T entity) {
		logger.debug("updating an object" + entity);
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		logger.debug("deleting an object" + entity);
		entityManager.remove(entity);
	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}
