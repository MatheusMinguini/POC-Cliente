package br.com.poc.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.poc.db.CategoriaDB;
import br.com.poc.db.ClienteDB;
import br.com.poc.model.Categoria;
import br.com.poc.model.Cliente;

@Path("/cliente")
public class ClienteService {
	
	@GET
	@Path("/teste")
	@Produces(MediaType.TEXT_HTML)
	public String teste(){
		String txt = "<html>" + "<title>" + "TESTE Serviço motorista" + "</title>"
				+ "<body>" + "<h1>" + "Acessei o serviço motorista" + "</h1>"
				+ "</body>" + "</html>";
		return txt;
	}
	
	
	@GET
	@Path("/selectClientes")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectClientes(){
		String clientes;
		List<Cliente> lista = new ArrayList<Cliente>();
		lista = new ClienteDB().getListAll();
		Gson json = new Gson();
		clientes = json.toJson(lista);
		return clientes;
	}
	
	@GET
	@Path("/selectCategoria")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCategoria(){
		String categoria;
		List<Categoria> lista = new ArrayList<Categoria>();
		lista = new CategoriaDB().getListAll();
		Gson json = new Gson();
		categoria = json.toJson(lista);
		return categoria;
	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserir(String cliente) throws JSONException{
		String cep = null, logradouro = null, bairro = null, complemento = null, cidade = null, estado = null;
		int numero = 0;
		
		JSONObject json = new JSONObject(cliente);
		
		String nome = json.getString("nome");
		String cpf = json.getString("cpf");
		String rg = json.getString("rg");
		
		int id_categoria = json.getInt("categoria");
		Categoria categoria = new Categoria();
		categoria.setId(id_categoria);
		
		
		
		try{
			numero = json.getInt("numero");
		}catch(Exception e){
			System.out.println("Número não encontrado");
		}
		
		try{
			 cep = json.getString("cep");
			 logradouro = json.getString("logradouro");
			 bairro = json.getString("bairro");
			 complemento = json.getString("complemento");
			 cidade = json.getString("cidade");
			 estado = json.getString("estado");
		}catch(Exception e){
			System.out.println("Campos vazios");
		}
		
		Cliente client = new Cliente(nome, cpf, rg, cep, logradouro, numero, bairro, complemento, cidade, estado);
		client.setCategoria(categoria);
		
		if(new ClienteDB().insert(client)){
			System.out.println("Inserido com sucesso!");
		}else{
			System.out.println("Não inserido!");
		}
	}
	
	@POST
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizar(String cliente) throws JSONException{
		String cep = null, logradouro = null, bairro = null, complemento = null, cidade = null, estado = null;
		int numero = 0;
	
		JSONObject json = new JSONObject(cliente);

		int id = json.getInt("id");
		String nome = json.getString("nome");
		String cpf = json.getString("cpf");
		String rg = json.getString("rg");
		
		int id_categoria;
		Categoria categoria = new Categoria();
		
		try{
			id_categoria = json.getInt("categoria");
			categoria.setId(id_categoria);
		}catch(Exception e){
			id_categoria = 0;
			categoria.setId(id_categoria);
			System.out.println("Sem categoria");
		}
		
		
		try{
			numero = json.getInt("numero");
		}catch(Exception e){
			System.out.println("Número não encontrado");
		}
		
		try{
			 cep = json.getString("cep");
			 logradouro = json.getString("logradouro");
			 bairro = json.getString("bairro");
			 complemento = json.getString("complemento");
			 cidade = json.getString("cidade");
			 estado = json.getString("estado");
		}catch(Exception e){
			System.out.println("Campos vazios");
		}
		
		
		Cliente client = new Cliente(nome, cpf, rg, cep, logradouro, numero, bairro, complemento, cidade, estado);
		client.setId(id);
		client.setCategoria(categoria);
		
		if(new ClienteDB().update(client)){
			System.out.println("Alterado com sucesso!");
		}else{
			System.out.println("Não alterado!");
		}
	}
	
	@POST
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletar(String id){
		int id_cliente = Integer.parseInt(id);
		Cliente cliente = new Cliente();
		cliente.setId(id_cliente);
		
		if(new ClienteDB().delete(cliente)){
			System.out.println("Deletado com sucesso!");
		}else{
			System.out.println("Não deletado!");
		}
	}
	
	
}
