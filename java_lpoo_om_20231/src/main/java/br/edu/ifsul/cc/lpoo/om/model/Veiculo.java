
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author telmo
 */

@Entity//indica que a classe Veicula será gerenciada
//pelo jpa/hibernate.
@Table(name = "tb_veiculo")//defini o formato do 
//armazenamento (em tabela).
public class Veiculo implements Serializable {
    
    @Id//definicao do atributo que será a chave primaria
    private String placa;
    
    @Column(nullable = false, length = 100)//coluna obrigatorio e de 100.
    private String modelo;
    
    @Column(nullable = false)//coluna obrigatoria
    private Integer ano;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP) //precisao da data
    private Calendar data_ultimo_servico;
    
    public Veiculo(){
        
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the ano
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    /**
     * @return the data_ultimo_servico
     */
    public Calendar getData_ultimo_servico() {
        return data_ultimo_servico;
    }

    /**
     * @param data_ultimo_servico the data_ultimo_servico to set
     */
    public void setData_ultimo_servico(Calendar data_ultimo_servico) {
        this.data_ultimo_servico = data_ultimo_servico;
    }
    
    
    
}
