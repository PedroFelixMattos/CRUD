package br.com.crudJAVA.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	//Nome do usuário do mysql
	private static final String USERNAME = "root";
	
	//senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:sqlite:/C:\\Users\\usuario\\Desktop\\sqlite-tools-win32-x86-3390400\\database.db";

	/*
	 * Conexão com o banco de dados
	 */
	public static Connection createConnectionToMySQL() throws Exception {
		//Faz com que a classe seja carregada pela JVM
		//Class.forName("com.mysql.jdbc.Driver");
	
		//Cria a cpnexão com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL);
		
		return connection;
	}


	public static void main(String[] args) throws Exception {
		
		//Recuperar uma conexão com o banco de dados
		Connection con = createConnectionToMySQL();
		
		//Testar se a conexão é nula
		if(con!=null) {
			System.out.print("Conexão obtida com sucesso!");
			con.close();
		}
		
	}


}
