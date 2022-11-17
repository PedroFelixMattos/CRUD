package br.com.crudJAVA.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

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

	public void adicionarValores() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		this.setNome(nome);
		
		System.out.print("Idade: ");
		int idade = scanner.nextInt();
		this.setIdade(idade);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.setDataCadastro((dtf.format(now)));

	}
}
