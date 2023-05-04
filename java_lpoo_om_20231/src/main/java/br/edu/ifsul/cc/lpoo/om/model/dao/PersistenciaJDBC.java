package br.edu.ifsul.cc.lpoo.om.model.dao;

import br.edu.ifsul.cc.lpoo.om.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author aline
 * Classe que implementara InterfacePersisitencia e utilização de JDBC para persisitencia dos dados
 */
public class PersistenciaJDBC implements InterfacePersistencia {
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "sql123";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_om_lpo_2023";
    private Connection con = null;

    public PersistenciaJDBC() throws Exception {

        Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
        System.out.println("Tentando estabelecer conexao JDBC com : " + URL + " ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);

    }

    @Override
    public Boolean conexaoAberta() {
        try {
            if (con != null)
                return !con.isClosed();//verifica se a conexao está aberta
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException e) {
            e.printStackTrace();//gera uma pilha de erro na saida.
        }

    }

    /*************************************************FIND*********************************/
    @Override
    public Object find(Class c, Object id) throws Exception {

        if(c == Cargo.class){

            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id, " +
                    "descricao " +
                    "from tb_cargo " +
                    "where id = ? ");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setDescricao(rs.getString("descricao"));

                rs.close();

                return cargo;
            }

        }
        else if (c == Cliente.class) {
            PreparedStatement ps = this.con.prepareStatement("select  p.cpf, "
                    + "c.observacoes, "
                    + "p.tipo," +
                    "p.cep," +
                    "p.complemento," +
                    "p.data_nascimento," +
                    "p.nome," +
                    "p.numero," +
                    "p.senha," +
                    "from tb_cliente c, tb_pessoa p " +
                    "where c.cpf=p.cpf ");

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Cliente cliente = new Cliente();

                cliente.setCpf(rs.getString("cpf"));
                cliente.setObservacoes(rs.getString("observacoes "));
                cliente.setComplemento(rs.getString("complemento"));
                Calendar dtNasc = Calendar.getInstance();
                dtNasc.setTimeInMillis(rs.getDate("data_nascimento").getTime());
                cliente.setData_nascimento(dtNasc);
                cliente.setNome(rs.getString("nome"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setSenha(rs.getString("senha"));

                rs.close();

                PreparedStatement psVeiculos =
                        this.con.prepareStatement("select  v.placa, v.modelo, v.ano,v.data_ultimo_servico "
                                + "from tb_cliente c, tb_veiculo v, tb_cliente_veiculo cv "
                                + "where c.cpf=cv.pessoa_cpf and c.id=cv.veiculo_id");

                ResultSet rsVeiculos = psVeiculos.executeQuery();
                List<Veiculo> veiculos = new ArrayList();

                while (rsVeiculos.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rsVeiculos.getString("placa"));
                    veiculo.setModelo(rsVeiculos.getString("modelo"));
                    veiculo.setAno(rsVeiculos.getInt("ano"));
                    Calendar dtUltServ = Calendar.getInstance();
                    dtUltServ.setTimeInMillis(rsVeiculos.getDate("data_ultimo_servico").getTime());
                    veiculo.setData_ultimo_servico(dtUltServ);
                    veiculos.add(veiculo);
                }

                cliente.setVeiculos(veiculos);
                rsVeiculos.close();
                return cliente;


            }
        } // final else if de Cliente
        else if (c == Curso.class){

            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id," +
                    "cargahoraria," +
                    "descricao," +
                    "dt_conclusao," +
                    "from tb_curso" +
                    "where id = ? ");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Curso curso = new Curso();
                curso.setDescricao(rs.getString("descricao"));
                curso.setCargahoraria(rs.getInt("caragahoraria"));

