package br.edu.ifsul.cc.lpoo.om.model;

public class Cliente extends Pessoa {
    private String observacoes;

    public Cliente() {
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
