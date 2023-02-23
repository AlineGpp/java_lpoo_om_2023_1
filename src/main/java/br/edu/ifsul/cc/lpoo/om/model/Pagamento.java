package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento  implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_pagamento", sequenceName = "seq_pagamento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pagamento", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private Integer numero_parcela;
    @Column(nullable = false)
    private Calendar data_vencimento;
    @Column(nullable = false)
    private Calendar data_pagamento;
    @Column(nullable = false)
    private Float valor;
    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;//agregacao por composic
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ManyToOne
    private FormaPagamento formaPagamento;

    public Pagamento() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero_parcela() {
        return numero_parcela;
    }

    public void setNumero_parcela(Integer numero_parcela) {
        this.numero_parcela = numero_parcela;
    }

    public Calendar getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Calendar data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Calendar getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Calendar data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
