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
		Sufragio g;
		Candidato candidato = null;
		Persona persona = null;
		ArrayList<Sufragio> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_SufragioS()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					persona = PersonaService.finById(getRs().getInt("SUF_PER_DUI"));
					candidato = CandidatoService.finById(getRs().getInt("SUF_CAN_ID"));
					//g = new Sufragio(getRs().getInt(1),persona,candidato, getRs().getString(2),getRs().getInt(3),getRs().getdouble(4));
					//l1.add(g);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		}finally {
			stmt.close();			
		}
		return l1;
	}
	
	@Override
	public Sufragio save(Sufragio t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_Sufragio(?,?)}";
			stmt = getConnection().prepareCall(query);
			/*stmt.setString(1, t.getGenSufragio());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) > 0) {
				t.setGenId(stmt.getInt(2));
			}*/
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();			
		}
		return t;
	}

	@Override
	public Boolean update(Sufragio t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_UPDATE_Sufragio(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			/*stmt.setString(1, t.getGenSufragio());
			stmt.setInt(2, t.getGenId());*/
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
	public Boolean delete(Sufragio t) throws SQLException {
		// TODO Auto-generated method stub
		// hace falta SP_DELETE_Sufragio
		return null;
	}

	@Override
	public Sufragio finById(Integer id) throws SQLException {
		CallableStatement stmt = null;
		Sufragio g =  null;
		try {
			String query = "{CALL SP_READ_ONE_GEN(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					//g = new Sufragio(getRs().getInt(1), getRs().getString(2));
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
	public Sufragio finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
