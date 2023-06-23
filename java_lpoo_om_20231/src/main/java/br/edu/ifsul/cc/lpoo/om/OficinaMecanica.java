
package br.edu.ifsul.cc.lpoo.om;

import javax.swing.JOptionPane;

/**
 *
 * @author telmo
 */
public class OficinaMecanica {
    
    private Controle controle;
    
    /* Atividades:
       1) Criar um construtor padrão na classe OficinaMecanica.
       2) No método main criar uma instancia de  OficinaMecanica.
       3) Criar a classe Controle em br.edu.ifsul.cc.lpoo.om
       4) Na classe Controle criar os métodos: conectarBD e initComponents
       5) Implementar o método conectarBD e fecharBD na classe Controle
       6) Criar o pacote br.edu.ifsul.cc.lpoo.om.gui
          e criar a classe JFramePrincipal
       7) Implementar a classe JFramePrincipal
          com base no exemplo do CS::go
       8) Implementar o método initComponents na classe JFramePrincipal
          com base no exemplo do CS::go
    
    */
    private OficinaMecanica(){
        
         try {
                controle = new Controle();//cria a instancia e atribui para o atributo controle.

                ////primeiramente - tenta estabelecer a conexao com o banco de dados.
                if(controle.conectarBD()){

                    //inicializa a interface gráfica.
                    controle.initComponents();

                }else{

                    JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                }

        } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "Erro ao tentar conectar no Banco de Dados: "+ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        
        System.out.println("OficinaMecanica");
        new OficinaMecanica();
    }
}
