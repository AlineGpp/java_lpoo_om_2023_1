
package br.edu.ifsul.cc.lpoo.om.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author telmo junior
 */

@Entity
@Table(name = "tb_maoobra")
public class MaoObra {
    
    @Id
    @SequenceGenerator(name = "seq_maoobra", sequenceName = "seq_maoobra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_maoobra", strategy = GenerationType.SEQUENCE)         
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIME ) 
    private Date tempo_estimado_execucao;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
    
    public MaoObra(){
        
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the tempo_estimado_execucao
     */
    public Date getTempo_estimado_execucao() {
        return tempo_estimado_execucao;
    }

    /**
     * @param tempo_estimado_execucao the tempo_estimado_execucao to set
     */
    public void setTempo_estimado_execucao(Date tempo_estimado_execucao) {
        this.tempo_estimado_execucao = tempo_estimado_execucao;
    }

    /**
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }
    
    
}
