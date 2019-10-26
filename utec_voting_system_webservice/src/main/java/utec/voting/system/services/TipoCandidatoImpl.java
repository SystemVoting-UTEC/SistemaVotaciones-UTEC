/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Genero;
import utec.voting.system.entities.TipoCandidato;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Manuel Cardona
 *
 */
public class TipoCandidatoImpl extends Conexion implements Service<TipoCandidato>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoCandidatoImpl.class);

	@Override
	public ArrayList<TipoCandidato> getAll() throws SQLException {
		TipoCandidato g;
		ArrayList<TipoCandidato> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_TIPOCANDIDATOS()}";
			CallableStatement stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new TipoCandidato(getRs().getInt(1), getRs().getString(2));
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
	public TipoCandidato save(TipoCandidato t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoCandidato update(TipoCandidato t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(TipoCandidato t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoCandidato finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoCandidato finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
