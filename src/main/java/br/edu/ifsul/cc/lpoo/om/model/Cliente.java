package br.edu.ifsul.cc.lpoo.om.model;
/**
 * @ManyToMany - agregação
 * @OneToMany - composição
 * @ManyToOne - associação
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@DiscriminatorValue("C")
@Table(name = "tb_cliente")
public class Cliente extends Pessoa {
    @Column(nullable = false)
    private String observacoes;
    @JoinTable(name = "tb_cliente_veiculo", joinColumns = {@JoinColumn(name = "cliente_id")}, //agregacao, vai gerar uma tabela associativa.
            inverseJoinColumns = {@JoinColumn(name = "veiculo_id")})
    private List<Veiculo> veiculos;

    public Cliente() {
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
