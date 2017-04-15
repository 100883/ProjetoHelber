/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidade.EPessoa;
import java.sql.SQLException;
import java.util.List;
import persistencia.PPessoa;

/**
 *
 * @author Gui Freitas
 */
public class NPessoa {
    
    PPessoa persistencia;
    
    public NPessoa() {
        persistencia = new PPessoa();
    }
    
    public void salvar(EPessoa parametro) throws SQLException {
        if (parametro.getCodigo() == 0) {
            persistencia.incluir(parametro);
        }else{
            persistencia.alterar(parametro);
        }
    }
    
    public void excluir(int parametro) throws SQLException, Exception{
        persistencia.excluir(parametro);
    }
    
    public EPessoa consultar(int parametro) throws SQLException{
        return persistencia.consultar(parametro);
    }
    
    public List<EPessoa> listar(String nome) throws SQLException{
        return persistencia.listar(nome);
    }
    
}
