package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.TipoCandidato;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class TipoCandidatoService extends Conexion implements Service<TipoCandidato>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TABLE = "TIPO_CANDIDATO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoCandidatoService.class);

	@Override
	public ArrayList<TipoCandidato> getAll() throws SQLException {
		TipoCandidato g;
		ArrayList<TipoCandidato> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				g = new TipoCandidato(getRs().getInt(1), getRs().getString(2));
				l1.add(g);
			}
		} catch (Exception e) {
			logger.error("Error <TipoCandidatoService: getAll>: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(TipoCandidato t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(TCA_TIPO) VALUES (?)"));
			getPs().setString(1, t.getTcaTipo());
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
	public Boolean update(TipoCandidato t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "TCA_TIPO = ? " + WHERE + " TCA_ID = ?"));
			getPs().setString(1, t.getTcaTipo());
			getPs().setInt(2, t.getTcaId());
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
	public Boolean delete(TipoCandidato t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " TCA_ID = ?"));
			getPs().setInt(1, t.getTcaId());
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
	public TipoCandidato finById(Integer id) throws SQLException {
		TipoCandidato g =  null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "TCA_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				g = new TipoCandidato(getRs().getInt(1), getRs().getString(2));
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public TipoCandidato finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
