package br.edu.ifsul.cc.lpoo.om.model;

import java.util.Calendar;

public class Pagamento {
    private Integer id;
    private Integer numero_parcela;
    private Calendar data_vencimento;
    private Calendar data_pagamento;
    private Float valor;

    private Servico servico;
    private FormaPagamento formaPagamento;


}
