/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.OptionTipoUsuario;
import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.entities.OptionMenu;
import utec.voting.system.jdbc.Conexion;


/**
 * @author manuel24-1992
 *
 */
public class OptionTipoUsuarioImpl extends Conexion implements Service<OptionTipoUsuario>, Serializable{
	
	private TipoUsuarioImpl TipoUsuarioService = new TipoUsuarioImpl();
	private OptionMenuImpl OptionMenuService = new OptionMenuImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoUsuarioImpl.class);

	@Override
	public ArrayList<OptionTipoUsuario> getAll() throws SQLException {
		TipoUsuario TipoUsuario = new TipoUsuario();
		OptionMenu OptionMenu = new OptionMenu();
		OptionTipoUsuario g= new OptionTipoUsuario();
		ArrayList<OptionTipoUsuario> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_ALL_OPTION_TIPO_USUARIO()}";
			CallableStatement stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					TipoUsuario = new TipoUsuario();
					OptionMenu = new OptionMenu();
					OptionMenu = OptionMenuService.finOneById(getRs().getInt(1));
					TipoUsuario = TipoUsuarioService.finById(getRs().getInt(2));
					g = new OptionTipoUsuario();
					g.setOptId(OptionMenu);
					g.setTusId(TipoUsuario);
					l1.add(g);
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return l1;
	}

	@Override
	public Boolean save(OptionTipoUsuario t) throws SQLException {
		CallableStatement stmt=null;
		try {
			//falta sp
			String query = "{CALL SP_CREATE_OPTION_TIPO_USUARIO(?,?,?)}";
			 stmt = getConnection().prepareCall(query);
			 stmt.setInt(1, t.getOptId().getOptId());
			 stmt.setInt(2, t.getTusId().getTusId());
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(3) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();			
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean update(OptionTipoUsuario t) throws SQLException {
		try {
			String query = "{CALL SP_UPDATE_OPTION_TIPO_USUARIO(?,?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getOptId().getOptId());
			 stmt.setInt(2, t.getTusId().getTusId());
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(3) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(OptionTipoUsuario t) throws SQLException {
		try {
			String query = "{CALL SP_DELETE_OPTMENU(?,?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getOptId().getOptId());
			 stmt.setInt(2, t.getTusId().getTusId());
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(3) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	public OptionTipoUsuario finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionTipoUsuario finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
