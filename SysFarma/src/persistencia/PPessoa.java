/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.EPessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gui Freitas
 */
public class PPessoa {

    public PPessoa() {

    }

    public void incluir(EPessoa parametro) throws SQLException {

        String sql = "INSERT INTO pessoa (tipopessoa, "
                + "nome, documento, cep, numero, complemento, "
                + "fone, fax, email, cliente, fornecedor, laboratorio)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, parametro.getTipoPessoa());
        prd.setString(2, parametro.getNome());
        prd.setString(3, parametro.getDocumento());
        prd.setString(4, parametro.getCep());
        prd.setString(5, parametro.getNumero());
        prd.setString(6, parametro.getComplemento());
        prd.setString(7, parametro.getFone());
        prd.setString(8, parametro.getFax());
        prd.setString(9, parametro.getEmail());
        prd.setString(10, parametro.getCliente());
        prd.setString(11, parametro.getFornecedor());
        prd.setString(12, parametro.getLaboratorio());

        prd.execute();

        String sql2 = "SELECT currval ('pessoa_codigo_seq') as codigo";

        Statement st = cnn.createStatement();

        ResultSet rs = st.executeQuery(sql2);

        if (rs.next()) {
            parametro.setCodigo(rs.getInt("codigo"));
        }

        rs.close();
        cnn.close();
    }

    public void alterar(EPessoa parametro) throws SQLException {

        Connection cnn = util.Conexao.getConexao();

        String sql = "UPDATE pessoa "
                + "SET tipopessoa = ?, "
                + "nome = ?, "
                + "documento = ?, "
                + "cep = ?, "
                + "numero = ?, "
                + "complemento = ?, "
                + "fone = ?, "
                + "fax = ?, "
                + "email = ?, "
                + "cliente = ?, "
                + "fornecedor = ?, "
                + "laboratorio = ?, "
                + " WHERE codigo = ?";

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, parametro.getTipoPessoa());
        prd.setString(2, parametro.getNome());
        prd.setString(3, parametro.getDocumento());
        prd.setString(4, parametro.getCep());
        prd.setString(5, parametro.getNumero());
        prd.setString(6, parametro.getComplemento());
        prd.setString(7, parametro.getFone());
        prd.setString(8, parametro.getFax());
        prd.setString(9, parametro.getEmail());
        prd.setString(10, parametro.getCliente());
        prd.setString(11, parametro.getFornecedor());
        prd.setString(12, parametro.getLaboratorio());
        prd.setInt(13, parametro.getCodigo());

        prd.execute();
        cnn.close();
    }

    public void excluir(int paramentro) throws SQLException {

        Connection cnn = util.Conexao.getConexao();

        String sql = "DELETE FROM pessoa WHERE codigo = ?";

        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, paramentro);

        prd.execute();
        cnn.close();
    }

    public EPessoa consultar(int parametro) throws SQLException {

        Connection cnn = util.Conexao.getConexao();

        String slq = "SELECT codigo, tipopessoa, "
                + "nome, documento, cep, numero, complemento, "
                + "fone, fax, email, cliente, fornecedor, laboratorio "
                + "FROM pessoa WHERE codigo = ?";

        PreparedStatement prd = cnn.prepareStatement(slq);
        prd.setInt(1, parametro);

        ResultSet rs = prd.executeQuery();
        EPessoa retorno = new EPessoa();

        if (rs.next()) {
            retorno.setCodigo(rs.getInt("codigo"));
            retorno.setTipoPessoa("tipopessoa");
            retorno.setNome("nome");
            retorno.setDocumento("documento");
            retorno.setCep("cep");
            retorno.setNumero("numero");
            retorno.setComplemento("complemento");
            retorno.setFone("fone");
            retorno.setFax("fax");
            retorno.setEmail("email");
            retorno.setCliente("cliente");
            retorno.setFornecedor("fornecedor");
            retorno.setLaboratorio("laboratorio");
        }
        rs.close();
        cnn.close();

        return retorno;
    }

    public List<EPessoa> listar(String nome) throws SQLException {
        List<EPessoa> lista = new ArrayList<>();

        Connection cnn = util.Conexao.getConexao();

        String sql = "SELECT * "
                + "FROM pessoa "
                + "WHERE 1=1";

        if (nome != null) {
            if (!nome.isEmpty()) {
                sql += " AND nome LIKE ? ";
            }
        }
        sql = " ORDER BY nome";

        PreparedStatement prd = cnn.prepareStatement(sql);

        if (nome != null) {
            if (!nome.isEmpty()) {
                prd.setString(1, "%" + nome + "%");
            }
        }

        ResultSet rs = prd.executeQuery();

        while (rs.next()) {
            EPessoa pessoa = new EPessoa();
            pessoa.setCodigo(rs.getInt("codigo"));
            pessoa.setTipoPessoa(rs.getString("tipopessoa"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setDocumento(rs.getString("documento"));
            pessoa.setCep(rs.getString("cep"));
            pessoa.setNumero(rs.getString("numero"));
            pessoa.setComplemento(rs.getString("complemento"));
            pessoa.setFone(rs.getString("fone"));
            pessoa.setFax(rs.getString("fax"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCliente(rs.getString("cliente"));
            pessoa.setFornecedor(rs.getString("fornecedor"));
            pessoa.setLaboratorio(rs.getString("laboratorio"));
            lista.add(pessoa);
        }
        rs.close();
        cnn.close();

        return lista;
    }
}
