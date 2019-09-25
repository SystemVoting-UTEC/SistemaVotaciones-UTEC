package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Departamento;
import com.utec.voting.modelo.EstadoFamiliar;
import com.utec.voting.modelo.Genero;
import com.utec.voting.modelo.Persona;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class PersonaService extends Conexion implements Service<Persona>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PersonaService.class);
	
	private static final String TABLE = "PERSONA";
	
	private GeneroService generoService =  new GeneroService();
	private DepartamentoService departamentoService =  new DepartamentoService();
	private EstadoFamiliarService estadoFamiliarService =  new EstadoFamiliarService();
	
	@Override
	public ArrayList<Persona> getAll() throws SQLException {
		Persona g;
		Departamento depto = null;
		Genero genero =  null;
		EstadoFamiliar estadoF = null;
		ArrayList<Persona> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				genero = generoService.finById(getRs().getInt("PER_GEN_ID"));
				depto = departamentoService.finById(getRs().getInt("PER_DEP_ID"));
				estadoF =  estadoFamiliarService.finById(getRs().getInt("PER_EST_ID"));
				g = new Persona(getRs().getString(1),getRs().getString(2),getRs().getString(3),
						getRs().getString(4),getRs().getString(5)
						,getRs().getString(6),getRs().getDate(7),
						getRs().getInt(8)
						,genero,depto, estadoF,getRs().getString(12),getRs().getString(13));
				l1.add(g);
			}
		} catch (Exception e) {
			logger.error("Error <DepartamentoService: getAll>: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(Persona t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(PER_DUI, PER_P_NOMBRE, PER_S_NOMBRE, PER_T_NOMBRE, PER_P_APELLIDO, PER_S_APELLIDO, PER_FECHA_NAC, PER_EDAD, PER_GEN_ID, PER_DEP_ID, PER_EST_ID, PER_MADRE, PER_PADRE)\r\n" + 
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
			getPs().setString(1, t.getPerDui());
			getPs().setString(2, t.getPerPNombre());
			getPs().setString(3, t.getPerSNombre());
			getPs().setString(4, t.getPerTNombre());
			getPs().setString(5, t.getPerPApellido());
			getPs().setString(6, t.getPerSApellido());
			getPs().setDate(7, t.getPerFechaNac());
			getPs().setInt(8, t.getPerEdad());
			getPs().setInt(9, t.getPerGenId().getGenId());
			getPs().setInt(10, t.getPerDepId().getDepId());
			getPs().setInt(11, t.getPerEstId().getEstId());
			getPs().setString(12, t.getPerMadre());
			getPs().setString(13, t.getPerPadre());
			if (getPs().executeUpdate() == 1) {
				bandera = true;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
	}

	@Override
	public Boolean update(Persona t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "PER_P_NOMBRE=?, PER_S_NOMBRE=?, PER_T_NOMBRE=?, PER_P_APELLIDO=?, PER_S_APELLIDO=?, PER_FECHA_NAC=?, PER_EDAD=?, PER_GEN_ID=?, PER_DEP_ID=?, PER_EST_ID=?, PER_MADRE=?, PER_PADRE=?" + 
					WHERE + "PER_DUI= ?"));
			getPs().setString(1, t.getPerPNombre());
			getPs().setString(2, t.getPerSNombre());
			getPs().setString(3, t.getPerTNombre());
			getPs().setString(4, t.getPerPApellido());
			getPs().setString(5, t.getPerSApellido());
			getPs().setDate(6, t.getPerFechaNac());
			getPs().setInt(7, t.getPerEdad());
			getPs().setInt(8, t.getPerGenId().getGenId());
			getPs().setInt(9, t.getPerDepId().getDepId());
			getPs().setInt(10, t.getPerEstId().getEstId());
			getPs().setString(11, t.getPerMadre());
			getPs().setString(12, t.getPerPadre());
			getPs().setString(13, t.getPerDui());
			if (getPs().executeUpdate() == 1) {
				bandera = true;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
	}

	@Override
	public Boolean delete(Persona t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " PER_DUI = ?"));
			getPs().setString(1, t.getPerDui());
			if (getPs().executeUpdate() == 1) {
				bandera = true;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
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
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "PER_DUI = ?"));
			getPs().setString(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				genero = generoService.finById(getRs().getInt("PER_GEN_ID"));
				depto = departamentoService.finById(getRs().getInt("PER_DEP_ID"));
				estadoF =  estadoFamiliarService.finById(getRs().getInt("PER_EST_ID"));
				g = new Persona(getRs().getString(1),getRs().getString(2),getRs().getString(3),
						getRs().getString(4),getRs().getString(5)
						,getRs().getString(6),getRs().getDate(7),
						getRs().getInt(8)
						,genero,depto, estadoF,getRs().getString(12),getRs().getString(13));
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

}
