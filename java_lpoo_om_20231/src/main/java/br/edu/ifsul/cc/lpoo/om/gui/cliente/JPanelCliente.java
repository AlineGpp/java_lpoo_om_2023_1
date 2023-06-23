package br.edu.ifsul.cc.lpoo.om.gui.cliente;

import br.edu.ifsul.cc.lpoo.om.Controle;
import br.edu.ifsul.cc.lpoo.om.gui.cargo.JPanelCargoFormulario;
import br.edu.ifsul.cc.lpoo.om.gui.cargo.JPanelCargoListagem;

import javax.swing.*;
import java.awt.*;

public class JPanelCliente extends JPanel {
    private CardLayout cardLayout;
    private Controle controle;

    //private JPanelCargoFormulario formulario;
    private JPanelClienteListagem listagem;

    public JPanelCliente(Controle controle){

        this.controle = controle;
        initComponents();
    }

    private void initComponents(){

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        //formulario = new JPanelCargoFormulario(this, controle);
        listagem = new JPanelClienteListagem(this, controle);

       // this.add(getFormulario(), "tela_cargo_formulario");
        this.add(listagem, "tela_cliente_listagem");

    }

    public void showTela(String nomeTela){

        if(nomeTela.equals("tela_cliente_listagem")){

            listagem.populaTable();

        }

        cardLayout.show(this, nomeTela);

    }

    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }

    /**
     * @return the formulario
     */
   /* public JPanelCargoFormulario getFormulario() {
        return formulario;
    }*/
}
