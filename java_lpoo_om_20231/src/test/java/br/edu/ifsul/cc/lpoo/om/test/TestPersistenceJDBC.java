
package br.edu.ifsul.cc.lpoo.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Equipe;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJDBC;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author telmo junior
 * @data 30/03/2023
 * 
 * Classe contendo testes unitários para persistencia de dados
 * utilizando JDBC.
 */
public class TestPersistenceJDBC {
    
    //@Test
    public void testPersistenciaListEquie() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("testPersistenciaListEquie: conectou no BD via jdbc ...");
            
            List<Equipe> lista = jdbc.listEquipe();
            if(!lista.isEmpty()){
                
                //questao 4.a
                for(Equipe e: lista){
                    System.out.println("Equipe: "+e.getId());
                    
                    //questao 4.c
                    System.out.println("...Removendo a equipe "+e.getId());
                    jdbc.remover(e);
                     
                }
                
            }else{
               //questao 4.b 
               Equipe e = new Equipe();
               e.setNome("equipe de pintura");
               e.setEspecialidades("pintura em madeira, ferro, alvenaria, etc..");
               
               List<Funcionario> listFunc = new ArrayList();
               listFunc.add(getFuncionario());
               
               e.setFuncionarios(listFunc);
               
                System.out.println("Criando equipe com um funcionario ...");
               jdbc.persist(e);
                
            }
            
            jdbc.fecharConexao();
        }else{
             System.out.println("testPersistenciaListEquie: nao conectou no BD via jdbc ...");
        }
    }
    
    private Funcionario getFuncionario() throws Exception {
        
         //criar um objeto do tipo PersistenciaJDBC.
        Funcionario fn = null;
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("testPersistenciaListFuncionario: conectou no BD via jdbc ...");
            
            fn = (Funcionario) jdbc.find(Funcionario.class, "12345678910");
            if(fn == null){
                fn = new Funcionario();
                
                fn.setCpf("12345678910");
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
        
        return fn;
    }
    
    
    @Test
    public void testPersistenciaFindFuncionario() throws Exception {
        
         //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("testPersistenciaListFuncionario: conectou no BD via jdbc ...");
            
            Funcionario fn = (Funcionario) jdbc.find(Funcionario.class, "12345678910");
            if(fn == null){
                fn = new Funcionario();
                
                fn.setCpf("12345678910");
                fn.setNome("Fulano");
                fn.setCep("99000000");
                fn.setNumero("123");
                fn.setSenha("123456");
                fn.setComplemento("casa");
                fn.setNumero_ctps("1000");
                fn.setData_nascimento(Calendar.getInstance());
                fn.setCargo((Cargo) jdbc.find(Cargo.class, 1));
                System.out.println("cep::"+fn.getCep());
                jdbc.persist(fn);
            }else{
                System.out.println("Encontrou o funcionario : "+fn.getCpf());
                
                fn.setNome("teste");
                jdbc.persist(fn);
                
                System.out.println("Alterou o funcionario : "+fn.getCpf());
                
                jdbc.remover(fn);
                
                  System.out.println("Removeu o funcionario : "+fn.getCpf());
            }
            
        }else{
             System.out.println("testPersistenciaFindFuncionario: nao conectou no BD via jdbc ...");
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
