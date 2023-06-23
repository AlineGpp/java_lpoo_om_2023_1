
package br.edu.ifsul.cc.lpoo.om.model;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author telmo
 */

@Entity
@DiscriminatorValue("F")
@Table(name = "tb_funcionario")
@NamedQueries({@NamedQuery(name="Funcionario.orderbynome", query="select f from Funcionario f order by f.nome asc")})
public class Funcionario extends Pessoa {
    
    @Column(nullable = false, length = 10)
    private String numero_ctps;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE) 
    private Calendar data_admissao;
    
    @Column(nullable = true)
    @Temporal(TemporalType.DATE) 
    private Calendar data_demissao;
    
    
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)    
    private Cargo cargo;
    
    @ManyToMany
    @JoinTable(name = "tb_funcionario_curso", 
            joinColumns = {
                @JoinColumn(name = "pessoa_cpf")}, //agregacao, vai gerar uma tabela associativa.
            inverseJoinColumns = {
                @JoinColumn(name = "curso_id")})
    private List<Curso> cursos;
    
    public Funcionario(){
        
    }

    /**
     * @return the numero_ctps
     */
    public String getNumero_ctps() {
        return numero_ctps;
    }

    /**
     * @param numero_ctps the numero_ctps to set
     */
    public void setNumero_ctps(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    /**
     * @return the data_admissao
     */
    public Calendar getData_admissao() {
        return data_admissao;
    }

    /**
     * @param data_admissao the data_admissao to set
     */
    public void setData_admissao(Calendar data_admissao) {
        this.data_admissao = data_admissao;
    }

    /**
     * @return the data_demissao
     */
    public Calendar getData_demissao() {
        return data_demissao;
    }

    /**
     * @param data_demissao the data_demissao to set
     */
    public void setData_demissao(Calendar data_demissao) {
        this.data_demissao = data_demissao;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the cursos
     */
    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
    @Override
    public String toString(){
        return super.getCpf();
    }
    
    
}
