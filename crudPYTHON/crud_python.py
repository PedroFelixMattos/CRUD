# -*- coding: utf-8 -*-
"""CRUD Python.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/14nUk_Cpb3yQzyKnw5bTjnDFjhYkzpf7v
"""

from sqlalchemy import create_engine
from datetime import datetime
from sqlalchemy.sql.expression import null

class Contato:
    def __init__(self, rowid: int, nome: str, idade: int, datacadastro: str):
      self.rowid = int(rowid)
      self.nome = nome
      self.idade = int(idade)
      self.datacadastro = datacadastro
    
    def __str__(self):
      return f"{self.rowid} |{self.nome} |{self.idade} |{self.datacadastro}"

#Cria a conexão com o database
my_conn = create_engine("sqlite:////content/database.db")

#Create - INSERT
def CRUDcreate(Contato: Contato):
  
  #deleção da rowid desnessessaria
  del(Contato.rowid)
  
  auxNome = Contato.nome
  auxIdade = str(Contato.idade)
  auxDatacadastro = Contato.datacadastro

  my_conn.execute("INSERT INTO contatos VALUES ('"+auxNome+"', "+auxIdade+", '"+auxDatacadastro+"')")

# Return - SELECT
def CRUDreturn():
  r_set=my_conn.execute("SELECT rowid, nome, idade, datacadastro FROM contatos")

  contatos = []
  for row in r_set:
    auxRowid = row[0]
    auxNome = row[1]
    auxIdade = row[2]
    auxDatacadastro = row[3]
    contatos.append(Contato(auxRowid, auxNome, auxIdade, auxDatacadastro))
    #print(Contato(auxRowid, auxNome, auxIdade, auxDatacadastro))

  for contato in contatos:
    print(contato)

#Update - UPDATE
def CRUDupdate(Contato: Contato, rowid: str):
  
  #deletar variavel desnessessaria
  del(Contato.rowid)

  auxNome = Contato.nome
  auxIdade = str(Contato.idade)
  auxDatacadastro = Contato.datacadastro

  my_conn.execute("UPDATE contatos SET nome= '"+auxNome
        +"', idade= "+auxIdade
        +", datacadastro= '"+auxDatacadastro
        +"' WHERE rowid= "+ rowid)

#Delete - DELETE
def CRUDdelete(rowid: int):
  my_conn.execute("DELETE FROM contatos WHERE rowid= "+str(rowid))

"""Main"""

operacao = input("Insira a operação desejada C,R,U ou D: ")


nome = 'Siclaninha'
idade = 18
datacadastro = str(datetime.today().strftime('%Y-%m-%d %H:%M:%S'))

#valor rowid usado para criação dos contatos
#deletado internamente nas funções
rowidTemp = 0

if(operacao == 'c'):
  CRUDcreate(Contato(rowidTemp, nome, idade, datacadastro))

else:
  if(operacao == 'r'):
     CRUDreturn()

  else:
    if(operacao == 'u'):
      rowid = int(input("Insira o rowid a ser atualizado: "))
      CRUDupdate(Contato(rowidTemp, nome, idade, datacadastro), str(rowid))

    else:
      if(operacao == 'd'):
        rowid = int(input("Insira o rowid a ser deletado: "))
        CRUDdelete(rowid)

      else:
        print("Por favor insira a operação entre C, R, U ou D")