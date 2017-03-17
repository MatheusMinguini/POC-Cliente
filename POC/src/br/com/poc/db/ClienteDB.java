package br.com.poc.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.poc.model.Cliente;
import br.com.poc.util.JPAUtil;

public class ClienteDB {
	private EntityManager entityManager;
	private List<Cliente> lista;
	
	public ClienteDB(){
		entityManager = new JPAUtil().getEntityManager();
	}
	
	public boolean insert(Cliente cliente){
		
		boolean status = false;
		
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
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
	
	public boolean update (Cliente cliente){
		boolean status = false;
		
		if(cliente.getCategoria().getId() == 0){
			Cliente c = new Cliente();
			c.setId(cliente.getId());
			c.setNome(cliente.getNome());
			c.setRg(cliente.getRg());
			c.setCpf(cliente.getCpf());
			c.setLogradouro(cliente.getLogradouro());
			c.setNumero(cliente.getNumero());
			c.setBairro(cliente.getBairro());
			c.setComplemento(cliente.getComplemento());
			c.setCidade(cliente.getCidade());
			c.setEstado(cliente.getEstado());
	
			try {
				entityManager.getTransaction().begin();
				entityManager.merge(c);
				entityManager.getTransaction().commit();
				status = true;
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
				status = false;
			} finally{
				entityManager.close();
			}
		}else{
			try {
				entityManager.getTransaction().begin();
				entityManager.merge(cliente);
				entityManager.getTransaction().commit();
				status = true;
			} catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
				status = false;
			} finally{
				entityManager.close();
			}
		}
		
		return status;
	}
	
	public boolean delete(Cliente cliente){
		boolean status = false;
		
		try {
			entityManager.getTransaction().begin();
			cliente = entityManager.find(Cliente.class, cliente.getId());
			entityManager.remove(cliente);
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
	public List<Cliente> getListAll(){
		try {
			
			lista = new ArrayList<Cliente>();
			lista = entityManager.createQuery("From " + Cliente.class.getName()).getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			entityManager.close();
		}
		return lista;
	}
}