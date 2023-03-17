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

    public  Cargo retornarCargo (int id) throws Exception{
        PersistenciaJPA persistencia = new PersistenciaJPA();
        Cargo cargo =(Cargo) persistencia.find(Cargo.class,id);// casting de obect para cargo
        if (cargo == null) {
            Cargo novoCargo = new Cargo();
            novoCargo.setDescricao("Fullstack");
            persistencia.persist(novoCargo);
        }
        return cargo;
    }
    public List<Curso> listarCursosCriados() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        List<Curso> listarCursos = new ArrayList<>();

        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JPA");

            Curso curso = new Curso();
            curso.setCargahoraria(20);
            curso.setDescricao("Programação WEb");
            curso.setDt_conclusao(Calendar.getInstance());

            listarCursos.add(curso);
            Curso curso1 = new Curso();
            curso1.setCargahoraria(20);
            curso1.setDescricao("Programação back-end");
            curso1.setDt_conclusao(Calendar.getInstance());

           persistencia.persist(curso1);
            persistencia.persist(curso);
            listarCursos.add(curso1);
            //persistencia.persist(listarCursos);
            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        return listarCursos;
    }


    @Test
    public void testListaFuncionarios() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();

        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JPA");
            List<Funcionario> lista = persistencia.listaFuncionario();

            if ((lista.size() > 0)) {
                for (Funcionario funcionario : lista) {
                    System.out.println(funcionario);
                    persistencia.remover(funcionario);
                }
            } else {
                Funcionario funcionario = new Funcionario();

                funcionario.setData_admissao(Calendar.getInstance());
                funcionario.setData_demissao(Calendar.getInstance());
                funcionario.setNumero_ctps("092536");
                funcionario.setCpf("864862848724");
                funcionario.setCursos( listarCursosCriados());
                funcionario.setCep("123456");
                funcionario.setNome("João");
                funcionario.setSenha("123456");
                funcionario.setComplemento("casa");
                funcionario.setData_nascimento(Calendar.getInstance());
                funcionario.setCargo(retornarCargo(1));
                funcionario.setNumero("123");

                persistencia.persist(funcionario);
            }

            persistencia.fecharConexao();

            System.out.println("fechou a conexao com o BD via JPA");

        } else {
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }


}
