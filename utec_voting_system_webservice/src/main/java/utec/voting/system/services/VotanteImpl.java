package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Persona;
import utec.voting.system.entities.Votante;
import utec.voting.system.jdbc.Conexion;

/**
 * 
 * @author fxnavas
 *
 */

public class VotanteImpl extends Conexion implements Service<Votante>, Serializable {

	private static final long serialVersionUID = 1L;
	
	static final Logger logger = Logger.getLogger(VotanteImpl.class);
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); 
	java.sql.Date sqlStartDate = null;
	private PersonaImpl personaService = new PersonaImpl();
	
	@Override
	public ArrayList<Votante> getAll() throws SQLException {
		Votante v = new Votante();
		Persona persona = new Persona();
		ArrayList<Votante> listaVotantes = new ArrayList<Votante>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_VOTANTES()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					v = new Votante();
					persona = new Persona();					
					persona = personaService.finById(getRs().getString(1));
					v.setVotPerDui(persona);
					v.setVotFechaExp(getRs().getDate(3).toString());
					v.setVotFechaVence(getRs().getDate(2).toString());
					v.setEstado(getRs().getInt(4));
					listaVotantes.add(v);					
				}
			}
		}catch (Exception e) {
			logger.error("Error es: " + e.getMessage());
		}finally {
			stmt.close();
		}		
		return listaVotantes;
	}

	@Override
	public Boolean save(Votante votante) throws SQLException {
		CallableStatement statement = null;
		try {
			String query = "{CALL SP_CREATE_VOTANTE(?,?,?,?,?)}";
			statement = getConnection().prepareCall(query);
			statement.setString(1, votante.getVotPerDui().getPerDui());
			sqlStartDate = new java.sql.Date(sdf1.parse(votante.getVotFechaVence()).getTime());
			statement.setDate(2, sqlStartDate);
			sqlStartDate = new java.sql.Date(sdf1.parse(votante.getVotFechaExp()).getTime());
			statement.setDate(3, sqlStartDate);
			statement.setInt(4, votante.getEstado());
			statement.registerOutParameter(5, Types.INTEGER);
			statement.execute();
			if (statement.getInt(5) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean update(Votante votante) throws SQLException {
		CallableStatement statement =  null;
		
		try {
			String query = "{CALL SP_UPDATE_VOTANTE(?,?,?,?,?)}";
			statement = getConnection().prepareCall(query);
			statement.setString(1, votante.getVotPerDui().getPerDui());
			sqlStartDate = new java.sql.Date(sdf1.parse(votante.getVotFechaVence()).getTime());
			statement.setDate(2, sqlStartDate);
			sqlStartDate = new java.sql.Date(sdf1.parse(votante.getVotFechaExp()).getTime());
			statement.setDate(3, sqlStartDate);
			statement.setInt(4, votante.getEstado());
			statement.registerOutParameter(5, Types.INTEGER);
			statement.execute();
			if (statement.getInt(5) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(Votante t) throws SQLException {
		// No se deberia eliminar a un votante
		return null;
	}

	@Override
	public Votante finById(Integer id) throws SQLException {
		 
		return null;
	}

	@Override
	public Votante finById(String dui) throws SQLException {
		CallableStatement statement = null;
		Persona persona = new Persona();
		Votante votante = new Votante();
		
		try {
			String query = "{CALL SP_READ_ONE_VOTANTE(?)}";
			statement = getConnection().prepareCall(query);
			statement.setString(1, dui);
			setRs(statement.executeQuery());
			
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					persona = new Persona();
					
					persona = personaService.finById(getRs().getString(1));
					votante = new Votante();
					
					votante.setVotPerDui(persona);
					votante.setVotFechaVence(getRs().getDate(2).toString());
					votante.setVotFechaExp(getRs().getDate(3).toString());
					votante.setEstado(getRs().getInt(4));					
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}finally {
			statement.close();
		}							
		return votante;
	}

}
