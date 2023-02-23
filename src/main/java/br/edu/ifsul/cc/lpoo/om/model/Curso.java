package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
@Entity
@Table(name = "tb_curso")
public class Curso  implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_curso", strategy = GenerationType.SEQUENCE)
    private Integer Id;
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dt_conclusão;
    @Column(nullable = true)
    private Integer cargaHoraria;

    public Curso() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDt_conclusão() {
        return dt_conclusão;
    }

    public void setDt_conclusão(Calendar dt_conclusão) {
        this.dt_conclusão = dt_conclusão;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
