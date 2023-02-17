package br.edu.ifsul.cc.lpoo.om.model;

import java.util.Calendar;

public class Curso {
    private Integer Id;
    private String descricao;
    private Calendar dt_conclusão;
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
