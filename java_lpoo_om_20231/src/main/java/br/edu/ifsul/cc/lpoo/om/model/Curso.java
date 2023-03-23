
package br.edu.ifsul.cc.lpoo.om.model;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "tb_curso")
@NamedQueries({@NamedQuery(name="Curso.orderbyid", query="select c from Curso c order by c.id asc")})
public class Curso {
    
    @Id
    @SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_curso", strategy = GenerationType.SEQUENCE) 
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(nullable = true)
    @Temporal(TemporalType.DATE) 
    private Calendar dt_conclusao;
    
    @Column(nullable = true)
    private Integer cargahoraria;
    
    public Curso(){
        
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dt_conclusao=" + dt_conclusao +
                ", cargahoraria=" + cargahoraria +
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

    /**
     * @return the dt_conclusao
     */
    public Calendar getDt_conclusao() {
        return dt_conclusao;
    }

    /**
     * @param dt_conclusao the dt_conclusao to set
     */
    public void setDt_conclusao(Calendar dt_conclusao) {
        this.dt_conclusao = dt_conclusao;
    }

    /**
     * @return the cargahoraria
     */
    public Integer getCargahoraria() {
        return cargahoraria;
    }

    /**
     * @param cargahoraria the cargahoraria to set
     */
    public void setCargahoraria(Integer cargahoraria) {
        this.cargahoraria = cargahoraria;
    }
    
    
}
