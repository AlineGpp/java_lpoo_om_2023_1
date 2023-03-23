
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "tb_cargo")
@NamedQueries({@NamedQuery(name="Cargo.orderbyid", query="select c from Cargo c order by c.id asc")})
public class Cargo implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cargo", strategy = GenerationType.SEQUENCE) 
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    public Cargo(){
        
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
