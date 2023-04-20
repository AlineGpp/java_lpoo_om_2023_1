package br.edu.ifsul.cc.lpoo.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJPA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//como resolver o problema de o id ser uma constante pode ser eoslvido com listas
//teste circukar

public class TestPersistenceJPA {

    @Test
    public void testConexaoGeracaoTabelas() {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JPA");

            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

    /*
        Exercício 2:
         Passo 1: encontrar a peça id=1
         Passo 2: caso encontre remove-la
         Passo 3: caso não encontre criar nova peça.
    */

    @Test
    public void testEncontrarPecas() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();

        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JPA");

            if (persistencia.find(Peca.class, 12) != null) {
                persistencia.remover(persistencia.find(Peca.class, 12));
            } else {
                Peca novaPeca = new Peca();
                novaPeca.setFornecedor("Fornecedor 1");
                novaPeca.setNome("Peca 1");
                novaPeca.setValor(170.99F);
                persistencia.persist(novaPeca);
            }

            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        }
    }

       /*
        Exercício 3:
        Passo 1: recuperar a lista de peças.
        Passo 2: se a lista.size() > 0 listar e remover.
        Passo 3: se a lista.size() == 0 inserir duas Peças.
    */

    @Test
    public void testPercsistenciaPeca() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();

        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JPA");
            List<Peca> lista = persistencia.listPeca();
            if ((lista.size() > 0)) {
                for (Peca peca : lista) {
                    System.out.println(peca);
                    persistencia.remover(peca);
                }

            } else {
                Peca novaPeca = new Peca();
                novaPeca.setFornecedor("Fornecedor 1");
                novaPeca.setNome("Peca 1");
                novaPeca.setValor(170.99F);
                persistencia.persist(novaPeca);

                novaPeca = new Peca();
                novaPeca.setFornecedor("Fornecedor 2");
                novaPeca.setNome("Peca 2");
                novaPeca.setValor(170.99F);
                persistencia.persist(novaPeca);
            }

            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

     /*
        Exercício 4:
            Passo 1: listar dos os funcionários com os seus respectivos curso.
            Passo 2: se a lista.size() > 0 listar e remover o funcionário.
            Passo 3: se a lista.size() == 0 inserir um funcionário e associar cursos..
    */


    @Test
    public void testListFunc() throws Exception{

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){

            List<Funcionario> list = persistencia.listaFuncionario();

            if(list.isEmpty()){

                Funcionario f = new Funcionario();
                f.setCpf("1234567891");
                f.setNumero_ctps("456");
                f.setNome("Ciclano");
                f.setSenha("123456");
                f.setComplemento("ruas das camelias");
                f.setCep("99000000");
                f.setNumero("123");
                f.setComplemento("casa 1");
                f.setData_admissao(Calendar.getInstance());//recupera a data atual.

                f.setCargo(getCargo(persistencia));//cria um novo cargo ou retorno a primeiro.


                List<Curso> listCurso = new ArrayList();

                listCurso.add(getCurso(persistencia));//adiciona um curso na lista.

                f.setCursos(listCurso);//seta lista de curso no funcionario


                persistencia.persist(f);//insert na tb_pessoa e tb_funcionario (nas associativas)

                System.out.println("Cadastrou o funcionario "+f.getCpf()+" com "+f.getCursos().size()+" cursos.");


            }else{

                System.out.println("Listagem de Funcionários::");
                for(Funcionario f : list){

                    System.out.println("\t cpf: "+f.getCpf());

                    System.out.println("\tCargo do funcionario: "+f.getCargo().getDescricao());

                    for(Curso c : f.getCursos()){

                        System.out.println("\t\t"+c.getDescricao());
                    }

                    persistencia.remover(f);

                    System.out.println("\t\t removeu o funcionario cpf="+f.getCpf());
                }
            }


        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

    private Curso getCurso(PersistenciaJPA jpa) throws Exception {

        List<Curso> list = jpa.listaDeCursos();
        if(list.isEmpty()){
            Curso c = new Curso();
            c.setDescricao("curso de mecanico");
            c.setCargahoraria(100);
            jpa.persist(c);

            return c;
        }else{

            return list.get(0);
        }

    }

    private Cargo getCargo(PersistenciaJPA jpa) throws Exception {

        List<Cargo> list = jpa.listaCargos();
        if(list.isEmpty()){
            Cargo c = new Cargo();
            c.setDescricao("Mecanico Master");
            jpa.persist(c);

            return c;
        }else{

            return list.get(0);
        }

    }



}
