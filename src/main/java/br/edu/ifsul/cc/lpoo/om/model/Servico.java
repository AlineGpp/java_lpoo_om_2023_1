package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
@Entity
@Table(name = "tb_servico")
public class Servico  implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_servico", sequenceName = "seq_servico_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_servico", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private Float valor;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_inicio;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_fim;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ManyToOne
    private  StatusServico statusServico;
    @Column(nullable = true)
    @ManyToOne
    private Orcamento orcamento;
    @Column(nullable = true)
    @ManyToOne
    private Equipe equipe;

    @OneToMany(mappedBy = "servico") //mappedBy deve apontar para a referencia de pagamento dentro do Servico.
    private List<Pagamento> pagamentos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Calendar getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Calendar data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Calendar getData_fim() {
        return data_fim;
    }

    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    public StatusServico getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(StatusServico statusServico) {
        this.statusServico = statusServico;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
