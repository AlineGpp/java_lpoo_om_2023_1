package br.edu.ifsul.cc.lpoo.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

/**
 * Classe contendo tetes unitários para persisitencia de dados utiliizando JDBC
 */
public class TestePersistenciaJDBC {

    @Test
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

    @Test
    public void testPersistenciaPeca() throws Exception {

        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();

        if(jdbc.conexaoAberta()){
            System.out.println("conectou no BD via jdbc ...");
            Peca p = (Peca) jdbc.find(Peca.class,19);

            if (p == null){
                System.out.println("Não encontrou a peca ");
                p = new Peca();
                p.setFornecedor("Fornecedor 1");
                p.setNome("Peca 1");
                p.setValor(10.0f);

                jdbc.persist(p);

                System.out.println("Insereu a peca: " + p.getId());
            }else
                System.out.println("Encontrou a peca: " + p.getId());
            p.setNome("Peca 1 alterada");
            p.setValor(20.0f);
            p.setFornecedor("Fornecedor 1 alterado");

            jdbc.persist(p);
            System.out.println("Alterou a peca: " + p.getId());

            System.out.println("removendo a peca " + p.getId());
            jdbc.remover(p);


            jdbc.fecharConexao();
        }
        else{
            System.out.println("nao conectou no BD via jdbc ...");

        }

    }

    @Test
    public void testPersisitenciaListPeca() throws Exception{
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("conectou no BD via jdbc ...");
            // Atividade prática para 13/04/2023.
            //recuperar a lista de peca
            //se a lista não estiver vazia -> percorrer e imprimir o id de cada peca e remover
            //se a lista estiver vazia -> criar uma peça.

            PersistenciaJDBC jpa = new PersistenciaJDBC();
            if(jpa.conexaoAberta()){
                List<Peca> lista = jpa.listPeca();
                if(lista.isEmpty()){

                    Peca p = new Peca();
                    p.setNome("Peca 1");
                    p.setFornecedor("Fornecedor 1");
                    p.setValor(10.0f);
                    jpa.persist(p);
                    System.out.println("Incluiu o produto: "+ p.getId());
                }else{

                    for(Peca p : lista){
                        System.out.println("Removeu a peca: "+ p.getId());
                        jpa.remover(p);
                    }
                }

                jpa.fecharConexao();
            }
        }else{
            System.out.println("nao conectou no BD via jdbc ...");
        }
    }

    @Test
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

                System.out.printf("inseriu funcionario " + fn.getNome());
            }else{
                System.out.println("Encontrou o funcionario : "+fn.getCpf());
            }

        }else{
            System.out.println("testPersistenciaListPeca: nao conectou no BD via jdbc ...");
        }

    }

}
