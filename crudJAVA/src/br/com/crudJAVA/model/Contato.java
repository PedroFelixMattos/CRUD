package br.com.crudJAVA.model;

import java.util.Date;

public class Contato {
	private int rowid;
	private String nome;
	private int idade;
	private String datacadastro;
	
	public int getId() {
		return rowid;
	}
	public void setId(int id) {
		this.rowid = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getDataCadastro() {
		return datacadastro;
	}
	public void setDataCadastro(String string) {
		this.datacadastro = string;
	}
}
