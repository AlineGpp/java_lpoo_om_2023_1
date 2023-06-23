package br.edu.ifsul.cc.lpoo.om.gui.cargo;

import br.edu.ifsul.cc.lpoo.om.Controle;
import br.edu.ifsul.cc.lpoo.om.model.Cargo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Optional;

public class JPanelCargoFormulario extends JPanel implements ActionListener {

    private JPanelCargo pnlCargo; ///FAZER UM PARA CARGO TAMBEM
    private Controle controle;

    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosCadastrais;
    private JPanel pnlCentroDadosCadastrais;

    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblId;
    private JTextField txfId;

    private JLabel lblDescricao;
    private JTextField txfDescricao;
    private Cargo cargo;
    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;



    public JPanelCargoFormulario(JPanelCargo pnlCargo, Controle controle){

        this.pnlCargo = pnlCargo;
        this.controle = controle;

        initComponents();

    }
    public Cargo getCargobyFormulario(){

        //validacao do formulario
        if(txfDescricao.getText().trim().length() > 0){
               Cargo c = new Cargo();
                if (cargo.getId() != null) {
                    c.setId(cargo.getId());
                    System.out.println("Obtido id do cargo: " + cargo.getId());
                } else {
                    c.setId(null);
                }
                c.setDescricao(txfDescricao.getText().trim());
            return c;
        }

        return null;
    }

    public void setCargoFormulario(Cargo c){

        if(c == null){//se o parametro estiver nullo, limpa o formulario
                txfDescricao.setText("");
                cargo  = null;

        }else{

            cargo = c;
            txfDescricao.setText(c.getDescricao());

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
/*
       lblId = new JLabel("ID:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblId, posicionador);//o add adiciona o rotulo no painel

        txfId = new JTextField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfId, posicionador);//o add adiciona o rotulo no painel
*/
        GridBagConstraints posicionador = new GridBagConstraints();
        lblDescricao = new JLabel("Descricao:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add( lblDescricao, posicionador);//o add adiciona o rotulo no painel


        txfDescricao = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(txfDescricao, posicionador);//o add adiciona o rotulo no painel


        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);


        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade
        btnGravar.setToolTipText("btnGravarCargo"); //acessibilidade
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_cargo");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade
        btnCancelar.setToolTipText("btnCancelarCargo"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_cargo");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent arg0) {


        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){

            Cargo c = getCargobyFormulario();//recupera os dados do formulÃ¡rio

            if(c != null){

                try {
                    System.out.println("Gravando Cargo: " + c.getDescricao());
                    pnlCargo.getControle().getConexaoJDBC().persist(c);

                    JOptionPane.showMessageDialog(this, "Cargo armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlCargo.showTela("tela_cargo_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Cargo! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }


        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){


            pnlCargo.showTela("tela_cargo_listagem");

        }
    }

}


