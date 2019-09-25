package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.Votante;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class VotanteService extends Conexion implements Service<Votante>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String TABLE = "VOTANTE";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(VotanteService.class);
	
	private PersonaService personaService = new PersonaService();
	
	@Override
	public ArrayList<Votante> getAll() throws SQLException {
		Votante g;
		Persona person;
		ArrayList<Votante> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				person =  personaService.finById(getRs().getString(1));
				g = new Votante(person, getRs().getDate(2),getRs().getDate(3));
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
	public Boolean save(Votante t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(VOT_PER_DUI, VOT_FECHA_VENCE, VOT_FECHA_EXP) VALUES (?, ?, ?)"));
			getPs().setString(1, t.getVotPerDui().getPerDui());
			getPs().setDate(2, t.getVotFechaVence());
			getPs().setDate(3, t.getVotFechaExp());
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
	public Boolean update(Votante t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "VOT_FECHA_VENCE=?, VOT_FECHA_EXP=? " + WHERE + " VOT_PER_DUI = ?"));
			getPs().setDate(1, t.getVotFechaVence());
			getPs().setDate(2, t.getVotFechaExp());
			getPs().setString(3, t.getVotPerDui().getPerDui());
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
	public Boolean delete(Votante t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " VOT_PER_DUI = ?"));
			getPs().setString(1, t.getVotPerDui().getPerDui());
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
	public Votante finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Votante finById(String id) throws SQLException {
		Votante g =  null;
		Persona person;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "VOT_PER_DUI = ?"));
			getPs().setString(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				person =  personaService.finById(getRs().getString(1));
				g = new Votante(person, getRs().getDate(2),getRs().getDate(3));
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

}
