package com.bookvault.repository;
import java.util.List;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;
import com.practice.model.*;

public class GenericDAO {

	EntityManager em;

	public GenericDAO (EntityManager em){
		this.em = em;
	}

	public <E> E create(E entity){
		em.persist(entity);
		return entity;
	}

	public <E> E find(Class<E> entityClass,long id){
		return em.find(entityClass, id);
	}
	
	public <E> List<E> findAll(Class<E> entityClass){
		TypedQuery<E> query = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
		return query.getResultList();
	}

	public <E> List<E> findAllWith(Class<E> entityClass,String fieldName,Object value){
		String command = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName +" =:value";
		TypedQuery<E> query = em.createQuery(command,entityClass);
		query.setParameter("value",value);
		return query.getResultList();
	}


	public <E> void removeCliente(E entity){
		if (entity != null){
			em.remove(entity);
		}
	}

}
