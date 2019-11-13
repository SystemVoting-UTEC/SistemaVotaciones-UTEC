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

import utec.voting.system.entities.Departamento;
import utec.voting.system.entities.Municipio;
import utec.voting.system.jdbc.Conexion;
import utec.voting.system.ws.DepartamentoService;

/**
 * @author Manuel Cardona
 *
 */
public class MunicipioImpl extends Conexion implements Service<Municipio>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final Logger logger = Logger.getLogger(MunicipioImpl.class);

	private DepartamentoImpl departamentoService = new DepartamentoImpl();

	@Override
	public ArrayList<Municipio> getAll() throws SQLException {
		CallableStatement stmt = null;
		Municipio g = new Municipio();
		Departamento departamento = new Departamento();
		ArrayList<Municipio> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_ALL_MUNICIPIO()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					departamento = new Departamento();
					
					departamento = DepartamentoService.finById(getRs().getInt(3));
					
					g = new Municipio();
					g.setMunId(getRs().getInt(1));
					g.setMunNombre(getRs().getString(2));
					g.setMunDepId(departamento);
					l1.add(g);
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
	public Municipio save(Municipio t) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_Municipio(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getMunNombre());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) > 0) {
				t.setMunId(stmt.getInt(2));
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();			
		}
		return t;
	}

	@Override
	public Boolean update(Municipio t) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_UPDATE_Municipio(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getMunNombre());
			stmt.setInt(2, t.getMunId());
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
	public Boolean delete(Municipio t) throws SQLException {
		// TODO Auto-generated method stub
		//SP_DELETE_Municipio
		return null;
	}

	@Override
	public Municipio finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement stmt = null;
		Municipio g =  null;
		try {
			String query = "{CALL SP_READ_ONE_Municipio(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					Municipio Municipio= new Municipio();
					((Service<utec.voting.system.entities.Municipio>) Municipio).finById(getRs().getInt(2));
					g = new Municipio();
					g.setMunId(getRs().getInt(1));
					
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
	public Municipio finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		//hace falta SP_READ_ONE_Municipio
		return null;
	}

}
