
package br.edu.ifsul.cc.lpoo.om.gui.autenticacao;

import br.edu.ifsul.cc.lpoo.om.Controle;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author telmo
 */
public class JPanelAutenticacao extends JPanel implements ActionListener {
    
    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;
    
    private JLabel  lblCPF;
    private JLabel lblSenha;
    private JTextField txfCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;
    
    //construtor da classe que recebe um parametro.
    public JPanelAutenticacao(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
    
        gridLayout = new GridBagLayout();//inicializando o gerenciador de layout
        this.setLayout(gridLayout);//definie o gerenciador para este painel.
        
        
        lblCPF = new JLabel("CPF:");        
        lblCPF.setToolTipText("Campo para a digitação do CPF"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblCPF, posicionador);//o add adiciona o rotulo no painel
        
        //atividade: continuar a codificação para os demais componentes gráficos.
        
        txfCPF = new JTextField(10);
        txfCPF.setFocusable(true);    //acessibilidade    
        txfCPF.setToolTipText("Campo para a digitação do CPF"); //acessibilidade
        //Util.considerarEnterComoTab(txfNickname);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        defaultBorder = txfCPF.getBorder();
        this.add(txfCPF, posicionador);//o add adiciona o rotulo no painel   
        
        
        lblSenha = new JLabel("Senha:");        
        lblSenha.setToolTipText("Campo para a digitação da senha"); //acessibilidade        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblSenha, posicionador);//o add adiciona o rotulo no painel
        
         
        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);    //acessibilidade    
        psfSenha.setToolTipText("Campo para a digitação da senha"); //acessibilidade  
        //Util.considerarEnterComoTab(psfSenha);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(psfSenha, posicionador);//o add adiciona o rotulo no painel 
        
        
        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);    //acessibilidade    
        btnLogar.setToolTipText("Botão para autenticar as credenciais"); //acessibilidade  
        //Util.registraEnterNoBotao(btnLogar);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);//registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);//o add adiciona o rotulo no painel
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("evento: "+ae.getActionCommand());
        if(btnLogar.getActionCommand().equals(ae.getActionCommand())){
            
            System.out.println("evento gerado pelo btnLogar");
            
            txfCPF.setBorder(new LineBorder(Color.BLACK,1));
            psfSenha.setBorder(new LineBorder(Color.BLACK,1));
            
            
            //validacao do formulario.
            if(txfCPF.getText().trim().length() == 11){
                
                txfCPF.setBorder(new LineBorder(Color.GREEN,1));
                
                 if(new String(psfSenha.getPassword()).trim().length() == 6 ){
                     
                    psfSenha.setBorder(new LineBorder(Color.GREEN,1));
                     
                    controle.autenticar(txfCPF.getText().trim(), new String(psfSenha.getPassword()).trim());
                     
                 }else {
                        
                    JOptionPane.showMessageDialog(this, "Informe Senha com 6 dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    psfSenha.setBorder(new LineBorder(Color.RED,1));
                    psfSenha.requestFocus();                                                
                }                
                
            }else{
                
                JOptionPane.showMessageDialog(this, "Informe CPF com 11 dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);                    
                txfCPF.setBorder(new LineBorder(Color.RED,1));
                txfCPF.requestFocus();
                    
            }
        }
        
    }
    
}
