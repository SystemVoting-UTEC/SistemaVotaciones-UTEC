package utec.voting.system.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
*
* @author Kevin Orellana
*/
public class Conexion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	protected static final Logger logger = Logger.getLogger(Conexion.class);
	
    private static Connection conecto ;
   
    private ResultSet rs;
    private PreparedStatement ps;
    /**
     * Variable de logueo para errores.
     */
	
    public Conexion(){
        
    }
    
    public Connection getConnection() {
    	try {
    		if(conecto == null) {
    			Context initCtx = new InitialContext();
    			Context envCtx = (Context) initCtx.lookup("java:comp/env");
    			DataSource ds = (DataSource)
    			envCtx.lookup("jdbc/system_voting");
    			conecto = ds.getConnection();
    		}
        } catch (Exception e) {
        	logger.error("Al intentar conectar"+e);
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
