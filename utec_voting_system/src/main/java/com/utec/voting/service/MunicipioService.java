package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Departamento;
import com.utec.voting.modelo.Municipio;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class MunicipioService extends Conexion implements Service<Municipio>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String TABLE = "MUNICIPIO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(MunicipioService.class);
	
	
	private DepartamentoService departamentoService = new DepartamentoService();
	
	@Override
	public ArrayList<Municipio> getAll() throws SQLException {
		Municipio g;
		Departamento depto;
		ArrayList<Municipio> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				depto =  departamentoService.finById(getRs().getInt(3));
				g = new Municipio(getRs().getInt(1), getRs().getString(2),depto);
				l1.add(g);
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(Municipio t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(MUN_NOMBRE, MUN_DEP_ID) VALUES(?, ?)"));
			getPs().setString(1, t.getMunNombre());
			getPs().setInt(2, t.getMunDepId().getDepId());
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
	public Boolean update(Municipio t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "SET MUN_NOMBRE=?, MUN_DEP_ID=? "+WHERE+" MUN_ID=?"));
			getPs().setString(1, t.getMunNombre());
			getPs().setInt(2, t.getMunDepId().getDepId());
			getPs().setInt(3, t.getMunId());
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
	public Boolean delete(Municipio t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " MUN_ID = ?"));
			getPs().setInt(1, t.getMunId());
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
	public Municipio finById(Integer id) throws SQLException {
		Municipio g =  null;
		Departamento depto;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "MUN_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				depto =  departamentoService.finById(getRs().getInt(3));
				g = new Municipio(getRs().getInt(1), getRs().getString(2),depto);
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public Municipio finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
