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

    @Override
    public Object find(Class c, Object id) throws Exception {
        if (c == Peca.class) {//select tb_peca
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id, " +
                    "fonecedor, " +
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

        } else if (c == Funcionario.class) {
            //select to_char(data_admissao, 'dd/MM/yyyy') as data_admissao, to_char (data_admissao,data_admissao)from tb_funcionario ;
            // fazer o selcet na tabela Pessoa também

            PreparedStatement ps = this.con.prepareStatement("SELECT data_admissao, data_demissao, numero_ctps, cpf, cargo_id FROM tb_funcionario"
                            + " WHERE id = ?");

            ResultSet rs = ps.executeQuery();

            Calendar dtCad = Calendar.getInstance();
            dtCad.setTimeInMillis(rs.getDate("data_admissao").getTime());

            if (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setDescricao(rs.getString("descricao"));

                rs.close();
                return cargo;
            }



        } else if (c == Cargo.class) {
            //select tb_cargo
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id, " +
                    "descicao " +
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
        } else if (c == Cliente.class) {
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "cpf, " +
                    "observacoes, " +
                    "from tb_cliente " +
                    "where id = ? ");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setCpf(rs.getString("observacoes "));

                rs.close();
                return cliente;
            }
        } else if (c== Curso.class) {
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id," +
                    "cargahoraria," +
                    "descricao," +
                    "dt_conclusao," +
                    "from tb_curso" +
                    "where id = ? ");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               Curso curso = new Curso();
               curso.setDescricao(rs.getString("descricao"));
               curso.setCargahoraria(rs.getInt("caragahoraria"));
               //curso.setDt_conclusao(rs.("dt_conclusao"));
            }

        } else if (c== Equipe.class) {
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id," + "nome," + "especialidades"+ "from tb_equipe" + "where id = ? ");
            ps.setInt(1, Integer.valueOf(id.toString()));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Equipe equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("nome"));
                equipe.setEspecialidades(rs.getString("especialidades"));
            }
        } else if (c== MaoObra.class) {
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id," + "descricao," + "tempo_estimado_execucao,"+ "valor" +  "from tb_mao_obra" + "where id = ? ");
            ps.setInt(1, Integer.valueOf(id.toString()));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                MaoObra maoObra = new MaoObra();
                maoObra.setId(rs.getInt("id"));
                maoObra.setDescricao(rs.getString("descricao"));
               // maoObra.setTempo_estimado_execucao(rs.getInt("tempo_estimado_execucao"));
                maoObra.setValor(rs.getFloat("valor"));
            }
        } else if (c== Orcamento.class) {
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id," + "observacoes," + "data"+ "from tb_orcamento" + "where id = ? ");
            ps.setInt(1, Integer.valueOf(id.toString()));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Orcamento orcamento = new Orcamento();
                orcamento.setId(rs.getInt("id"));
                orcamento.setObservacoes(rs.getString("observacoes"));
                //orcamento.setData(rs.getDate("data"));

            }
        } else if (c == Pagamento.class) {
            PreparedStatement ps = this.con.prepareStatement("select " +
                    "id,"+"numero_parcelas," + "data_vencimento," + "data_pagamento"+ "from tb_pagamento" + "where id = ? ");
            ps.setInt(1, Integer.valueOf(id.toString()));
            ResultSet rs = ps.executeQuery();


        }
        return null;
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

        } else if (o instanceof Cargo) {
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
        } else if (o instanceof Cliente) {
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
        } else if (o instanceof Curso) {
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
                //ps.setDate(3,c.getDt_conclusao());
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
        } else if (o instanceof Equipe) {
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

        } else if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;
            //não tem como gerar um cpf por isso que usamos a data de admissão
            //utilizando a data_admissao
            if (f.getData_admissao() == null) {
                //insert into tb_funcionario
                PreparedStatement ps = this.con.prepareStatement("insert into tb_funcionario "
                        + "(id, "
                        + "nome, "
                        + "cpf, "
                        + "senha, "
                        + "numero_ctps, "
                        + "data_nascimento, "
                        + "data_admissao, "
                        + "data_demissao, "
                        + "cargo_id, "
                        + "cep, "
                        + "numero, "
                        + "complemento) "
                        + "values ("
                        + "nextval('seq_funcionario_id'), "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?);");
                ps.setString(1, f.getNome());
                ps.setString(2, f.getCpf());
                ps.setString(3, f.getSenha());
                ps.setString(4, f.getNumero_ctps());
                ps.setDate(5,  new java.sql.Date(f.getData_nascimento().getTimeInMillis()));
                ps.setDate(6,  new java.sql.Date(f.getData_admissao().getTimeInMillis()));
                ps.setDate(7,  new java.sql.Date(f.getData_demissao().getTimeInMillis()));
                ps.setInt(8, f.getCargo().getId());
                ps.setString(9, f.getCep());
                ps.setInt(10, parseInt(f.getNumero()));
                ps.setString(11, f.getComplemento());

                ps.execute();
                ps.close();
            } else {
                //update tb_funcionario
                PreparedStatement ps = this.con.prepareStatement("update tb_funcionario set "
                        + "nome = ?, "
                        + "cpf = ?, "
                        + "senha = ?, "
                        + "numero_ctps = ?, "
                        + "data_nascimento = ?, "
                        + "data_admissao = ?, "
                        + "data_demissao = ?, "
                        + "cargo_id = ?, "
                        + "cep = ?, "
                        + "numero = ?, "
                        + "complemento = ? "
                        + "where "
                        + "id = ? ");
                ps.setString(1, f.getNome());
                ps.setString(2, f.getCpf());
                ps.setString(3, f.getSenha());
                ps.setString(4, f.getNumero_ctps());
                //ps.setDate(5, f.getData_nascimento());
                //ps.setDate(6, f.getData_admissao());
                //ps.setDate(7, f.getData_demissao());
                ps.setInt(8, f.getCargo().getId());
                ps.setString(9, f.getCep());
                ps.setInt(10, parseInt(f.getNumero()));
                ps.setString(11, f.getComplemento());

                ps.execute();
                ps.close();

            }

        } else if (o instanceof MaoObra) {
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

            } else {
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
        } else if (o instanceof Orcamento) {
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
                //ps.setDate(1, o1.getData());
                ps.setString(2, o1.getObservacoes());
                ps.setString(3, o1.getCliente().getCpf());
                ps.setDouble(4, o1.getValorTotal());
                ps.setString(5, o1.getVeiculo().getPlaca());


                ps.execute();
                ps.close();

            }

        } else if (o instanceof Pagamento) {

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
                ps.setDate(2, new java.sql.Date(p.getData_vencimento().getTimeInMillis()));
                ///ps.set(3, p.getFormaPagamento());
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
        } else if (o instanceof Servico) {
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
        } else if (o instanceof Veiculo) {
            Veiculo v = (Veiculo) o;
            if (v.getPlaca() == null) {
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
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if(o instanceof  Peca){
            Peca p = (Peca) o; // conversaõ ou casting,modelagem de objeto para Peca.
            //testa par descobrir se existe informação na chave primária

                //delete from tb_peca
                PreparedStatement ps = this.con.prepareStatement("delete from tb_peca where id = ?;");

                ps.setInt(1, p.getId());

                ps.execute();//executa o insert

                ps.close();//fecha o cursor
        } else if ( o instanceof  Cargo) {
            Cargo c = (Cargo) o;

        } else if (o instanceof  Funcionario) {
            Funcionario f = (Funcionario) o;
            //não tem como gerar um cpf por isso que usamos a data de admissão
            //utilizando a data_admissao
            if (f.getData_admissao() != null){
                //delete from tb_funcionario
            }

        }

    }

    @Override
    public Funcionario doLogin(String cpf, String senha) throws Exception {
        return null;
    }

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
        while(rs.next()){

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
        return null;
    }

    @Override
    public List<Curso> listaDeCursos() throws Exception {
        return null;
    }

    @Override
    public List<Cargo> listaCargos() throws Exception {
        return null;
    }
}
