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
		CallableStatement stmt = null;
		Votante v = new Votante();
		Persona persona = new Persona();
		
		ArrayList<Votante> listaVotantes = new ArrayList<Votante>();
		try {
			String query = "{CALL SP_READ_VOTANTES()}";
			stmt.getConnection().prepareCall(query);
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
					System.out.println(listaVotantes);
					
				}
			}
		}catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}finally {
			stmt.close();
		}
		return listaVotantes;
	}

	@Override
	public Votante save(Votante t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Votante t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Votante t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Votante finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Votante finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
