
package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Cliente;
import br.edu.ifsul.cc.lpoo.om.model.Curso;
import br.edu.ifsul.cc.lpoo.om.model.Equipe;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import br.edu.ifsul.cc.lpoo.om.model.Peca;
import java.util.List;

/**
 *
 * @author telmo junior.
 */
public interface InterfacePersistencia {
 
    public Boolean conexaoAberta();
    
    public void fecharConexao();
    
    public Object find(Class c, Object id) throws Exception;//select.
    
    public void persist(Object o) throws Exception;//insert ou update.
    
    public void remover(Object o) throws Exception;//delete.
    
    public Funcionario doLogin(String cpf, String senha) throws Exception;
    
    public List<Peca> listPecas() throws Exception;
    
    public List<Funcionario> listFuncionario() throws Exception;
    
    public List<Cliente> listCliente() throws Exception;
    
    public List<Curso> listCurso() throws Exception;
    
    public List<Cargo> listCargo() throws Exception;
    
    public List<Equipe> listEquipe() throws Exception;
}
