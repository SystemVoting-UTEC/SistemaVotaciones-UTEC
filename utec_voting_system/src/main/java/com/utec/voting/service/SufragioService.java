package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Candidato;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.Sufragio;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class SufragioService extends Conexion implements Service<Sufragio>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TABLE = "SUFRAGIO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(SufragioService.class);
	
	private PersonaService personaService = new PersonaService();
	private CandidatoService candidatoService = new CandidatoService();
	
	@Override
	public ArrayList<Sufragio> getAll() throws SQLException {
		Sufragio g;
		Persona persona;
		Candidato candidato;
		ArrayList<Sufragio> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				persona =  personaService.finById(getRs().getString(2));
				candidato =  candidatoService.finById(getRs().getInt(3));
				g = new Sufragio(getRs().getInt(1), persona, candidato, getRs().getDouble(4));
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
	public Boolean save(Sufragio t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Sufragio t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Sufragio t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sufragio finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sufragio finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
