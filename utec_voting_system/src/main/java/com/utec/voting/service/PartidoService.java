package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Partido;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class PartidoService extends Conexion implements Service<Partido>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TABLE = "ESTADO_FAMILIAR";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PartidoService.class);
	
	@Override
	public ArrayList<Partido> getAll() throws SQLException {
		Partido g;
		ArrayList<Partido> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				g = new Partido(getRs().getInt(1), getRs().getString(2));
				l1.add(g);
			}
		} catch (Exception e) {
			logger.error("Error <PartidoService: getAll>: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(Partido t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(PAR_NOMBRE) VALUES (?)"));
			getPs().setString(1, t.getParNombre());
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
	public Boolean update(Partido t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "PAR_NOMBRE = ? " + WHERE + " PAR_ID = ?"));
			getPs().setString(1, t.getParNombre());
			getPs().setInt(2, t.getParId());
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
	public Boolean delete(Partido t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " PAR_ID = ?"));
			getPs().setInt(1, t.getParId());
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
	public Partido finById(Integer id) throws SQLException {
		Partido g = null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "EST_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				g = new Partido(getRs().getInt(1), getRs().getString(2));
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public Partido finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
