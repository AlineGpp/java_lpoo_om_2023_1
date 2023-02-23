package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
@Entity
@Table(name = "tb_orcamento")
public class Orcamento implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_orcamento", sequenceName = "seq_orcamento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_orcamento", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private String observacoes;
    @Column(nullable = false)
    private Calendar data;
    @Column(nullable = false)
    @ManyToOne
    private Cliente cliente;
   @ManyToMany
   @JoinColumn(name = "veiculo_placa", nullable = false)
   @ManyToOne
    private Veiculo veiculo;
    @ManyToMany // N p/ N
    @JoinTable(name = "tb_orcamento_pecas", joinColumns = {@JoinColumn(name = "orcamento_id")}, //agregacao, vai gerar uma tabela associativa.
            inverseJoinColumns = {@JoinColumn(name = "pecas_id")})
    private List<Peca> pecas;
    @ManyToMany // N p/ N
    @JoinTable(name = "tb_orcamento_maoObra", joinColumns = {@JoinColumn(name = "orcamento_id")}, //agregacao, vai gerar uma tabela associativa.
            inverseJoinColumns = {@JoinColumn(name = "maoObra_id")})
    private List<MaoObra> maoObra;


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


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<MaoObra> getMaoObra() {
        return maoObra;
    }

    public void setMaoObra(List<MaoObra> maoObra) {
        this.maoObra = maoObra;
    }
}
