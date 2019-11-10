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
import utec.voting.system.entities.Municipio;
import utec.voting.system.entities.Partido;
import utec.voting.system.entities.Persona;
import utec.voting.system.entities.TipoCandidato;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Manuel Cardona
 *
 */
public class CandidatoImpl extends Conexion implements Service<Candidato>, Serializable{
	
private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(CandidatoImpl.class);
	
	private PersonaImpl personaService = new PersonaImpl();
	private PartidoImpl partidoService = new PartidoImpl();
	private DepartamentoImpl departamentoService = new DepartamentoImpl();
	private MunicipioImpl municipioService = new MunicipioImpl();
	private TipoCandidatoImpl tipoCandidatoService = new  TipoCandidatoImpl();
	
	@SuppressWarnings("null")
	@Override
	public ArrayList<Candidato> getAll() throws SQLException {
		CallableStatement stmt = null;
		Candidato g = new Candidato();
		Persona persona = new Persona();
		Partido partido = new Partido();
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		TipoCandidato tipocandidato = new TipoCandidato();
		ArrayList<Candidato> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_CANDIDATOS()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					persona = new Persona();
					partido = new Partido();
					departamento = new Departamento();
					municipio = new Municipio();
					tipocandidato = new TipoCandidato();
					
					persona = personaService.finById(getRs().getString(2));
					partido = partidoService.finById(getRs().getInt(3));
					departamento = departamentoService.finById(getRs().getInt(4));
					municipio = municipioService.finById(getRs().getInt(5));
					tipocandidato = tipoCandidatoService.finById(getRs().getInt(6));				
					
					g.setCanId(getRs().getInt(1));
					g.setCanPerDui(persona);
					g.setCanParId(partido);
					g.setCanDepId(departamento);
					g.setCanMunId(municipio);
					g.setCanTcaId(tipocandidato);
					l1.add(g);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}finally {
			stmt.close();
		}
		return l1;
	}

	@Override
	public Candidato save(Candidato t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_CANDIDATO(?,?,?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getCanPerDui().getPerDui());
			stmt.setInt(2, t.getCanParId().getParId());
			stmt.setInt(3, t.getCanDepId().getDepId());
			stmt.setInt(4, t.getCanMunId().getMunId());
			stmt.setInt(5, t.getCanTcaId().getTcaId());
			stmt.registerOutParameter(6, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) > 0) {
//				t.setTcaId(stmt.getInt(2));
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();
		}
		return t;
	}

	@Override
	public Boolean update(Candidato t) throws SQLException {
		CallableStatement stmt = null;
		try {
			// SP_UPDATE_CANDIDATO hace falta en los procedures
			String query = "{CALL SP_UPDATE_CANDIDATO(?,?,?,?,?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getCanId());
			stmt.setInt(2, t.getCanParId().getParId());
			stmt.setString(3, t.getCanPerDui().getPerDui());
			stmt.setInt(4, t.getCanParId().getParId());
			stmt.setInt(5, t.getCanDepId().getDepId());
			stmt.setInt(6, t.getCanMunId().getMunId());
			stmt.setInt(7, t.getCanTcaId().getTcaId());
			stmt.registerOutParameter(8, Types.INTEGER);
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
	public Boolean delete(Candidato t) throws SQLException {
		//SP_DELETE_CANDIDATO
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public Candidato finById(Integer id) throws SQLException {
		CallableStatement stmt = null;
		Candidato g = null;
		Persona persona = new Persona();
		Partido partido = new Partido();
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		TipoCandidato tipocandidato = new TipoCandidato();
		try {
			//SP_READ_ONE_CANDIDATO hace falta en los procedures
			String query = "{CALL SP_READ_ONE_CANDIDATO(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					persona = new Persona();
					partido = new Partido();
					departamento = new Departamento();
					municipio = new Municipio();
					tipocandidato = new TipoCandidato();
					
					persona = personaService.finById(getRs().getString(2));
					partido = partidoService.finById(getRs().getInt(3));
					departamento = departamentoService.finById(getRs().getInt(4));
					municipio = municipioService.finById(getRs().getInt(5));
					tipocandidato = tipoCandidatoService.finById(getRs().getInt(6));
					
					g.setCanId(getRs().getInt(1));
					g.setCanPerDui(persona);
					g.setCanParId(partido);
					g.setCanDepId(departamento);
					g.setCanMunId(municipio);
					g.setCanTcaId(tipocandidato);
				}
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();
		}
		return g;
	}

	@Override
	public Candidato finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
