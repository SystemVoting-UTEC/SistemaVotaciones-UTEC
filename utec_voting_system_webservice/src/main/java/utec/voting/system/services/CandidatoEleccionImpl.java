package utec.voting.system.services;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Candidato;
import utec.voting.system.entities.CandidatoEleccion;
import utec.voting.system.entities.Eleccion;
import utec.voting.system.jdbc.Conexion;
/**
 * @author Manuel Cardona
 *
 */

public class CandidatoEleccionImpl extends Conexion implements Service<CandidatoEleccion>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(CandidatoEleccionImpl.class);
	
	private final EleccionImpl EleccionService = new EleccionImpl();
	private final CandidatoImpl CandidatoService = new CandidatoImpl();
	Eleccion Eleccion = new Eleccion();
	Candidato Candidato = new Candidato();

	@Override
	public ArrayList<CandidatoEleccion> getAll() throws SQLException {
		CandidatoEleccion g;
		final ArrayList<CandidatoEleccion> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			final String query = "{CALL SP_READ_ALL_CANDIDATO_ELECCION()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {

					Eleccion = new Eleccion();
					Eleccion = EleccionService.finById(getRs().getInt(2));
					
					Candidato = new Candidato();
					Candidato = CandidatoService.finById(getRs().getInt(3));

					g = new CandidatoEleccion();
					g.setCelId(getRs().getInt(1));
					g.setElcId(Eleccion);
					g.setCanId(Candidato);
					l1.add(g);
				}
			}
		} catch (final Exception e) {
			logger.error("Error: " + e);
		} finally {
			stmt.close();
		}
		return l1;

	}

	@Override
	public Boolean save(CandidatoEleccion t) throws SQLException {
		CallableStatement stmt = null;
		try {
			final String query = "{CALL SP_CREATE_CANDIDATO_ELECCION(?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getCelId());
			stmt.setInt(2, t.getElcId().getElcId());
			stmt.setInt(3, t.getCanId().getCanId());
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(4) > 0) {
				return Boolean.TRUE;
			}
		} catch (final Exception e) {
			logger.error("Error" + e);
		} finally {
			stmt.close();
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean update(CandidatoEleccion t) throws SQLException {
		CallableStatement stmt = null;
		try {
			final String query = "{CALL SP_UPDATE_CANDIDATO_ELECCION(?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getCelId());
			stmt.setInt(2, t.getElcId().getElcId());
			stmt.setInt(3, t.getCanId().getCanId());
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(4) > 0) {
				return Boolean.TRUE;
			}
		} catch (final Exception e) {
			logger.error("Error" + e);
		} finally {
			stmt.close();
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(CandidatoEleccion t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidatoEleccion finById(Integer id) throws SQLException {
		CallableStatement stmt = null;
		CandidatoEleccion g = new CandidatoEleccion();
		Eleccion Eleccion = new Eleccion();
		Candidato Candidato = new Candidato();
		try {
			String query = "{CALL SP_READ_ONE_CANDIDATO_ELECCION(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					Eleccion = new Eleccion();
					Candidato = new Candidato();
					
					Eleccion = EleccionService.finById(getRs().getInt(2));
					Candidato = CandidatoService.finById(getRs().getInt(3));
					g = new CandidatoEleccion();
					g.setCelId(getRs().getInt(1));
					g.setElcId(Eleccion);
					g.setCanId(Candidato);
					}
				}
			} 
		catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();
		}
		return g;
	}

	@Override
	public CandidatoEleccion finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
