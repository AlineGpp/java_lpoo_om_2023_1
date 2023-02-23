package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

///MAPEANDO...

@Entity //indica que a classe veiculo sera gerenciada pelo jpa/hibernate
@Table(name = "tb_veiculo") //define o formato de armazenamento  ( em tabela)
//tudo marcado com table precisa d euma key

public class Veiculo implements Serializable { //permite armazenar em instancia os dados em arquivo, em cache
    @Id// define atributo chave primaria
    private String placa;
    @Column(nullable = false, length = 100) //coluna obrigatória e de 100
    private String modelo;
    @Column(nullable = false)
    private Integer ano;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP) // data completa
    private Calendar data_ultimo_servico;

    public Veiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Calendar getData_ultimo_servico() {
        return data_ultimo_servico;
    }

    public void setData_ultimo_servico(Calendar data_ultimo_servico) {
        this.data_ultimo_servico = data_ultimo_servico;
    }
}
