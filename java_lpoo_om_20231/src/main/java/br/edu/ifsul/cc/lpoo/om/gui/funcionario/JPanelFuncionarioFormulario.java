
package br.edu.ifsul.cc.lpoo.om.gui.funcionario;

import br.edu.ifsul.cc.lpoo.om.Controle;
import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author telmo
 */
public class JPanelFuncionarioFormulario extends JPanel implements ActionListener {
    
    private JPanelFuncionario pnlFuncionario;
    private Controle controle;
    
    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;
    
    private JPanel pnlDadosCadastrais;    
    private JPanel pnlCentroDadosCadastrais;
    
    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblCPF;
    private JTextField txfCPF;
    
    private JLabel lblSenha;
    private JPasswordField txfSenha;
    
    private JLabel lblNumeroCTPS;
    private JTextField txfNumeroCTPS;
    
    private JLabel lblCargo;
    private JComboBox cbxCargo;
    
    private JLabel lblDataAdmissao;
    private JTextField txfDataAdmissao;
        
    private JLabel lblDataNascimento;
    private JTextField txfDataNascimento;
    
    private JLabel lblCEP;
    private JTextField txfCEP;
    
    private JLabel lblComplemento;
    private JTextField txfComplemento;
    
    private JLabel lblNome;
    private JTextField txfNome;
    
    private JLabel lblNumero;
    private JTextField txfNumero;
    
    
    private Funcionario funcionario;
    private SimpleDateFormat format;
    
    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;
    

    
    public JPanelFuncionarioFormulario(JPanelFuncionario pnlFuncionario, Controle controle){
        
        this.pnlFuncionario = pnlFuncionario;
        this.controle = controle;
        
        initComponents();
        
    }
    
    public void populaComboCargo(){
        
        cbxCargo.removeAllItems();//zera o combo

        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxCargo.getModel();

        model.addElement("Selecione"); //primeiro item        
        try {

            List<Cargo> listCargos = controle.getConexaoJDBC().listCargo();
            for(Cargo c : listCargos){
                model.addElement(c);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Cargos -:"+ex.getLocalizedMessage(), "Cargos", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }  
        
        
    }
    
    public Funcionario getFuncionariobyFormulario(){
        
        //validacao do formulario
         if(txfCPF.getText().trim().length() == 11 && 
            new String(txfSenha.getPassword()).trim().length() > 3 && 
            cbxCargo.getSelectedIndex() > 0 &&
            txfDataNascimento.getText().trim().length() == 10 &&
            txfCEP.getText().trim().length() == 8 && 
            txfComplemento.getText().trim().length() > 0 && 
            txfNome.getText().trim().length() > 0 &&
            txfNumero.getText().trim().length() > 0 ){

            Funcionario f = new Funcionario();
            f.setCpf(txfCPF.getText().trim());            
            f.setSenha(new String(txfSenha.getPassword()).trim());
            f.setCargo((Cargo) cbxCargo.getSelectedItem());
            f.setNumero_ctps(txfNumeroCTPS.getText().trim());
            f.setCep(txfCEP.getText().trim());
            f.setComplemento(txfComplemento.getText().trim());
            f.setNome(txfNome.getText().trim());
            f.setNumero(txfNumero.getText().trim());
            format = new SimpleDateFormat("dd/MM/yyyy");
            
            try{
                
                Calendar cl = Calendar.getInstance();
                cl.setTimeInMillis(format.parse(txfDataNascimento.getText()).getTime());
                f.setData_nascimento(cl);
            
            }catch(ParseException ex){
                
                JOptionPane.showMessageDialog(this, "Erro ao formatar data de nascimento -:"+ex.getLocalizedMessage(), "Funcionario", JOptionPane.ERROR_MESSAGE);
           
                return null;
            }
            
            //recuperar a data de admissao, caso não seja nula.
            if(txfDataAdmissao.getText() != null && txfDataAdmissao.getText().trim().length() > 0){
                
                try{
                    Calendar cl = Calendar.getInstance();
                    cl.setTimeInMillis(format.parse(txfDataAdmissao.getText()).getTime());
                    f.setData_admissao(cl);
                }catch(ParseException ex){

                    JOptionPane.showMessageDialog(this, "Erro ao formatar data de admissao -:"+ex.getLocalizedMessage(), "Funcionario", JOptionPane.ERROR_MESSAGE);
                    return null;
                }  
                
            }          
            return f;
         }

         return null;
    }
     
    public void setFuncionarioFormulario(Funcionario f){

        if(f == null){//se o parametro estiver nullo, limpa o formulario
            txfCPF.setText("");
            txfSenha.setText("");
            cbxCargo.setSelectedIndex(0);
            txfNumeroCTPS.setText("");
            txfCPF.setEditable(true);
            txfDataNascimento.setText("");
            txfDataAdmissao.setText("");
            txfCEP.setText("");
            txfComplemento.setText("");
            txfNumero.setText("");
            txfNome.setText("");
            funcionario = null;
            

        }else{

            funcionario = f;
            txfCPF.setEditable(false);
            txfCPF.setText(funcionario.getCpf());
            txfSenha.setText(funcionario.getSenha());
            cbxCargo.getModel().setSelectedItem(funcionario.getCargo());//aqui chama o método equals do classe Endereco
            format = new SimpleDateFormat("dd/MM/yyyy");
            if(funcionario.getData_nascimento() != null)
                txfDataNascimento.setText(format.format(funcionario.getData_nascimento().getTime()));
            txfDataAdmissao.setText(format.format(funcionario.getData_admissao().getTime()));
            txfNumeroCTPS.setText(funcionario.getNumero_ctps());
            txfCEP.setText(funcionario.getCep());
            txfComplemento.setText(funcionario.getComplemento());
            txfNumero.setText(funcionario.getNumero());
            txfNome.setText(funcionario.getNome());
          
                        
        }

    }
    
    private void initComponents(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);
        
        pnlDadosCadastrais = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosCadastrais.setLayout(gridBagLayoutDadosCadastrais);
        
        lblCPF = new JLabel("CPF:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblCPF, posicionador);//o add adiciona o rotulo no painel  
        
        txfCPF = new JTextField(20);        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfCPF, posicionador);//o add adiciona o rotulo no painel  
                
        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblSenha, posicionador);//o add adiciona o rotulo no painel  
        
        txfSenha = new JPasswordField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfSenha, posicionador);//o add adiciona o rotulo no painel  
                
