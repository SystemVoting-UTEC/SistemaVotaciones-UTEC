package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Departamento;
import utec.voting.system.jdbc.Conexion;

public class DepartamentoImpl extends Conexion implements Service<Departamento>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(DepartamentoImpl.class);
	
	@Override
	public ArrayList<Departamento> getAll() throws SQLException {
		Departamento g;
		ArrayList<Departamento> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READALL_DEPTO()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Departamento(getRs().getInt(1), getRs().getString(2));
					l1.add(g);
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Error: " + e);
		}finally {
			stmt.close();
		}
		return l1;
	}
	
	@Override
	public Departamento save(Departamento t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_DEPTO(?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getDepNombre());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) > 0) {
				t.setDepId(stmt.getInt(2));
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();
		}
		return t;
	}

	@Override
	public Departamento update(Departamento t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_UPDATE_DEPTO(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getDepNombre());
			stmt.setInt(2, t.getDepId());
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(3) >= 1) {
				logger.error("Actualizadoooo.............");
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();
		}
		return t;
	}

	@Override
	public Boolean delete(Departamento t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departamento finById(Integer id) throws SQLException {
		Departamento g =  null;
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ONE_DEP(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Departamento(getRs().getInt(1), getRs().getString(2));
				}
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
		}finally {
			stmt.close();
		}
		return g;
	}

	@Override
	public Departamento finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
