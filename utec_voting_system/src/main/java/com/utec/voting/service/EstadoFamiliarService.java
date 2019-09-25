package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.EstadoFamiliar;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class EstadoFamiliarService extends Conexion implements Service<EstadoFamiliar>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TABLE = "ESTADO_FAMILIAR";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoCandidatoService.class);

	@Override
	public ArrayList<EstadoFamiliar> getAll() throws SQLException {
		EstadoFamiliar g;
		ArrayList<EstadoFamiliar> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				g = new EstadoFamiliar(getRs().getInt(1), getRs().getString(2));
				l1.add(g);
			}
		} catch (Exception e) {
			logger.error("Error <EstadoFamiliarService: getAll>: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(EstadoFamiliar t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(EST_ESTADO) VALUES (?)"));
			getPs().setString(1, t.getEstEstado());
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
	public Boolean update(EstadoFamiliar t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "EST_ESTADO = ? " + WHERE + " EST_ID = ?"));
			getPs().setString(1, t.getEstEstado());
			getPs().setInt(2, t.getEstId());
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
	public Boolean delete(EstadoFamiliar t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " EST_ID = ?"));
			getPs().setInt(1, t.getEstId());
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
	public EstadoFamiliar finById(Integer id) throws SQLException {
		EstadoFamiliar g = null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "EST_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				g = new EstadoFamiliar(getRs().getInt(1), getRs().getString(2));
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public EstadoFamiliar finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
