package br.edu.ifsul.cc.lpoo.om.model;

import java.util.Calendar;
import java.util.List;

public class Orcamento {
    private Integer id;
    private String observacoes;
    private Calendar data;

    private MaoObra maoObra;
    private Cliente cliente;
    private Funcionario funcionario;

    private List<Veiculo> veiculos;
    private List<Peca> pecas;


    public Orcamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public MaoObra getMaoObra() {
        return maoObra;
    }

    public void setMaoObra(MaoObra maoObra) {
        this.maoObra = maoObra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }
}
