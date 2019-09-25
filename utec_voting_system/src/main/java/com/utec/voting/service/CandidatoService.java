package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Candidato;
import com.utec.voting.modelo.Departamento;
import com.utec.voting.modelo.Partido;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.TipoCandidato;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class CandidatoService extends Conexion implements Service<Candidato>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String TABLE = "CANDIDATO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(CandidatoService.class);
	
	private PersonaService personaService = new PersonaService();
	private PartidoService partidoService =  new PartidoService();
	private DepartamentoService departamentoService =  new DepartamentoService();
	private TipoCandidatoService tipoCandidatoService = new TipoCandidatoService();
	
	@Override
	public ArrayList<Candidato> getAll() throws SQLException {
		Candidato g;
		Persona person;
		Departamento depto;
		TipoCandidato canType;
		Partido partido;
		ArrayList<Candidato> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				person =  personaService.finById(getRs().getString(2));
				partido = partidoService.finById(getRs().getInt(3));
				depto = departamentoService.finById(getRs().getInt(4));
				canType = tipoCandidatoService.finById(getRs().getInt(5));
				g = new Candidato(getRs().getInt(1),person, partido,depto,canType);
				l1.add(g);
			}
		} catch (Exception e) {
			logger.error("Error <CandidatoService: getAll>: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(Candidato t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(CAN_PER_DUI, CAN_PAR_ID, CAN_DEP_ID, CAN_TCA_ID) VALUES(?, ?, ?, ?)"));
			getPs().setString(1, t.getCanPerDui().getPerDui());
			getPs().setInt(2, t.getCanParId().getParId());
			getPs().setInt(3, t.getCanDepId().getDepId());
			getPs().setInt(4, t.getCanTcaId().getTcaId());
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
	public Boolean update(Candidato t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "CAN_PER_DUI=?, CAN_PAR_ID=?, CAN_DEP_ID=?, CAN_TCA_ID=? " + WHERE + " CAN_ID = ?"));
			getPs().setString(1, t.getCanPerDui().getPerDui());
			getPs().setInt(2, t.getCanParId().getParId());
			getPs().setInt(3, t.getCanDepId().getDepId());
			getPs().setInt(4, t.getCanTcaId().getTcaId());
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
	public Boolean delete(Candidato t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " CAN_ID = ?"));
			getPs().setInt(1, t.getCanId());
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
	public Candidato finById(Integer id) throws SQLException {
		Candidato g =  null;
		Persona person;
		Departamento depto;
		TipoCandidato canType;
		Partido partido;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "DEP_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				person =  personaService.finById(getRs().getString(2));
				partido = partidoService.finById(getRs().getInt(3));
				depto = departamentoService.finById(getRs().getInt(4));
				canType = tipoCandidatoService.finById(getRs().getInt(5));
				g = new Candidato(getRs().getInt(1),person, partido,depto,canType);
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public Candidato finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
