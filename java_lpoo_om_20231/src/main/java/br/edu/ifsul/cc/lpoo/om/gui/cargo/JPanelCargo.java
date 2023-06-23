package br.edu.ifsul.cc.lpoo.om.gui.cargo;

import br.edu.ifsul.cc.lpoo.om.Controle;

import javax.swing.*;
import java.awt.*;

public class JPanelCargo extends JPanel {
    private CardLayout cardLayout;
    private Controle controle;

    private JPanelCargoFormulario formulario;
    private JPanelCargoListagem listagem;

    public JPanelCargo(Controle controle){

        this.controle = controle;
        initComponents();
    }

    private void initComponents(){

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        formulario = new JPanelCargoFormulario(this, controle);
        listagem = new JPanelCargoListagem(this, controle);

        this.add(getFormulario(), "tela_cargo_formulario");
        this.add(listagem, "tela_cargo_listagem");

    }

    public void showTela(String nomeTela){

        if(nomeTela.equals("tela_cargo_listagem")){

            listagem.populaTable();

        }else if(nomeTela.equals("tela_cargo_formulario")){

           // getFormulario().populaComboCargo();

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
    public JPanelCargoFormulario getFormulario() {
        return formulario;
    }
}
