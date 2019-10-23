package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departamento save(Departamento t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departamento update(Departamento t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Departamento t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departamento finById(Integer id) throws SQLException {
		Departamento g =  null;
		try {
			String query = "{CALL SP_READ_ONE_DEP(?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
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
		}
		return g;
	}

	@Override
	public Departamento finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
