package br.com.poc.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.poc.model.Categoria;
import br.com.poc.model.Cliente;
import br.com.poc.util.JPAUtil;

public class CategoriaDB {


		private EntityManager entityManager;
		private List<Categoria> lista;
		
		public CategoriaDB(){
			entityManager = new JPAUtil().getEntityManager();
		}
		
		public boolean insert(Categoria categoria){
			
			boolean status = false;
			
			try{
				entityManager.getTransaction().begin();
				entityManager.persist(categoria);
				entityManager.getTransaction().commit();
				status = true;
			}catch(Exception e){
				e.printStackTrace();
				entityManager.getTransaction().rollback();
			}finally{
				entityManager.close();
			}
			return status;
		}
		
		public boolean update (Categoria categoria){
			boolean status = false;
			try {
				entityManager.getTransaction().begin();
				entityManager.merge(categoria);
				entityManager.getTransaction().commit();
				status = true;
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
				status = false;
			} finally{
				entityManager.close();
			}
			return status;
		}
		
		public boolean delete(Categoria categoria){
			boolean status = false;
			
			try {
				entityManager.getTransaction().begin();
				categoria = entityManager.find(Categoria.class, categoria.getId());
				entityManager.remove(categoria);
				entityManager.getTransaction().commit();
				status = true;
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
			} finally {
				entityManager.close();
			}
			return status;
		}
		
		@SuppressWarnings("unchecked")
		public List<Categoria> getListAll(){
			try {
				
				lista = new ArrayList<Categoria>();
				lista = entityManager.createQuery("From " + Categoria.class.getName()).getResultList();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				entityManager.close();
			}
			return lista;
		}
	
}
