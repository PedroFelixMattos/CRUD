package br.com.crudJAVA.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.crudJAVA.factory.ConnectionFactory;
import br.com.crudJAVA.model.Contato;

public class ContatoDAO {

	/*
	 * CRUD
	 * c: CREATE - OK - INSERT
	 * r: READ - OK - SELECT
	 * u: UPDATE - OK - UPDATE
	 * d: DELETE - DELETE
	 */

	public void save(Contato contato) {
	
		Connection conn = null;
		//PreparedStatement pstm = null;
	
	
		try {
			//Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			Statement statement = conn.createStatement();
			
			//Adicionamos os valores que são esperados pela query
			final String auxNome = contato.getNome();
			final int auxIdade = contato.getIdade();
			final String auxDatacadastro = contato.getDataCadastro();
		
			//Executar a query
			String sql = "INSERT INTO contatos (nome, idade, datacadastro) VALUES ('"+auxNome+"', "+auxIdade+", '"+auxDatacadastro+"');";
			//System.out.print(sql);
			statement.executeUpdate(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//Fechar as conexões
			try {
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Contato contato) {
		
		Connection conn = null;
		Statement statement = null;
		
		try {
			//Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar a classe para executar a query
			statement = conn.createStatement();
			
			//Adicionar os valores para atualizar
			final String auxNome = contato.getNome();
			final int auxIdade = contato.getIdade();
			final String auxDatacadastro = contato.getDataCadastro();
			
			//QUal o ID do registro que deseja atualizar?
			final int auxId = contato.getId();
			
			//Verifica se o ID a ser atualizado esta presente no Banco de Dados
			boolean temRowid = false;
			for(Contato contatoR : this.getContatos()) {
				if(contatoR.getId() == auxId){
					temRowid = true;
					break;
				}
			}
			
			//Executar a query
			if(temRowid == true) {
				String sql = "UPDATE contatos SET nome = '"+auxNome+"', idade = "+auxIdade+", datacadastro = '"+auxDatacadastro+"' "+
						"WHERE rowid = "+auxId;
				//System.out.print(sql);
				statement.executeUpdate(sql);
			}else {
				System.out.print("ID não encontrado");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(conn!=null) {
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteByID(int id) {
	
		Connection conn = null;
		Statement statement = null;
		
		try {
			//Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar a classe para executar a query
			statement = conn.createStatement();
			
			//Verifica se o ID a ser atualizado esta presente no Banco de Dados
			boolean temRowid = false;
			for(Contato contatoR : this.getContatos()) {
				if(contatoR.getId() == id){
					temRowid = true;
					break;
				}
			}
			
			//Executar a query
			if(temRowid == true) {
				String sql = "DELETE FROM contatos WHERE rowid = "+id;
				//System.out.print(sql);
				statement.executeUpdate(sql);
			}else {
				System.out.print("ID não encontrado");
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> getContatos(){
		
		String sql = "SELECT rowid, nome, idade, datacadastro FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		Statement statement = null;
		//Classe que vai recuperar os dados do banco. ***SELECT***
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			statement = conn.createStatement();
			
			rset = statement.executeQuery(sql);
			//System.out.print(sql);
			while (rset.next()) {
				
				Contato contato = new Contato();
			
				//Recuperar ID
				contato.setId(rset.getInt("rowid"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar a data de cadastro
				//System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rset.getString("datacadastro")));
				contato.setDataCadastro(rset.getString("datacadastro"));
			
				contatos.add(contato);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null) {
					rset.close();
				}
				
				if(statement!=null) {
					statement.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return contatos;
	}

}

