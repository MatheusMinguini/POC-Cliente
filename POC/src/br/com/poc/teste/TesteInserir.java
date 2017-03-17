package br.com.poc.teste;

import br.com.poc.db.CategoriaDB;
import br.com.poc.db.ClienteDB;
import br.com.poc.model.Categoria;
import br.com.poc.model.Cliente;

public class TesteInserir {

	public static void main(String[] args) {

		//OBJETO CATEGORIA
		Categoria categoria = new Categoria();
		categoria.setDescricao("Crediario");
		
		if(new CategoriaDB().insert(categoria)){
			System.out.println("Categoria inserida com sucesso!");
		}else{
			System.out.println("Categoria não inserida!");
		}
		
		//OBJETO CLIENTE
		Cliente client = new Cliente();
		client.setNome("matheus");
		client.setRg("rg1");
		client.setCpf("cpf1");
		client.setCategoria(categoria);
		client.setCep("sou um cep");
		client.setLogradouro("sou um endereco");
		client.setNumero(1);
		client.setBairro("sou um bairro");
		client.setComplemento("complemento");
		client.setCidade("cidade");
		client.setEstado("estado");
		
		if(new ClienteDB().insert(client)){
			System.out.println("Inserido com sucesso!");
		}else{
			System.out.println("Não inserido!");
		}
		
	}
}

