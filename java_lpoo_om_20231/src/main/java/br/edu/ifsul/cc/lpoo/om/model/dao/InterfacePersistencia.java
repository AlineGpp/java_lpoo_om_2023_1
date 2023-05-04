
package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.*;

import java.util.List;


public interface InterfacePersistencia {
 
    public Boolean conexaoAberta();
    
    public void fecharConexao();
    
    public Object find(Class c, Object id) throws Exception;//select.
    
    public void persist(Object o) throws Exception;//insert ou update.
    
    public void remover(Object o) throws Exception;//delete.
    
    public Funcionario doLogin(String cpf, String senha) throws Exception;

    public List<Peca> listPeca() throws Exception;

    public List<Funcionario> listaFuncionario() throws Exception;

    public List<Curso> listaDeCursos() throws Exception;

    public List<Cargo> listaCargos() throws Exception;

    public  List<Cliente> listaClientes() throws Exception;

    public List<Servico> listaOrdemServico() throws Exception;

    public List<Equipe> listaEquipe() throws Exception;

    public List<MaoObra> listaMaoObra() throws Exception;

    public List<Orcamento> listaOrcamento() throws Exception;

    public List<Pagamento> listaPagamento() throws Exception;

    public List<Veiculo> listaVeiculo() throws Exception;
}

