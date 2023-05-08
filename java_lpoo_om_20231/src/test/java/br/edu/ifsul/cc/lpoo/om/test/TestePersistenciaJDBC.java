package br.edu.ifsul.cc.lpoo.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Equipe;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.List;

/**
 * Classe contendo tetes unitários para persisitencia de dados utiliizando JDBC
 */
public class TestePersistenciaJDBC {


    @Test
    public void testPersistenciaListEquie() throws Exception {

        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("testPersistenciaListEquie: conectou no BD via jdbc ...");

            List<Equipe> lista = jdbc.listEquipe();
            if(!lista.isEmpty()){
                ///SELECT e.id, e.especialidades, e.nome,f.cpf
                //	FROM tb_equipe e,tb_funcionario f,tb_equipe_funcionario ef where  ef.equipe_id=e.id and ef.funcionario_cpf = f.cpf;
                //questao 4.a

                for(Equipe e: lista){
                    System.out.println("Equipe: "+e.getId());
                    System.out.println("Especialidade: "+e.getEspecialidades());
                    System.out.println("Nome: "+e.getNome());
                    System.out.println("Funcionarios "+e.getFuncionarios());

                    //questao 4.c
                    System.out.println("...Removendo a equipe "+e.getId());
                   // jdbc.remover(e);

                }
            }else{
                //questao 4.b

            }

            jdbc.fecharConexao();
        }else{
            System.out.println("testPersistenciaListEquie: nao conectou no BD via jdbc ...");
        }
    }


    //@Test
    public void testPersistenciaFindFuncionario() throws Exception {

        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("testPersistenciaListFuncionario: conectou no BD via jdbc ...");

            Funcionario fn = (Funcionario) jdbc.find(Funcionario.class, "52738");
            if(fn == null){
                fn = new Funcionario();

                fn.setCpf("52738");
                fn.setNome("Fulano");
                fn.setCep("99000000");
                fn.setNumero("123");
                fn.setSenha("123456");
                fn.setComplemento("casa");
                fn.setNumero_ctps("1000");
                fn.setData_nascimento(Calendar.getInstance());
                fn.setCargo((Cargo) jdbc.find(Cargo.class, 1));
                jdbc.persist(fn);
            }else{
                System.out.println("Encontrou o funcionario : "+fn.getCpf());
            }

        }else{
            System.out.println("testPersistenciaListPeca: nao conectou no BD via jdbc ...");
        }

    }

    //@Test
    public void testPersistenciaFindCargo() throws Exception {

        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("testPersistenciaListCargo: conectou no BD via jdbc ...");

            Cargo cg = (Cargo) jdbc.find(Cargo.class, 1);
            if(cg == null){
                cg = new Cargo();
                cg.setDescricao("cargo de mecanico master");
                jdbc.persist(cg);
            }else{
                System.out.println("Encontrou o cargo : "+cg.getId());
            }

        }else{
            System.out.println("testPersistenciaListPeca: nao conectou no BD via jdbc ...");
        }

    }


    //@Test
    public void testPersistenciaListFuncionario() throws Exception {

    /*
       ##### Exercicio de Preparação para a Avaliação ####
      1) Recuperar a lista de Funcionarios com seus respectivos cursos.
      2) Se a lista não for vazia, imprimir cpf e cargo de cada funcionario
            e os seus respectivos cursos (descrição), alterá-lo (cargo) e remove-lo.
      3) Se a lista estiver vazia, cadastrar um novo funcionario e associar um curso.
    */
    }

    //@Test
    public void testPersistenciaListPeca() throws Exception {

        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("testPersistenciaListPeca: conectou no BD via jdbc ...");

            // Atividade prática para 13/04/2023.
            //recuperar a lista de peca
            //se a lista não estiver vazia -> percorrer e imprimir o id de cada
            //  peca e remover
            //se a lista estiver vazia -> criar uma peça.

        }else{
            System.out.println("testPersistenciaListPeca: nao conectou no BD via jdbc ...");
        }
    }


    //@Test
    public void testPersistenciaPeca() throws Exception {

        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("conectou no BD via jdbc ...");

            //chama o método find da classe PersistenciaJDBC
            //modela o retorno do método find: de Object para Peca
            Peca p = (Peca) jdbc.find(Peca.class, 10);
            if(p == null){
                System.out.println("Nao encontrou a peça 9");

                p = new Peca();
                p.setFornecedor("fornecedor de peça");
                p.setNome("correia");
                p.setValor(250.0f);

                jdbc.persist(p);

                System.out.println("inseriu a peca "+p.getId());

            }else{
                System.out.println("Encontrou a peça id="+p.getId());
                p.setNome("nome alterado");

                jdbc.persist(p);

                System.out.println("alterou a peca id="+p.getId());

                System.out.println("removendo a peca id ="+p.getId());
                jdbc.remover(p);
            }

            jdbc.fecharConexao();
        }else{
            System.out.println("nao conectou no BD via jdbc ...");

        }

    }


    //@Test
    public void testPersistenciaConexao() throws Exception {

        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("conectou no BD via jdbc ...");
            jdbc.fecharConexao();
        }else{
            System.out.println("nao conectou no BD via jdbc ...");

        }

    }

}
