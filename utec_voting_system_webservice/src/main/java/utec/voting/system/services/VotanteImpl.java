package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
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
	
	private PersonaImpl personaService = new PersonaImpl();
	
	@SuppressWarnings("null")
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
					persona = new Persona();					
					persona = personaService.finById(getRs().getString(1));
					v.setVotPerDui(persona);
					v.setVotFechaExp(getRs().getDate(3));
					v.setVotFechaVence(getRs().getDate(2));
					v.setEstado(getRs().getInt(4));
					listaVotantes.add(v);					
				}
			}
		}catch (Exception e) {
			logger.error("Error es: " + e.getMessage());
		}finally {
			
			System.out.println(listaVotantes);
			stmt.close();
		}		
		return listaVotantes;
	}

	@Override
	public Votante save(Votante votante) throws SQLException {
		CallableStatement statement = null;
		try {
			String query = "{CALL SP_CREATE_VOTANTE(?,?,?,?)}";
			statement = getConnection().prepareCall(query);
			
			statement.setString(1, votante.getVotPerDui().getPerDui());
			statement.setDate(2, votante.getVotFechaVence());
			statement.setDate(3, votante.getVotFechaExp());
			statement.setInt(4, votante.getEstado());
			
			statement.execute();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}finally {
			statement.close();
		}
		return votante;
	}

	@Override
	public Boolean update(Votante votante) throws SQLException {
		CallableStatement statement =  null;
		
		try {
			String query = "{CALL SP_UPDATE_VOTANTE(?,?,?,?)}";
			statement = getConnection().prepareCall(query);
			
			statement.setString(1, votante.getVotPerDui().getPerDui());
			statement.setDate(2, votante.getVotFechaVence());
			statement.setDate(3, votante.getVotFechaExp());
			statement.setInt(4, votante.getEstado());
			
			statement.execute();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}finally {
			statement.close();
		}
		return true;
	}

	@Override
	public Boolean delete(Votante t) throws SQLException {
		// No se deberia eliminar a un votante
		return null;
	}

	@Override
	public Votante finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
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
					votante.setVotFechaVence(getRs().getDate(2));
					votante.setVotFechaExp(getRs().getDate(3));
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
