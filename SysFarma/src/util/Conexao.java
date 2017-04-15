/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gui Freitas
 */
public class Conexao {

    private static Connection conexao;

    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = conectar();
            }
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
            erro.printStackTrace();
        }

        return conexao;
    }

    private static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/sysfarma", "postgres", "root");

        } catch (ClassNotFoundException e) {
            System.out.println("A aplicação não contém o driver para o banco.");
            return null;

        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco. Verifique a url, o usuário e a senha");
            return null;
        }
    }

}