                return  curso;
            }

        }// final do else if Curso
        else if ( c == Equipe.class){

            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id," +
                    "nome," +
                    "especialidades," +
                    "from tb_equipe" +
                    "where id = ? ");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Equipe equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("nome"));
                equipe.setEspecialidades(rs.getString("especialidades"));

                PreparedStatement psFuncionarios =
                        this.con.prepareStatement("select   f.data_admissao, f.data_demissao, f.numero_ctps, f.cpf, f.cargo_id "
                                + "from tb_equipe e, tb_funcionario f, tb_equipe_funcionario ef "
                                + "where f.cpf=ef.pessoa_cpf and e.id=equipe_id");
                ResultSet rsFuncionarios = psFuncionarios.executeQuery();

                List<Funcionario> funcionarios = new ArrayList();

                while (rsFuncionarios.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setNumero_ctps("numero_ctps");
                    Calendar dtAdmissao = Calendar.getInstance();
                    dtAdmissao.setTimeInMillis(rsFuncionarios.getDate("data_admissao").getTime());
                    funcionario.setData_admissao(dtAdmissao);
                    Calendar dtDemissao =  Calendar.getInstance();
                    dtDemissao.setTimeInMillis(rsFuncionarios.getDate("data_demissao").getTime());
                    funcionario.setData_demissao(dtDemissao);
                    funcionario.setNumero_ctps("numero_ctps");
                    funcionario.setCpf("cpf");
                    Cargo cargo = new Cargo();
                    funcionario.setCargo(cargo);

                }
                rsFuncionarios.close();
                return  equipe;
            }


        }//final do else if Equipe
        else if (c == Funcionario.class) {

            PreparedStatement ps = this.con.prepareStatement("SELECT data_admissao, data_demissao, numero_ctps, cpf, cargo_id FROM tb_funcionario  WHERE cpf = ?");

            ps.setString(1,id.toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Funcionario funcionario = new Funcionario();
                Calendar dtAdmissao = Calendar.getInstance();
                dtAdmissao.setTimeInMillis(rs.getDate("data_admissao").getTime());
                funcionario.setData_admissao(dtAdmissao);
                Calendar dtDemissao = Calendar.getInstance();
                if (rs.getDate("data_demissao") != null){
                    dtDemissao.setTimeInMillis(rs.getDate("data_demissao").getTime());
                }
                funcionario.setData_demissao(dtDemissao);
                funcionario.setNumero_ctps(rs.getString("numero_ctps"));
                funcionario.setCpf(rs.getString("cpf"));
                //rs.close();


                PreparedStatement psCursos = this.con.prepareStatement("SELECT cur.id, cur.cargahoraria, cur.descricao, f.cpf FROM tb_curso cur,tb_funcionario f, tb_funcionario_curso cf where cf.pessoa_cpf =f.cpf and cf.curso_id = cur.id;");

                ResultSet rsCursos = psCursos.executeQuery();

                List<Curso> cursos = new ArrayList();

                while (rsCursos.next()){
                    Curso curso = new Curso();
                    curso.setDescricao(rs.getString("descricao"));
                    curso.setCargahoraria(rs.getInt("cargahoraria"));
                    cursos.add(curso);
                }
                funcionario.setCursos(cursos);
                rsCursos.close();
                return  funcionario;
            }

        }//final do else if Funcionario
        else if (c == MaoObra.class) {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, descricao, tempo_estimado_execucao, valor FROM public.tb_maoobra where id = ?");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MaoObra maoObra = new MaoObra();
                maoObra.setId(rs.getInt("id"));
                maoObra.setDescricao(rs.getString("descricao"));
                Calendar cl = Calendar.getInstance();
                cl.setTimeInMillis(rs.getDate("tempo_estimado_execucao").getTime());
                maoObra.setTempo_estimado_execucao(cl.getTime());
                maoObra.setValor(rs.getFloat("valor"));
                rs.close();
                return maoObra;
            }

        } //final do if else MaoObra
        else if (c == Orcamento.class) {

            PreparedStatement ps = this.con.prepareStatement("SELECT id, data, observacoes, valortotal, cliente_cpf, veiculo_placa FROM tb_orcamento where id = ?");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Orcamento orcamento = new Orcamento();
                orcamento.setId(rs.getInt("id"));
                orcamento.setObservacoes(rs.getString("observacoes"));
                Calendar cl = Calendar.getInstance();
                cl.setTimeInMillis(rs.getDate("dt_conclusao").getTime());
                orcamento.setData(cl);

                Veiculo veiculo = new Veiculo();
                orcamento.setVeiculo(veiculo);


                PreparedStatement psPeca = this.con.prepareStatement("SELECT p.id, p.fornecedor, p.nome, p.valor FROM tb_orcamento o,tb_peca p,tb_orcamento_maoobra om where p.id = ?");

                ResultSet rsPecas = psPeca.executeQuery();

                List<Peca> pecas = new ArrayList();

                while (rsPecas.next()) {
                   Peca peca = new Peca();
                   peca.setNome(rsPecas.getString("nome"));;
                   peca.setFornecedor(rsPecas.getString("fornecedor"));
                   peca.setValor(rsPecas.getFloat("valor"));
                   pecas.add(peca);
                }

                orcamento.setPeca(pecas);
                rsPecas.close();

                PreparedStatement psMaoObra = this.con.prepareStatement("SELECT mao.id, mao.descricao, mao.tempo_estimado_execucao, mao.valor FROM tb_maoobra mao,tb_orcamento o,tb_orcamento_maoobra om " +
                        "where id = ?");
                ResultSet rsMaoObra = psMaoObra.executeQuery();

                List<MaoObra> maoObras = new ArrayList<>();

                while (rsMaoObra.next())
                {
                    MaoObra maoObra = new MaoObra();
                    maoObra.setDescricao(rsMaoObra.getString("descricao"));
                    maoObra.setValor(rsMaoObra.getFloat("valor"));
                    Calendar execucao = Calendar.getInstance();
                    cl.setTimeInMillis(rs.getDate("tempo_estimado_execucao").getTime());
                    maoObra.setTempo_estimado_execucao(execucao.getTime());

                }

                rs.close();

                return orcamento;

            }


        }// final do else if Orcamento
        else if (c == Pagamento.class) {
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id, data_pagamento, data_vencimento, formapagamento, numero_parcela, valor, servico " + "where id = ? ");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                Calendar cl = Calendar.getInstance();
                cl.setTimeInMillis(rs.getDate("data_pagamento").getTime());
                pagamento.setData_pagamento(cl);
                Calendar cl2 = Calendar.getInstance();
                cl2.setTimeInMillis(rs.getDate("data_vencimento").getTime());
                pagamento.setData_vencimento(cl2);

                if (rs.getString("formapagamento").equals("PIX")) {
                    pagamento.setFormaPagamento(FormaPagamento.PIX);
                } else if (rs.getString("formapagamento").equals("CARTAO_CREDITO")) {
                    pagamento.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
                } else if (rs.getString("formapagamento").equals("CARTAO_DEBITO")) {
                    pagamento.setFormaPagamento(FormaPagamento.CARTAO_DEBITO);
                } else if (rs.getString("formapagamento").equals("PARCELAMENTO")) {
                    pagamento.setFormaPagamento(FormaPagamento.PARCELAMENTO);
                    pagamento.setNumero_parcela(rs.getInt("numero_parcela"));
                    pagamento.setValor(rs.getFloat("valor"));

                    PreparedStatement psServico = this.con.prepareStatement("SELECT s.id, s.data_fim, s.data_inicio, s.status, s.valor, s.equipe_id, s.orcamento_id,p.servico_id e.id FROM tb_servico s ,tb_pagamento p, tb_equipe e where s.id =p.servico_id and s.id =e.equipe_id ; ");

                    ResultSet rsServico = psServico.executeQuery();

                    Servico servico = new Servico();

                    Calendar dtFim = Calendar.getInstance();
                    dtFim.setTimeInMillis(rsServico.getDate("data_fim").getTime());
                    servico.setData_fim(dtFim);

                    Calendar dtInicio = Calendar.getInstance();
                    dtInicio.setTimeInMillis(rsServico.getDate("data_inicio").getTime());
                    servico.setData_inicio(dtInicio);
                    servico.setStatus(StatusServico.valueOf(rsServico.getString("status")));
                    servico.setValor(rsServico.getFloat("valor"));
                    Equipe equipe = new Equipe();
                    servico.setEquipe(equipe);

                  pagamento.setServico(servico);

                    rs.close();
                    return pagamento;
                }

            }

        } //final pagamento
        else if (c == Peca.class) {

            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id, " +
                    "fornecedor, " +
                    "nome," +
                    "valor " +
                    "from tb_peca " +
                    "where id = ? ");
            //1 representa o primeiro parametro (?)
            ///converte o id que é um objeto pra string e de string para inteiro
            ps.setInt(1, Integer.valueOf(id.toString()));

            //executa o comando SQL(select)
            ResultSet rs = ps.executeQuery();
            // o metodo next recupera a proxima linha do resultado se existir uma linha retorna verdadeiro se nao existir uma linha retorna falso
            if (rs.next()) {
                Peca peca = new Peca();
                peca.setId(rs.getInt("id")); // passa o nome da coluna para recuperar através dela
                peca.setNome(rs.getString("nome"));
                peca.setValor(rs.getFloat("valor"));
                peca.setFornecedor(rs.getString("fornecedor"));
                rs.close();//fechar o cursor do DB para essa consulta
                return peca;//retorna o objeto peca
            }

        }//final do if else de peca
        else if (c == Veiculo.class) {

          PreparedStatement ps = this.con.prepareStatement("SELECT placa, ano, data_ultimo_servico, modelo FROM tb_veiculo;");

          ResultSet rsVeiculo = ps.executeQuery();

          if (rsVeiculo.next()){
              Veiculo veiculo = new Veiculo();
              veiculo.setPlaca(rsVeiculo.getString("placa"));
              veiculo.setAno(rsVeiculo.getInt("ano"));
              Calendar dtUltServ = Calendar.getInstance();
              dtUltServ.setTimeInMillis(rsVeiculo.getDate("data_ultimo_servico").getTime());
              veiculo.setData_ultimo_servico(dtUltServ);
              veiculo.setModelo(rsVeiculo.getString("modelo"));
          }


        }//final do else if de Veiculo

        return null; /// classe não encontrada!
    }

    /****************************************************PERSIST*****************************************************/
    @Override
    public void persist(Object o) throws Exception {
        if (o instanceof Peca) {
            Peca p = (Peca) o; // conversaõ ou casting,modelagem de objeto para Peca.
            //testa par descobrir se existe informação na chave primária
            if (p.getId() == null) {
                //insert into tb_peca
                PreparedStatement ps = this.con.prepareStatement("insert into tb_peca "
                        + "(id, "
                        + "fornecedor, "
                        + "nome, "
                        + "valor) "
                        + "values ("
                        + "nextval('seq_peca_id'), "
                        + "?, "
                        + "?, "
                        + "?);");

                ps.setString(1, p.getFornecedor());
                ps.setString(2, p.getNome());
                ps.setFloat(3, p.getValor());


                ps.execute();//executa o insert

                ps.close();//fecha o cursor

            } else { //
                //update tb_peca
                PreparedStatement ps = this.con.prepareStatement("update tb_peca set "
                        + "fornecedor = ?, "
                        + "nome = ?, "
                        + "valor = ? "
                        + "where "
                        + "id = ? ");

                ps.setString(1, p.getFornecedor());
                ps.setString(2, p.getNome());
                ps.setFloat(3, p.getValor());
                ps.setInt(4, p.getId());

                ps.execute();//executa o insert

                ps.close();//fecha o cursor
            }
        } /// final de peca

        else if (o instanceof Cargo) {
            Cargo c = (Cargo) o;
            if (c.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_cargo "
                        + "(id, "
                        + "descricao,) "
                        + "values ("
                        + "nextval('seq_cargo_id'), "
                        + "?);");

                ps.setString(1, c.getDescricao());
                ps.execute();
                ps.close();
            } else {
                //update tb_cargo
                PreparedStatement ps = this.con.prepareStatement("update tb_cargo set "
                        + "descricao = ? "
                        + "where "
                        + "id = ? ");
                ps.setString(1, c.getDescricao());

                ps.execute();
                ps.close();
            }
        } // final de cargo

        else if (o instanceof Cliente) {
            Cliente c = (Cliente) o;
            if (c.getData_nascimento() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_cliente "
                        + "(id, "
                        + "observacoes, )"
                        + "values ("
                        + "nextval('seq_cargo_id'), "
                        + "?);");

                ps.setString(1, c.getObservacoes());
                ps.execute();
                ps.close();
            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_cliente set "
                        + "observacoes = ? "
                        + "where "
                        + "id = ? ");
                ps.setString(1, c.getObservacoes());

                ps.execute();
                ps.close();
            }
        } // final de Cliente

    else if (o instanceof Curso) {
            Curso c = (Curso) o;
            if (c.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_curso "
                        + "(id, "
                        + "cargahoraria,"
                        + "descricao, "
                        + "dt_conclusao)"
                        + "values ("
                        + "nextval('seq_curso_id'), "
                        + "?, "
                        + "?, "
                        + "?);");

                ps.setInt(1, c.getCargahoraria());
                ps.setString(2, c.getDescricao());
                //ps.setDate(3, new java.sql.Date(c.getDt_conclusao().getTimeInMillis()));

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_curso set "
                        + "cargahoraria = ?, "
                        + "descricao = ?, "
                        + "dt_conclusao = ? "
                        + "where "
                        + "id = ? ");
                ps.setInt(1, c.getCargahoraria());
                ps.setString(2, c.getDescricao());
                //ps.setDate(3,c.getDt_conclusao());

                ps.execute();
                ps.close();
            }
        } // final de curso

        else if (o instanceof Equipe) {
            Equipe e = (Equipe) o;
            if (e.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_equipe "
                        + "(id, "
                        + "nome, "
                        + "especialidaes,) "
                        + "values ("
                        + "nextval('seq_equipe_id'), "
                        + "?, "
                        + "?);");

                ps.setString(1, e.getNome());
                ps.setString(2, e.getEspecialidades());
                ps.execute();
                ps.close();
            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_equipe set "
                        + "nome = ?, "
                        + "especialidaes = ? "
                        + "where "
                        + "id = ? ");
                ps.setString(1, e.getNome());
                ps.setString(2, e.getEspecialidades());

                ps.execute();
                ps.close();
            }
        } //final de equipe

        else if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;
            //não tem como gerar um cpf por isso que usamos a data de admissão
            //utilizando a data_admissao
            if (f.getData_admissao() == null) {
                //insert into tb_funcionario
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (cpf, nome, numero, senha, complemento, tipo, cep, data_nascimento) values "
                        + "("
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "? "
                        + ")");

                ps.setString(1, f.getCpf());
                ps.setString(2, f.getNome());
                ps.setString(3, f.getNumero());
                ps.setString(4, f.getSenha());
                ps.setString(5, f.getComplemento());
                ps.setString(6, "F");
                ps.setString(7, f.getCep());
                ps.setDate(8, new java.sql.Date(f.getData_nascimento().getTimeInMillis()));
                ps.execute();
                ps.close();

                ps = this.con.prepareStatement("insert into tb_funcionario (data_admissao, numero_ctps, cpf, cargo_id) values "
                        + "("
                        + "now(), "
                        + "?, "
                        + "?, "
                        + "? "
                        + ")");
                ps.setString(1, f.getNumero_ctps());
                ps.setString(2, f.getCpf());
                ps.setInt(3, f.getCargo().getId());
                ps.execute();

                ps.close();


            } else {
                //update tb_funcionario
               // PreparedStatement ps = this.con.prepareStatement("update tb_funcionario set "

                PreparedStatement ps = this.con.prepareStatement("update  tb_pessoa set (cpf, nome, numero, senha, complemento, tipo, cep, data_nascimento) values "
                        + "("
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "? "
                        + ")");
                ps.setString(1, f.getCpf());
                ps.setString(2, f.getNome());
                ps.setString(3, f.getNumero());
                ps.setString(4, f.getSenha());
                ps.setString(5, f.getComplemento());
                ps.setString(6, "F");
                ps.setString(7, f.getCep());
                ps.setDate(8, new java.sql.Date(f.getData_nascimento().getTimeInMillis()));
                ps.execute();
                ps.close();

                ps = this.con.prepareStatement("update tb_funcionario set (data_admissao, numero_ctps, cpf, cargo_id) values "
                        + "("
                        + "now(), "
                        + "?, "
                        + "?, "
                        + "? "
                        + ")");
                ps.setString(1, f.getNumero_ctps());
                ps.setString(2, f.getCpf());
                ps.setInt(3, f.getCargo().getId());
                ps.execute();

                ps.close();
            }

        } //final de funcionario

        else if (o instanceof MaoObra) {
            MaoObra m = (MaoObra) o;
            if (m.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_mao_obra "
                        + "(id, "
                        + "descricao, "
                        + "tempo_estimado_execucao, "
                        + "valor) "
                        + "values ("
                        + "nextval('seq_mao_obra_id'), "
                        + "?, "
                        + "?, "
                        + "?);");
                ps.setString(1, m.getDescricao());
                //ps.setInt(2, m.getTempo_estimado_execucao());
                ps.setDouble(3, m.getValor());
                ps.execute();
                ps.close();
            }
            else {
                PreparedStatement ps = this.con.prepareStatement("update tb_mao_obra set "
                        + "descricao = ?, "
                        + "tempo_estimado_execucao = ?, "
                        + "valor = ? "
                        + "where "
                        + "id = ? ");
                ps.setString(1, m.getDescricao());
                //ps.setInt(2, m.getTempo_estimado_execucao());
                ps.setDouble(3, m.getValor());
                ps.execute();
                ps.close();
            }
        } // final mao de obra

        else if (o instanceof Orcamento) {
            Orcamento o1 = (Orcamento) o;
            if (o1.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_orcamento "
                        + "(id, "
                        + "observacoes, "
                        + "data, "
                        + "cliente,"
                        + "valoTotal,"
                        + "veiculo) "
                        + "values ("
                        + "nextval('seq_orcamento_id'), "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?);");
                ps.setDate(1, new Date(o1.getData().getTimeInMillis()));
                ps.setString(2, o1.getObservacoes());
                ps.setString(3, o1.getCliente().getCpf());
                ps.setDouble(4, o1.getValorTotal());
                ps.setString(5, o1.getVeiculo().getPlaca());


                ps.execute();
                ps.close();

            }

        } // final de orcamento

        else if (o instanceof Pagamento) {

            Pagamento p = (Pagamento) o;

            if (p.getId() == null) {

                PreparedStatement ps = this.con.prepareStatement("insert into tb_pagamento "
                        + "(id, "
                        + "data_pagamento, "
                        + "data_vencimento, "
                        + "formapagamento, "
                        + "numero_parcela, "
                        + "valor,"
                        + "servico_id)"
                        + "values ("
                        + "nextval('seq_pagamento_id'), "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?);");

                ps.setDate(1, new java.sql.Date(p.getData_pagamento().getTimeInMillis()));
                ps.setDate(2, new  Date(p.getData_vencimento().getTimeInMillis()));
                //ps.setString(3, p.getFormaPagamento());

                ps.setInt(4, p.getNumero_parcela());
                ps.setDouble(5, p.getValor());
                ps.setInt(6, p.getServico().getId());

                ps.execute();
                ps.close();
            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_pagamento set "
                        + "data_pagamento = ?, "
                        + "data_vencimento = ?, "
                        + "formapagamento = ?, "
                        + "numero_parcela = ?, "
                        + "valor = ?, "
                        + "servico_id = ? "
                        + "where "
                        + "id = ? ");

                ps.setDate(1, new java.sql.Date(p.getData_pagamento().getTimeInMillis()));
                ps.setDate(2, new java.sql.Date(p.getData_vencimento().getTimeInMillis()));
                //ps.set(3, p.getFormaPagamento());
                ps.setInt(4, p.getNumero_parcela());
                ps.setDouble(5, p.getValor());
                ps.setInt(6, p.getServico().getId());

                ps.execute();
                ps.close();
            }
        } // final de pagamento

        else if (o instanceof Servico) {
            Servico s = (Servico) o;
            if (s.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_servico "
                        + "(id, "
                        + "data_inicio, "
                        + "data_fim, "
                        + "status, "
                        + "valor, "
                        + "equipe, "
                        + "orcamento) "
                        + "values ("
                        + "nextval('seq_servico_id'), "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?);");

                ps.setDate(1, new java.sql.Date(s.getData_inicio().getTimeInMillis()));
                ps.setDate(2, new java.sql.Date(s.getData_fim().getTimeInMillis()));
                //ps.setStatus(3, s.getStatus());
                ps.setDouble(4, s.getValor());
                ps.setString(5, s.getEquipe().getNome());
                ps.setInt(6, s.getOrcamento().getId());

                ps.execute();
                ps.close();

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_servico set "
                        + "data_inicio = ?, "
                        + "data_fim = ?, "
                        + "status = ?, "
                        + "valor = ?, "
                        + "equipe = ?, "
                        + "orcamento = ? "
                        + "where "
                        + "id = ? ");

                ps.setDate(1, new java.sql.Date(s.getData_inicio().getTimeInMillis()));
                ps.setDate(2, new java.sql.Date(s.getData_fim().getTimeInMillis()));
                //ps.setStatus(3, s.getStatus());
                ps.setDouble(4, s.getValor());
                ps.setString(5, s.getEquipe().getNome());
                ps.setInt(6, s.getOrcamento().getId());

                ps.execute();
                ps.close();
            }
        } /// final servico

        else if (o instanceof Veiculo) {
            Veiculo v = (Veiculo) o;
            if(v.getPlaca() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_veiculo "
                        + "(placa, "
                        + "ano, "
                        + "data_ultimo_servico, "
                        + "modelo,) "
                        + "values ("
                        + "nextval('seq_veiculo_id'), "
                        + "?, "
                        + "?, "
                        + "?; ");

                ps.setInt(1, v.getAno());
                ps.setDate(2, new java.sql.Date(v.getData_ultimo_servico().getTimeInMillis()));
                ps.setString(3, v.getModelo());

                ps.execute();
                ps.close();

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_veiculo set "
                        + "ano = ?, "
                        + "data_ultimo_servico = ?, "
                        + "modelo = ? "
                        + "where "
                        + "placa = ? ");

                ps.setInt(1, v.getAno());
                ps.setDate(2, new java.sql.Date(v.getData_ultimo_servico().getTimeInMillis()));
                ps.setString(3, v.getModelo());

                ps.execute();
                ps.close();

            }
        } // final veiculo
    }///final persist

    @Override
    public Funcionario doLogin(String cpf, String senha) throws Exception {
        return null;
    }

    //////////////////////LISTAS ///////////////////////
    @Override
    public List<Peca> listPeca() throws Exception {

        List<Peca> listagemRetorno;
        //select na tb_peca
        PreparedStatement ps =
                this.con.prepareStatement("select "
                        + "id, "
                        + "fornecedor, "
                        + "nome, "
                        + "valor "
                        + "from tb_peca "
                        + "order by id asc ");

        //executa o comando SQL (select)
        ResultSet rs = ps.executeQuery();

        listagemRetorno = new ArrayList();

        //o método next recupera a proxima linha do resultado,
        //se exitir uma linha retorna verdadeiro
        //se nao existir uma linha retorna false.
        while (rs.next()) {

            Peca p = new Peca();
            p.setId(rs.getInt("id"));//recupera pelo nome da coluna
            p.setNome(rs.getString("nome"));
            p.setFornecedor(rs.getString("fornecedor"));
            p.setValor(rs.getFloat("valor"));

            listagemRetorno.add(p);
        }

        rs.close();//fecha o cursor do BD para essa consulta

        return listagemRetorno;//retorna a lista.
    }

    @Override
    public List<Funcionario> listaFuncionario() throws Exception {
        List<Funcionario> lista;

        PreparedStatement ps = this.con.prepareStatement(" select p.cpf, p.nome, p.senha, f.cargo, f.numero_ctps,  p.data_admissao "
                + " from tb_pessoa p, tb_funcionario f where p.cpf=f.cpf order by p.data_admissao asc");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList();

        while(rs.next()){

            Funcionario f = new Funcionario();
            f.setCpf(rs.getString("cpf"));
            f.setNome(rs.getString("nome"));
            f.setSenha(rs.getString("senha"));
           /// f.setCargo(Cargo.getCargo(rs.getString("cargo")));
            f.setNumero_ctps(rs.getString("numero_ctps"));
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(rs.getDate("data_admissao").getTime());
            f.setData_admissao(c);

            lista.add(f);

        }

        return lista;
    }

    ////CONTINUAR AS OUTRAS LISTAS AQUI

    @Override
    public List<Curso> listaDeCursos() throws Exception {
        List<Curso> listCurso = null;

        PreparedStatement ps = this.con.prepareStatement("SELECT id, cargahoraria, descricao" +
                "\tFROM tb_curso where  id =?;");
        ResultSet rs = ps.executeQuery();

        listCurso = new ArrayList();

        while(rs.next()){

            Curso c = new Curso();
            c.setId(rs.getInt("id"));
            c.setCargahoraria(rs.getInt("cargahoraria"));
            c.setDescricao(rs.getString("descricao"));

            listCurso.add(c);

        }

        rs.close();
        ps.close();

        return listCurso;
    }

    @Override
    public List<Cargo> listaCargos() throws Exception {
        List<Cargo> listCargo;

        PreparedStatement ps = this.con.prepareStatement(" select c.id, c.descricao,f.numero_ctps, f.data_admissao, f.data_demissao, f.cargo, f.cursos "
                + " from tb_cargo c, tb_funcionario f where c.cpf = f.cpf order by id asc");

;
        ResultSet rs = ps.executeQuery();

        listCargo = new ArrayList();

        while (rs.next()) {
            Cargo c = new Cargo();
            c.setId(rs.getInt("id"));

            c.setDescricao(rs.getString("descricao"));

            listCargo.add(c);
        }

        return listCargo;
    }
    public  List<Cliente> listaClientes() throws Exception
    {
        return  null;
    }

    public List<Servico> listaOrdemServico() throws Exception{
        return null;
    }

    public List<Equipe> listaEquipe() throws Exception{
        return null;
    }

    public List<MaoObra> listaMaoObra() throws Exception{
        return null;
    }


    public List<Orcamento> listaOrcamento() throws Exception{
        return null;
    }

    public List<Pagamento> listaPagamento() throws Exception{
        return null;
    }

    public List<Veiculo> listaVeiculo() throws Exception{
        return null;
    }

    @Override
    public void remover(Object o) throws Exception {
        if (o instanceof Peca) {
            Peca p = (Peca) o; // conversaõ ou casting,modelagem de objeto para Peca.
            //testa par descobrir se existe informação na chave primária

            //delete from tb_peca
            PreparedStatement ps = this.con.prepareStatement("delete from tb_peca where id = ?;");

            ps.setInt(1, p.getId());

            ps.execute();//executa o insert

            ps.close();//fecha o cursor
        } else if (o instanceof Cargo) {
            Cargo c = (Cargo) o;
            PreparedStatement ps = this.con.prepareStatement("delete  from tb_cargo where  id = ?;");
            ps.setInt(1,c.getId());
            ps.execute();
            ps.close();

        } else if (o instanceof Funcionario) {

            Funcionario f = (Funcionario) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_funcionario where cpf = ? ");
            ps.setString(1, f.getCpf());

            ps.execute();
            ps.close();

            ps = this.con.prepareStatement("delete from tb_pessoa where cpf = ? ");
            ps.setString(1, f.getCpf());

            ps.execute();
            ps.close();
        } else if (o instanceof Cliente)
        {
            Cliente c = (Cliente) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_cliente where cpf = ? ");
            ps.setString(1, c.getCpf());

            ps.execute();
            ps.close();

            ps = this.con.prepareStatement("delete from tb_pessoa where cpf = ? ");
            ps.setString(1, c.getCpf());

            ps.execute();
            ps.close();

        } else if (o instanceof Curso) {

            Curso c = (Curso) o; // conversaõ ou casting,modelagem de objeto para Peca.
            //testa par descobrir se existe informação na chave primária

            PreparedStatement ps = this.con.prepareStatement("delete from tb_curso where id = ?;");

            ps.setInt(1, c.getId());

            ps.execute();//executa o insert

            ps.close();//fecha o cursor
        } else if (o instanceof Equipe) {
           Equipe e = (Equipe) o;

            //remove as linhas na tabela associativa.
            PreparedStatement ps2 = this.con.prepareStatement("delete from tb_equipe_funcionario where equipe_id = ?");
            ps2.setInt(1, e.getId());
            ps2.execute();

            //remove as linhas na tabela.
            PreparedStatement ps3 = this.con.prepareStatement("delete from tb_equipe where id = ?");
            ps3.setInt(1, e.getId());
            ps3.execute();
        } else if (o instanceof MaoObra) {
            MaoObra m = (MaoObra) o;

            PreparedStatement ps = this.con.prepareStatement("delete  from tb_maoobra where  id = ?");

            ps.setInt(1,m.getId());

            ps.executeQuery();
        } else if (o instanceof  Orcamento) {
            Orcamento orca = (Orcamento) o;

            PreparedStatement ps = this.con.prepareStatement("delete  from tb_orcamento where  id = ?");

            ps.setInt(1,orca.getId());

            ps.executeQuery();
        } else if (o instanceof Pagamento) {
           Pagamento pg = (Pagamento) o;

            PreparedStatement ps = this.con.prepareStatement("delete  from tb_pagamento where  id = ?");

            ps.setInt(1,pg.getId());

            ps.executeQuery();
        } else if (o instanceof  Servico) {
            Servico s = (Servico) o;
            PreparedStatement ps = this.con.prepareStatement("delete  from tb_servico where  id = ?");

            ps.setInt(1,s.getId());
            ps.executeQuery();

        } else if (o instanceof Veiculo) {
            Veiculo v = (Veiculo) o;
            PreparedStatement ps = this.con.prepareStatement("delete  from tb_veiculo where  id = ?");

            ps.setString(1,v.getPlaca());
            ps.executeQuery();
        }

    }
}

