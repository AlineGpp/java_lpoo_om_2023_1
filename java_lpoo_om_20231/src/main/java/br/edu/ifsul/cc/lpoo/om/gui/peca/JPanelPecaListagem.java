
package br.edu.ifsul.cc.lpoo.om.gui.peca;

import br.edu.ifsul.cc.lpoo.om.Controle;
import br.edu.ifsul.cc.lpoo.om.model.Peca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author telmo
 */
public class JPanelPecaListagem extends JPanel implements ActionListener {

    private BorderLayout borderLayout;
    private JPanel pnlNorte;
    private JLabel lblTitulo;
    private JPanel pnlCentro;
    private JPanel pnlSul;
    private DefaultTableModel modeloTabela;
    private JScrollPane scpTabela;
    private JTable tblListagem;
    private JButton btnRemover;
    
    private Controle controle;

    public JPanelPecaListagem(Controle controle){
        this.controle = controle;
        initComponents();
        populaTable();
    }

    ///popula a tabela com os dados do banco
    public void populaTable(){

        DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();//recuperacao do modelo da tabela

        model.setRowCount(0);//elimina as linhas existentes (reset na tabela)

        try {

            List<Peca> listPeca = controle.getConexaoJDBC().listPecas();

            for (Peca p : listPeca) {
                model.addRow(new Object[]{p, p.getId(), p.getNome(), p.getValor(), p.getFornecedor()});
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Pecas -:"+ex.getLocalizedMessage(), "Pecas", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    private void initComponents(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());
        lblTitulo = new JLabel("Listagem de Peças");
        pnlNorte.add(lblTitulo);
        
        this.add(pnlNorte, BorderLayout.NORTH);
        
        scpTabela = new JScrollPane();
        
        modeloTabela = new DefaultTableModel(
            new String [] {
               "Fornecedor","Id", "Nome", "Valor"
            }, 0);
        
        tblListagem = new JTable();
        
        tblListagem.setModel(modeloTabela);
        scpTabela = new JScrollPane();
        
        scpTabela.setViewportView(tblListagem);
    
        pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.add(scpTabela, BorderLayout.CENTER);
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        pnlSul = new JPanel();
        
        pnlSul.setLayout(new FlowLayout());
        
        btnRemover = new JButton("Remover");
        btnRemover.setActionCommand("remover");
        btnRemover.addActionListener(this);
        pnlSul.add(btnRemover);
        
        this.add(pnlSul, BorderLayout.SOUTH);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

         if(ae.getActionCommand().equals(btnRemover.getActionCommand())){

            int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table
                System.out.println("model" + model);
                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Peca p = (Peca) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    controle.getConexaoJDBC().remover(p);
                    JOptionPane.showMessageDialog(this, "Peca removido!", "Peca", JOptionPane.INFORMATION_MESSAGE);
                    populaTable(); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Peca -:"+ex.getLocalizedMessage(), "Peca", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    
    }
    
}
