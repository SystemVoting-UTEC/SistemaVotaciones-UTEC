package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Genero;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class GeneroService extends Conexion implements Service<Genero>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TABLE = "GENERO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(GeneroService.class);

	@Override
	public ArrayList<Genero> getAll() throws SQLException {
		Genero g;
		ArrayList<Genero> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				g = new Genero(getRs().getInt(1), getRs().getString(2));
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
	public Boolean save(Genero g) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(GEN_GENERO) VALUES (?)"));
			getPs().setString(1, g.getGenGenero());
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
	public Boolean update(Genero g) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "GEN_GENERO = ? " + WHERE + " GEN_ID = ?"));
			getPs().setString(1, g.getGenGenero());
			getPs().setInt(2, g.getGenId());
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
	public Boolean delete(Genero g) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " GEN_ID = ?"));
			getPs().setInt(1, g.getGenId());
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
	public Genero finById(Integer id) throws SQLException {
		Genero g =  null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "GEN_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				g = new Genero(getRs().getInt(1), getRs().getString(2));
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public Genero finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
