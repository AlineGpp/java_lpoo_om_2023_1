package br.edu.ifsul.cc.lpoo.om.test;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJPA;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author telmo
 */
public class TestPersistenceJPA {
    
    //@Test 
    public void testConexaoGeracaoTabelas(){
        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");
            
            persistencia.fecharConexao();
            
            System.out.println("fechou a conexao com o BD via JPA");
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    } 
    /*
        Exercicio 1: inserir nova Peça.
    */
    //@Test 
    public void testPersitenciaPeca() throws Exception{
        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");
            
            Peca p = new Peca();
            
            p.setFornecedor("fornecedor de teste");
            p.setNome("bieleta");
            p.setValor(100f);            
            persistencia.persist(p);
            
            System.out.println("Inseriu a peca id="+p.getId());
            
            Peca pc = (Peca) persistencia.find(Peca.class, 1);
            
            System.out.println("Encontrou a peca id="+pc.getId());
            
            persistencia.fecharConexao();
            
            System.out.println("fechou a conexao com o BD via JPA");
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    }
    
    /*
        Exercicio 2:
         Passo 1: encontrar a peça id = 1
         Passo 2: caso encontre remove-la
         Passo 3: caso não eoncontre criar nova peça.
    */
    
    //@Test 
    public void testFindPeca() throws Exception{
    
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            Peca p = (Peca) persistencia.find(Peca.class, 1);
            if(p == null){
                p = new Peca();
                p.setFornecedor("fornecedor de teste");
                p.setNome("biela");
                p.setValor(100f);            
                persistencia.persist(p);
                
            }else{
                
                System.out.println("Encontrou a peca id="+p.getId());
                
                persistencia.remover(p);
                
                System.out.println("Removeu a peca id="+p.getId());
            }
            
            persistencia.fecharConexao();
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }                
    
    }

    /*
        Exercício 3 :
        Passo 1: recuperar a lista de peças.
        Passo 2: se a lista.size() > 0 listar e remover.
        Passo 3: se a lista.size() == 0 inserir duas Peças.
    */
    
    //@Test 
    public void testListPeca() throws Exception{
    
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            List<Peca> list = persistencia.listPecas();
            if(list.isEmpty()){
                
                Peca p = new Peca();
                p.setFornecedor("fornecedor de teste");
                p.setNome("bieleta");
                p.setValor(100f);            
                persistencia.persist(p);
                
                p = new Peca();
                p.setFornecedor("fornecedor de teste 2");
                p.setNome("mancal");
                p.setValor(100f);            
                persistencia.persist(p);
                
            }else{
                
                for(Peca p : list){
                    
                    System.out.println(p);
                    persistencia.remover(p);
                }
            }
            
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    
    }
    
    /*
        Exercicio 4:
            Passo 1: listar dos os funcionarios com os seus respectivos curso.
            Passo 2: se a lista.size() > 0 listar e remover o funcionario.
            Passo 3: se a lista.size() == 0 inserir um funcionario e associar cursos.    
    */
    
    @Test 
    public void testListFunc() throws Exception{
    
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            
            List<Funcionario> list = persistencia.listFuncionario();
            
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
        
        List<Curso> list = jpa.listCurso();
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
        
        List<Cargo> list = jpa.listCargo();
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
