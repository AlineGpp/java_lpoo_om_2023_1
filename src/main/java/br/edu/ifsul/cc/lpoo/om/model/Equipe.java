package br.edu.ifsul.cc.lpoo.om.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_equipe")
public class Equipe implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_equipe", sequenceName = "seq_equipe_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_equipe", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String especialidades;

    @ManyToMany
    @JoinTable(name = "tb_equipe_funcionario", joinColumns = {@JoinColumn(name = "equipe_id")}, //agregacao, vai gerar uma tabela associativa.
            inverseJoinColumns = {@JoinColumn(name = "funcionario_id")})
    private List<Funcionario> funcionarios;

    public Equipe() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
