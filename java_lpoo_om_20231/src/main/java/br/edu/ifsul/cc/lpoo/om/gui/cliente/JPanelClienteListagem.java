package br.edu.ifsul.cc.lpoo.om.gui.cliente;

import br.edu.ifsul.cc.lpoo.om.Controle;
import br.edu.ifsul.cc.lpoo.om.gui.cargo.JPanelCargo;
import br.edu.ifsul.cc.lpoo.om.model.Cargo;
import br.edu.ifsul.cc.lpoo.om.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class JPanelClienteListagem extends JPanel implements ActionListener{
        private JPanelCliente pnlCliente;
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


        public JPanelClienteListagem(JPanelCliente pnlCliente, Controle controle){

            this.pnlCliente = pnlCliente;
            this.controle = controle;

            initComponents();
        }

        public void populaTable(){

            DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();//recuperacao do modelo da tabela
            System.out.println("populaTable");

            model.setRowCount(0);//elimina as linhas existentes (reset na tabela)

            try {
                List< Cliente> listCliente = controle.getConexaoJDBC().listCliente();

                for(Cliente c : listCliente){

                    model.addRow(new Object[]{c, c.getNome(), c.getCpf(), c.getObservacoes()});
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Erro ao listar Clientes -:"+ex.getLocalizedMessage(), "Clientes", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        }

        private void initComponents(){

            borderLayout = new BorderLayout();
            this.setLayout(borderLayout);//seta o gerenciado border para este painel

            pnlNorte = new JPanel();
            pnlNorte.setLayout(new FlowLayout());

            lblFiltro = new JLabel("Filtrar por Nome:");
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
                         "Observacoes", "Nome","CPF"
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

                pnlCliente.showTela("tela_cliente_formulario");
///criar um setpara cargo
                //pnlCliente.getFormulario().setCargoFormulario(null); //limpando o formulário.
                System.out.println("Novo cliente solicitado");
            }else if(arg0.getActionCommand().equals(btnAlterar.getActionCommand())){
                System.out.println("Alteração solicitada");
                int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
                if(indice > -1){

                    DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                    Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                    Cliente c = (Cliente) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                    try {
                        c = (Cliente) pnlCliente.getControle().getConexaoJDBC().find(Cliente.class, c.getCpf());
                        pnlCliente.showTela("tela_cliente_formulario");
                       // pnlCliente.getFormulario().setClienteFormulario(c); //limpando o formulário.

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao recuperar Clientess-:"+ex.getLocalizedMessage(), "Clientes", JOptionPane.ERROR_MESSAGE);
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

                   Cliente c = (Cliente) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                    try {
                        pnlCliente.getControle().getConexaoJDBC().remover(c);
                        JOptionPane.showMessageDialog(this, "Cliente removido!", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        populaTable(); //refresh na tabela

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao remover Cliente -:"+ex.getLocalizedMessage(), "Cliente", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
                }

            }


        }

    }





