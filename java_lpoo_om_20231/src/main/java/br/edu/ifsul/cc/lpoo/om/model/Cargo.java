
package br.edu.ifsul.cc.lpoo.om.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author telmo
 */

@Entity
@Table(name = "tb_cargo")
@NamedQueries({@NamedQuery(name="consulta_cargo_orderbyid", query="select c from Cargo c order by c.id asc")})
public class Cargo implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cargo", strategy = GenerationType.SEQUENCE) 
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    public Cargo(){
        
    }
    
    public Cargo(Integer id){
        
        this.id = id;
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
    
    @Override
    public String toString(){
        return descricao;
    }
    
    @Override
    public boolean equals(Object o){

        if(o == null){
            return false;

        }else if(!(o instanceof Cargo)){
            return false;

        }else{
            Cargo c = (Cargo) o;
            if (c.getId().intValue() == this.getId().intValue()){
                return true;
            }else{
                return false;
            }
        }
    }
    
}
