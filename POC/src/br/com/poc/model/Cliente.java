package br.com.poc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="tb_cliente")
@NamedQuery (name = Cliente.GET_ALL, query="SELECT c FROM Cliente c")

public class Cliente {

	//SELECTS
	public static final String GET_ALL = "cliente.getAll";
	
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String rg;
	private String cpf;
	private String cep;
	private String logradouro;
	private int numero;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;
	
	@ManyToOne
	private Categoria categoria;
	
	public Cliente(){
	}
	public Cliente(String nome, String rg, String cpf, String cep, String logradouro, int numero, String bairro,
			String complemento, String cidade, String estado) {
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString(){
		return "ID: " + this.id + "Nome:" + this.nome + "RG:" + this.rg + "CPF:" + this.cpf + "RG:" + this.rg 
				+ "CEP:" + this.cep + "Logradouro:" + this.logradouro + "Bairro:" + this.bairro
				+ "Complemento" + this.complemento + "Cidade:" + this.cidade + "Estado:" + this.estado;
	}
	
	
}