        lblDataNascimento = new JLabel("Data de Nascimento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblDataNascimento, posicionador);//o add adiciona o rotulo no painel  
                
        txfDataNascimento = new JTextField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfDataNascimento, posicionador);//o add adiciona o rotulo no painel  
            
        lblCargo = new JLabel("Cargo:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(lblCargo, posicionador);//o add adiciona o rotulo no painel  
                
        cbxCargo = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(cbxCargo, posicionador);//o add adiciona o rotulo no painel 
                
        lblDataAdmissao = new JLabel("Data de admissão:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblDataAdmissao, posicionador);//o add adiciona o rotulo no painel 
                
        txfDataAdmissao = new JTextField(20);
        txfDataAdmissao.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)        
        pnlDadosCadastrais.add(txfDataAdmissao, posicionador);//o add adiciona o rotulo no painel 
        
        
        lblNumeroCTPS = new JLabel("Numero da CTPS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblNumeroCTPS, posicionador);//o add adiciona o rotulo no painel 
                
        txfNumeroCTPS = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)        
        pnlDadosCadastrais.add(txfNumeroCTPS, posicionador);//o add adiciona o rotulo no painel 
        
        
        lblCEP = new JLabel("CEP:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;//ancoragem a esquerda.
        pnlDadosCadastrais.add(lblCEP, posicionador);//o add adiciona o rotulo no painel 
                
        txfCEP = new JTextField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)        
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfCEP, posicionador);//o add adiciona o rotulo no painel 
        
        lblComplemento = new JLabel("Complemento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;//ancoragem a esquerda.
        pnlDadosCadastrais.add(lblComplemento, posicionador);//o add adiciona o rotulo no painel 
                
        txfComplemento = new JTextField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)        
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfComplemento, posicionador);//o add adiciona o rotulo no painel 
        
        
        lblNome = new JLabel("Nome:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;//ancoragem a esquerda.
        pnlDadosCadastrais.add(lblNome, posicionador);//o add adiciona o rotulo no painel 
                
        txfNome = new JTextField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)        
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfNome, posicionador);//o add adiciona o rotulo no painel 
        
                
        lblNumero = new JLabel("Numero:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;//ancoragem a esquerda.
        pnlDadosCadastrais.add(lblNumero, posicionador);//o add adiciona o rotulo no painel 
                
        txfNumero = new JTextField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)        
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfNumero, posicionador);//o add adiciona o rotulo no painel 
        
        
        
        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);
        
        
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());
        
        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade    
        btnGravar.setToolTipText("btnGravarJogador"); //acessibilidade
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_jogador");
        
        pnlSul.add(btnGravar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade    
        btnCancelar.setToolTipText("btnCancelarJogador"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_jogador");
        
        pnlSul.add(btnCancelar);
        
        this.add(pnlSul, BorderLayout.SOUTH);
        
        format = new SimpleDateFormat("dd/MM/yyyy");
        
    }

    public void actionPerformed(ActionEvent arg0) {
        
        
        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){
            
            Funcionario j = getFuncionariobyFormulario();//recupera os dados do formulÃ¡rio
            
            if(j != null){

                try {
                    
                    pnlFuncionario.getControle().getConexaoJDBC().persist(j);
                    
                    JOptionPane.showMessageDialog(this, "Funcionario armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
                    
                    pnlFuncionario.showTela("tela_funcionario_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Funcionario! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            
            
                pnlFuncionario.showTela("tela_funcionario_listagem");
            
        }
    }
    
}
