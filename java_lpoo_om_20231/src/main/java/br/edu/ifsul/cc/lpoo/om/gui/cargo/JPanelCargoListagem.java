package br.edu.ifsul.cc.lpoo.om.gui.cargo;

import br.edu.ifsul.cc.lpoo.om.Controle;
import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class JPanelCargoListagem  extends JPanel implements ActionListener{
        private JPanelCargo pnlCargo;
        private Controle controle;

        private BorderLayout borderLayout;
        private JPanel pnlNorte;
        private JLabel lblFiltro;
        private JTextField txfFiltro;
        private JButton btnFiltro;

        private JPanel pnlCentro;
        private JScrollPane scpListagem;
        private JTable tblListagem; /// tabela que mostra o cpf, nome e numero ctps
        private DefaultTableModel modeloTabela;//modelo da tabela

        private JPanel pnlSul;
        private JButton btnNovo;
        private JButton btnAlterar;
        private JButton btnRemover;

        private SimpleDateFormat format;


        public JPanelCargoListagem(JPanelCargo pnlCargo, Controle controle){

            this.pnlCargo = pnlCargo;
            this.controle = controle;

            initComponents();
        }

        public void populaTable(){

            DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();//recuperacao do modelo da tabela
            System.out.println("populaTable");

            model.setRowCount(0);//elimina as linhas existentes (reset na tabela)

            try {

                List<Cargo> listCargo = controle.getConexaoJDBC().listCargo();

                for(Cargo c : listCargo){

                    model.addRow(new Object[]{c, c.getId(),c.getDescricao()});
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Erro ao listar Cargos -:"+ex.getLocalizedMessage(), "Cargos", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        }

        private void initComponents(){

            borderLayout = new BorderLayout();
            this.setLayout(borderLayout);//seta o gerenciado border para este painel

            pnlNorte = new JPanel();
            pnlNorte.setLayout(new FlowLayout());

            lblFiltro = new JLabel("Filtrar por Id:");
            pnlNorte.add(lblFiltro);

            txfFiltro = new JTextField(20);
            pnlNorte.add(txfFiltro);

            btnFiltro = new JButton("Filtrar");
            btnFiltro.addActionListener(this);
            btnFiltro.setFocusable(true);    //acessibilidade
            btnFiltro.setToolTipText("btnFiltrar"); //acessibilidade
            btnFiltro.setActionCommand("botao_filtro");
            pnlNorte.add(btnFiltro);

            this.add(pnlNorte, BorderLayout.NORTH);//adiciona o painel na posicao norte.

            pnlCentro = new JPanel();
            pnlCentro.setLayout(new BorderLayout());


            scpListagem = new JScrollPane();
            tblListagem =  new JTable();
            /// o titulo da tabela em cada coluna
            modeloTabela = new DefaultTableModel(
                    new String [] {
                          "Descrição","Id"
                    }, 0);

            tblListagem.setModel(modeloTabela);
            scpListagem.setViewportView(tblListagem);

            pnlCentro.add(scpListagem, BorderLayout.CENTER);


            this.add(pnlCentro, BorderLayout.CENTER);//adiciona o painel na posicao norte.

            pnlSul = new JPanel();
            pnlSul.setLayout(new FlowLayout());

            btnNovo = new JButton("Novo");
            btnNovo.addActionListener(this);
            btnNovo.setFocusable(true);    //acessibilidade
            btnNovo.setToolTipText("btnNovo"); //acessibilidade
            btnNovo.setMnemonic(KeyEvent.VK_N);
            btnNovo.setActionCommand("botao_novo");

            pnlSul.add(btnNovo);

            btnAlterar = new JButton("Editar");
            btnAlterar.addActionListener(this);
            btnAlterar.setFocusable(true);    //acessibilidade
            btnAlterar.setToolTipText("btnAlterar"); //acessibilidade
            btnAlterar.setActionCommand("botao_alterar");

            pnlSul.add(btnAlterar);

            btnRemover = new JButton("Remover");
            btnRemover.addActionListener(this);
            btnRemover.setFocusable(true);    //acessibilidade
            btnRemover.setToolTipText("btnRemvoer"); //acessibilidade
            btnRemover.setActionCommand("botao_remover");

            pnlSul.add(btnRemover);//adiciona o botao na fila organizada pelo flowlayout


            this.add(pnlSul, BorderLayout.SOUTH);//adiciona o painel na posicao norte.

        }




        @Override
        public void actionPerformed(ActionEvent arg0) {

            if(arg0.getActionCommand().equals(btnNovo.getActionCommand())){

                pnlCargo.showTela("tela_cargo_formulario");
///criar um setpara cargo
                pnlCargo.getFormulario().setCargoFormulario(null); //limpando o formulário.
                System.out.println("Novo cargo solicitado");
            }else if(arg0.getActionCommand().equals(btnAlterar.getActionCommand())){
                System.out.println("Alteração solicitada");
                int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
                if(indice > -1){

                    DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                    Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                    Cargo c = (Cargo) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                    try {
                        c = (Cargo) pnlCargo.getControle().getConexaoJDBC().find(Cargo.class, c.getId());
                        System.out.println("Cargo: " + c + " - " + c.getId() + " - " + c.getDescricao());
                        pnlCargo.showTela("tela_cargo_formulario");
                        pnlCargo.getFormulario().setCargoFormulario(c); //limpando o formulário.

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao recuperar Cargos-:"+ex.getLocalizedMessage(), "Cargos", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }



                }else{
                    JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edição", JOptionPane.INFORMATION_MESSAGE);
                }


            }else if(arg0.getActionCommand().equals(btnRemover.getActionCommand())){


                int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
                if(indice > -1){

                    DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table
                    System.out.println("model" + model);
                    Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada
                    System.out.println("linha" + linha);
                   Cargo c = (Cargo) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                    try {
                        pnlCargo.getControle().getConexaoJDBC().remover(c);
                        JOptionPane.showMessageDialog(this, "Cargo removido!", "Cargo", JOptionPane.INFORMATION_MESSAGE);
                        populaTable(); //refresh na tabela

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao remover Cargo -:"+ex.getLocalizedMessage(), "Cargo", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
                }





            }


        }

    }





