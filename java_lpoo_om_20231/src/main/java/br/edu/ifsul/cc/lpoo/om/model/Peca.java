
package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;


@Entity
@Table(name = "tb_peca")
@NamedQueries({@NamedQuery(name="Peca.orderbynome", query="select p from Peca p order by p.nome asc")})
public class Peca {
    
    @Id
    @SequenceGenerator(name = "seq_peca", sequenceName = "seq_peca_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_peca", strategy = GenerationType.SEQUENCE) 
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
         
    @Column(nullable = true, length = 200)
    private String fornecedor;
    
    public Peca(){
        
    }

    @Override
    public String toString() {
        return "Peca{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", fornecedor='" + fornecedor + '\'' +
                '}';
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }


    
    
}
