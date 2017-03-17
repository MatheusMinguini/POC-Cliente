package br.com.poc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
private static final String PERSISTENCE_UNIT = "bdcliente";
	
	private static EntityManagerFactory entityManagerFactory;
	
	
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		if (entityManager == null){
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
}
