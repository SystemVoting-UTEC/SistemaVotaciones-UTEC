package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Departamento;
import utec.voting.system.entities.EstadoFamiliar;
import utec.voting.system.entities.Genero;
import utec.voting.system.entities.Persona;
import utec.voting.system.jdbc.Conexion;

public class PersonaImpl extends Conexion implements Service<Persona>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PersonaImpl.class);
	
	private GeneroImpl generoService =  new GeneroImpl();
	private DepartamentoImpl departamentoService =  new DepartamentoImpl();
	private EstadoFamiliarImpl estadoFamiliarService =  new EstadoFamiliarImpl();

	@Override
	public ArrayList<Persona> getAll() throws SQLException {
		Persona g;
		String PER_DUI;
		String PER_P_NOMBRE;
		String PER_S_NOMBRE;
		String PER_T_NOMBRE;
		String PER_P_APELLIDO;
		String PER_S_APELLIDO;
		Date PER_FECHA_NAC;
		int PER_EDAD;
		String PER_MADRE;
		String PER_PADRE;
		int ESTADO;
		
		Departamento depto = null;
		Genero genero =  null;
		EstadoFamiliar estadoF = null;
		ArrayList<Persona> l1 = new ArrayList<>();
		
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ALL_PERSONA()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					PER_DUI = getRs().getString(1);
					PER_P_NOMBRE = getRs().getString(2);
					PER_S_NOMBRE = getRs().getString(3);
					PER_T_NOMBRE = getRs().getString(4);
					PER_P_APELLIDO = getRs().getString(5);
					PER_S_APELLIDO = getRs().getString(6);
					PER_FECHA_NAC = getRs().getDate(7);
					PER_EDAD = getRs().getInt(8);
					genero = generoService.finById(getRs().getInt("PER_GEN_ID"));
					depto = departamentoService.finById(getRs().getInt("PER_DEP_ID"));
					estadoF =  estadoFamiliarService.finById(getRs().getInt("PER_EST_ID"));
					PER_MADRE = getRs().getString(12);
					PER_PADRE = getRs().getString(13);
					ESTADO = getRs().getInt(14);
					
					g = new Persona(PER_DUI, PER_P_NOMBRE, PER_S_NOMBRE, PER_T_NOMBRE, PER_P_APELLIDO
							, PER_S_APELLIDO, PER_FECHA_NAC, PER_EDAD, genero, depto, estadoF, 
							PER_MADRE, PER_PADRE, ESTADO);
					l1.add(g);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		} finally {
			stmt.close();
		}
		return l1;
	}

	@Override
	public Persona save(Persona t) throws SQLException {
		// TODO Auto-generated method stub
		//SP_CREATE_PERSONA
		return null;
	}

	@Override
	public Boolean update(Persona t) throws SQLException {
		// TODO Auto-generated method stub
		//SP_UPDATE_PERSONA
		return null;
	}

	@Override
	public Boolean delete(Persona t) throws SQLException {
		// TODO Auto-generated method stub
		//SP_DELETE_PERSONA
		return null;
	}

	@Override
	public Persona finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona finById(String id) throws SQLException {
		Persona g = null;
		Departamento depto = null;
		Genero genero =  null;
		EstadoFamiliar estadoF = null;
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ONE_PER(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					genero = generoService.finById(getRs().getInt("PER_GEN_ID"));
					depto = departamentoService.finById(getRs().getInt("PER_DEP_ID"));
					estadoF =  estadoFamiliarService.finById(getRs().getInt("PER_EST_ID"));
					g = new Persona(getRs().getString(1),getRs().getString(2),getRs().getString(3),
							getRs().getString(4),getRs().getString(5)
							,getRs().getString(6),getRs().getDate(7),
							getRs().getInt(8)
							,genero,depto, estadoF,getRs().getString(12),getRs().getString(13), getRs().getInt(14));
				}
			}
		} catch (Exception e) {
			System.out.println("Error en metodo finByIdPersona"+e);
		}finally {
			stmt.close();
		}
		return g;
	}
}
