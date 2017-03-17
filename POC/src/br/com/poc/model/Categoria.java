package br.com.poc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="tb_categoria")
@NamedQuery (name = Categoria.GET_ALL, query="SELECT c FROM Categoria c")

public class Categoria {
	
	//SELECTS
	public static final String GET_ALL = "categoria.getAll";
	
	@Id
	@GeneratedValue
	private int id;
	
	private String descricao;
	
	public Categoria(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
