package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "tb_maoObra")
public class MaoObra implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_maoObra", sequenceName = "seq_maoObra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_maoObra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo_estimado_execusao;
    @Column(nullable = false)
    private Float valor;

    public MaoObra() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getTempo_estimado_execusao() {
        return tempo_estimado_execusao;
    }

    public void setTempo_estimado_execusao(Date tempo_estimado_execusao) {
        this.tempo_estimado_execusao = tempo_estimado_execusao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
