
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import main.db.DB;
import main.model.dao.DaoFactory;
import main.model.dao.DepartmentDao;
import main.model.entities.Department;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author evand
 */
public class Teste {

    private static String driver;
    private static String url;
    private static String usuario;
    private static String senha;

    /**
     *
     * @throws NutricaoException
     */
    private static void init() throws Exception {
        Properties properties = new Properties();
        FileInputStream arquivoPropriedade = null;

        try {
            arquivoPropriedade = new FileInputStream("dbConexao.properties");
            properties.load(arquivoPropriedade);

            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            usuario = properties.getProperty("jdbc.username");
            senha = properties.getProperty("jdbc.password");

            Class.forName(driver);

        } catch (IOException ex) { //Fim do try
            ex.printStackTrace();
            StringBuffer mensagem = new StringBuffer("Não foi possível conectar com o Banco de Dados!");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new Exception(mensagem.toString());

        } catch (ClassNotFoundException ex) {//Fim do catch
            ex.printStackTrace();
            StringBuffer mensagem = new StringBuffer("Não foi possível conectar com o Banco de Dados!");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new Exception(mensagem.toString());
        } //Fim do catch
    }

    /**
     *
     * @return Connection
     * @throws br.gov.ce.cidh.excecao.SisInfoCidhException
     */
    public static Connection getConexao() throws Exception {
        try {
            init();
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {//Fim try
            ex.printStackTrace();
            StringBuffer mensagem = new StringBuffer("Não foi possível estabelecer conexao com o banco de dados!");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new Exception(mensagem.toString());
        }//Fim do catch
    }

    /**
     *
     * @param conn
     * @param stmt
     * @param rs
     * @throws br.gov.ce.cidh.excecao.SisInfoCidhException
     */
    public static void closeConexao(Connection conn, PreparedStatement stmt, ResultSet rs) throws Exception {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) { //fim do try
            StringBuffer mensagem = new StringBuffer("Não foi possível finalizar a conexão! ");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new Exception(mensagem.toString());
        } //fim do catch
    }

    /**
     *
     * @param conn
     * @param stmt
     * @param rs
     * @throws br.gov.ce.cidh.excecao.SisInfoCidhException
     */
    public static void closeConexao(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {//Fim do try
            StringBuffer mensagem = new StringBuffer("Não foi possível finalizar a conexão!");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new Exception(mensagem.toString());
        }
    }

    public static void main(String args[]) {

        Connection conn;
        Statement stmt;
        ResultSet rs;

        String sql = "select * from department";

        try {
            conn = DB.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Namex: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
