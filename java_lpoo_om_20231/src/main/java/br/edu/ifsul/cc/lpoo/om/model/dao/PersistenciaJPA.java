
package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;


public class PersistenciaJPA implements InterfacePersistencia{
    
    public EntityManagerFactory factory;    //fabrica de gerenciadores de entidades
    public EntityManager entity;            //gerenciador de entidades JPA

    
    public PersistenciaJPA(){
        
        //parametro: é o nome da unidade de persistencia (Persistence Unit)
        factory = Persistence.createEntityManagerFactory("pu_java_lpoo_om_20231");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        
        return entity.isOpen(); 
    }

    @Override
    public void fecharConexao() {
        
        entity.close();  
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        return entity.find(c, id); //encontra um determinado registro
    }

    @Override
    public void persist(Object o) throws Exception {
        entity.getTransaction().begin();//abir a transação
        entity.persist(o);//realiza o insert ou update
        entity.getTransaction().commit();//comita a transaçaõ (comando sql)
    }

    @Override
    public void remover(Object o) throws Exception {
        entity.getTransaction().begin();//abir a transação
        entity.remove(o);//realiza o delete
        entity.getTransaction().commit();//comita a transaçaõ (comando sql)
    }


    @Override
    public List<Peca> listPeca() throws Exception {

        return entity.createNamedQuery("Peca.orderbyid").getResultList();
    }

    @Override
    public List<Funcionario> listaFuncionario() throws Exception {
        return entity.createNamedQuery("Funcionario.orderbynome").getResultList();
    }

    @Override
    public List<Curso> listaDeCursos() throws Exception {
        return entity.createNamedQuery("Curso.orderbyid").getResultList();
    }

    @Override
    public List<Cargo> listaCargos() throws Exception {
        return entity.createNamedQuery("Cargo.orderbyid").getResultList();
    }

    @Override
    public Funcionario doLogin(String cpf, String senha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cliente> listaClientes() throws Exception {
        return entity.createNamedQuery("Cliente.orderbycpf").getResultList();
    }

    @Override
    public List<Equipe> listaEquipe() throws Exception {
        return entity.createNamedQuery("Equipe.orderbyid").getResultList();
    }

    @Override
    public List<MaoObra> listaMaoObra() throws Exception {
        return entity.createNamedQuery("MaoObra.orderbyid").getResultList();
    }

    @Override
    public List<Orcamento> listaOrcamento() throws Exception {
        return entity.createNamedQuery("Orcamento.orderbyid").getResultList();
    }

    @Override
    public List<Servico> listaOrdemServico() throws Exception{
        return entity.createNamedQuery("Servico.orderbyid").getResultList();
    }


   @Override
    public List<Veiculo> listaVeiculo() throws Exception {
        return entity.createNamedQuery("Veiculo.orderbyid").getResultList();
    }

    @Override
    public List<Pagamento> listaPagamento() throws Exception {
        return entity.createNamedQuery("Pagamento.orderbyid").getResultList();
    }


}
