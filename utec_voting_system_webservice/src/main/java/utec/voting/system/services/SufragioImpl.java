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

import utec.voting.system.entities.Candidato;
import utec.voting.system.entities.Departamento;
import utec.voting.system.entities.Persona;
import utec.voting.system.entities.Sufragio;
import utec.voting.system.jdbc.Conexion;


/**
 * @author manuel cardona
 *
 */
public class SufragioImpl extends Conexion implements Service<Sufragio>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(SufragioImpl.class);
	
	private CandidatoImpl CandidatoService =  new CandidatoImpl();
	private PersonaImpl PersonaService =  new PersonaImpl();
	
	@Override
	public ArrayList<Sufragio> getAll() throws SQLException {
		
		return null;
		
	}
	
	@Override
	public Sufragio save(Sufragio t) throws SQLException {
		
		return null;
	}

	@Override
	public Boolean update(Sufragio t) throws SQLException {
		
		return null;
	}

	@Override
	public Boolean delete(Sufragio t) throws SQLException {
		// TODO Auto-generated method stub
		// hace falta SP_DELETE_Sufragio
		return null;
	}

	@Override
	public Sufragio finById(Integer id) throws SQLException {
		
		return null;
	}

	@Override
	public Sufragio finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
