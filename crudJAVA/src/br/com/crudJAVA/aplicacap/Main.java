package br.com.crudJAVA.aplicacap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import br.com.crudJAVA.dao.ContatoDAO;
import br.com.crudJAVA.model.Contato;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("Insira a operação C.R.U.D desejada: ");
		Scanner entrada = new  Scanner(System.in);
		char operacao = entrada.next().charAt(0); 
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ContatoDAO contatoDAO = new ContatoDAO();
		if(operacao == 'c') {	
			Contato contatoC = new Contato();
	
			contatoC.setNome("Joaquina Leopoldina");
			contatoC.setIdade(31);
			contatoC.setDataCadastro(dtf.format(now));
			
			contatoDAO.save(contatoC);
		}else if (operacao == 'u') {
			//Atualizar o contato
			Contato contatoU = new Contato();
			contatoU.setNome("João Silva das Cruzes");
			contatoU.setIdade(58);
			contatoU.setDataCadastro(dtf.format(now));
			System.out.print("Escolha o id que deseja atualizar: ");
			Scanner entradaAdicao = new  Scanner(System.in);
			int adicao = Integer.parseInt(entradaAdicao.next());
			contatoU.setId(adicao); // é o numero que está no banco de dados da PK

			contatoDAO.update(contatoU);
		}else if(operacao == 'd') {
		//Deletar o contato pelo seu número de ID
			System.out.print("Escolha o id que deseja remover: ");
			Scanner entradaDelecao = new  Scanner(System.in);
			int delecao = Integer.parseInt(entradaDelecao.next());
			contatoDAO.deleteByID(delecao);
		}else if(operacao == 'r') {
			//Visualização dos registros do banco de dados TODOS
			for(Contato contatoR : contatoDAO.getContatos()) {
				System.out.println("Contato: "+contatoR.getNome()+
						", Idade: "+contatoR.getIdade()+
						", Data de Cadastro: "+contatoR.getDataCadastro());
			}
		}
	}
}
