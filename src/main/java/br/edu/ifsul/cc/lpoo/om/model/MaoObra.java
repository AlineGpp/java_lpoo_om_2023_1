package br.edu.ifsul.cc.lpoo.om.model;

import java.util.Date;

public class MaoObra {
    private Integer id;
    private String descricao;
    private Date tempo_estimado_execusao;
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
