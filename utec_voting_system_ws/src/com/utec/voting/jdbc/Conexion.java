package com.utec.voting.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
*
* @author Kevin Orellana
*/
public class Conexion {
    private String username = "kevin-UTEC";
    private String password = "12345";
    private String hostname = "104.197.59.203";
    private String puerto = "3306";
    private String base = "SYSTEM_VOTING";
    private String classname = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + base;
    private static Connection conecto;
    private ResultSet rs;
    private PreparedStatement ps;
    public static final String SELECT = "SELECT * FROM ";
    public static final String UPDATE = "UPDATE ";
    public static final String INSERT = "INSERT INTO ";
    public static final String SET = " SET ";
    public static final String WHERE = " WHERE ";
    public static final String AND = " AND ";
    public static final String OR = " OR ";
    public static final String DELETE = "DELETE FROM ";
    /**
     * Variable de logueo para errores.
     */
    
    public Conexion(){
        
    }
    
    public Connection getConnection() {
    	try {
            Class.forName(classname).newInstance();
            conecto = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
        }
        return this.conecto;
    }

	/**
	 * @return the ps
	 * @throws SQLException 
	 */
	public PreparedStatement consPrepare(String query) throws SQLException {
		this.setPs(this.getConnection().prepareStatement(query));
		return ps;
	}
	
	public PreparedStatement getPs() throws SQLException {
		return ps;
	}
	
	/**
	 * @param ps the ps to set
	 */
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	/**
	 * @return the rs
	 */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * @param rs the rs to set
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
