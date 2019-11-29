package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); 
	java.sql.Date sqlStartDate = null;
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
		String PER_FECHA_NAC;
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
					PER_FECHA_NAC = getRs().getDate(7).toString();
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
	public Boolean save(Persona persona) throws SQLException {
		CallableStatement statement  = null;
		
		try {
			String query = "{CALL SP_CREATE_PERSONA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			statement = getConnection().prepareCall(query);
			
			statement.setString(1, persona.getPerDui());
			statement.setString(2, persona.getPerPNombre());
			statement.setString(3, persona.getPerSNombre());
			statement.setString(4, persona.getPerTNombre().equals("") ? null : persona.getPerTNombre());
			statement.setString(5, persona.getPerPApellido());
			statement.setString(6, persona.getPerSApellido());
			sqlStartDate = new java.sql.Date(sdf1.parse(persona.getPerFechaNac()).getTime());
			statement.setDate(7, sqlStartDate);
			statement.setInt(9,persona.getPerGenId().getGenId());
			statement.setInt(10,persona.getPerDepId().getDepId());
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaNac = LocalDate.parse(persona.getPerFechaNac(), fmt);
			LocalDate ahora = LocalDate.now();
			Period periodo = Period.between(fechaNac, ahora);
			statement.setInt(8, periodo.getYears());
			statement.setInt(11,persona.getPerEstId().getEstId());
			statement.setString(12, persona.getPerMadre());
			statement.setString(13, persona.getPerPadre());
			statement.setInt(14,persona.getPerEstado());
			statement.registerOutParameter(15, Types.INTEGER);
			statement.execute();
			if (statement.getInt(15) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			statement.close();			
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean update(Persona persona) throws SQLException {
		CallableStatement statement = null;
		
		try {
			String query = "{CALL SP_UPDATE_PERSONA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			statement = getConnection().prepareCall(query);
			
			statement.setString(1, persona.getPerDui());
			statement.setString(2, persona.getPerPNombre());
			statement.setString(3, persona.getPerSNombre());
			statement.setString(4, persona.getPerTNombre().equals("") ? null : persona.getPerTNombre());
			statement.setString(5, persona.getPerPApellido());
			statement.setString(6, persona.getPerSApellido());
			sqlStartDate = new java.sql.Date(sdf1.parse(persona.getPerFechaNac()).getTime());
			statement.setDate(7, sqlStartDate);
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaNac = LocalDate.parse(persona.getPerFechaNac(), fmt);
			LocalDate ahora = LocalDate.now();
			Period periodo = Period.between(fechaNac, ahora);
			statement.setInt(8, periodo.getYears());
			statement.setInt(9, persona.getPerGenId().getGenId());
			statement.setInt(10, persona.getPerDepId().getDepId());
			statement.setInt(11, persona.getPerEstId().getEstId());
			statement.setString(12, persona.getPerMadre());
			statement.setString(13, persona.getPerPadre());
			statement.setInt(14, persona.getPerEstado());
			statement.registerOutParameter(15, Types.INTEGER);
			statement.execute();
			if (statement.getInt(15) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			statement.close();			
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(Persona t) throws SQLException {
		 
		//SP_DELETE_PERSONA
		return null;
	}

	@Override
	public Persona finById(Integer id) throws SQLException {
		 
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
							,getRs().getString(6),getRs().getString(7),
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
