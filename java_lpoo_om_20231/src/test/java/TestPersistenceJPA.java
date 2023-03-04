
import br.edu.ifsul.cc.lpoo.om.model.dao.PersistenciaJPA;
import org.junit.Test;



/**
 *
 * @author telmo
 */
public class TestPersistenceJPA {
    
    @Test 
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
    
}
