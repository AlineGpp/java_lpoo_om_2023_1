
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author telmo
 */

@Entity//indica que a classe será gerenciada
//pelo jpa/hibernate.
@Table(name = "tb_orcamento")//defini o formato do 
//armazenamento (em tabela).
public class Orcamento implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_orcamento", sequenceName = "seq_orcamento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_orcamento", strategy = GenerationType.SEQUENCE)  
    private Integer id;
    
    @Column(nullable = true, length = 100)
    private String observacoes;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)    
    private Calendar data;
    
    @ManyToOne
    @JoinColumn(name = "cliente_cpf", nullable = false)
    private Cliente cliente;
    
    //associacao
    @ManyToOne
    @JoinColumn(name = "veiculo_placa", nullable = false)
    private Veiculo veiculo;
    
    
    @ManyToMany
    @JoinTable(name = "tb_orcamento_peca", joinColumns = {@JoinColumn(name = "cliente_cpf")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "peca_id")})     
    private List<Peca> peca;
    
    
    @ManyToMany
    @JoinTable(name = "tb_orcamento_maoobra", joinColumns = {@JoinColumn(name = "cliente_cpf")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "peca_id")})     
    private List<MaoObra> maoObra;
    
    
    @Column(nullable = false, precision = 2)
    private Float valorTotal;
    
    public Orcamento(){
        
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the peca
     */
    public List<Peca> getPeca() {
        return peca;
    }

    /**
     * @param peca the peca to set
     */
    public void setPeca(List<Peca> peca) {
        this.peca = peca;
    }

    /**
     * @return the maoObra
     */
    public List<MaoObra> getMaoObra() {
        return maoObra;
    }

    /**
     * @param maoObra the maoObra to set
     */
    public void setMaoObra(List<MaoObra> maoObra) {
        this.maoObra = maoObra;
    }

    /**
     * @return the valorTotal
     */
    public Float getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
    
}
