package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Departamento;

public class DepartamentoService extends Conexion implements Service<Departamento>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TABLE = "DEPARTAMENTO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(DepartamentoService.class);

	@Override
	public ArrayList<Departamento> getAll() throws SQLException {
		Departamento g;
		ArrayList<Departamento> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Departamento(getRs().getInt(1), getRs().getString(2));
					l1.add(g);
				}
			}
		} catch (Exception e) {
			logger.error("Error <DepartamentoService: getAll>: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(Departamento t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(DEP_NOMBRE) VALUES (?)"));
			getPs().setString(1, t.getDepNombre());
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
	public Boolean update(Departamento t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "DEP_NOMBRE = ? " + WHERE + " DEP_ID = ?"));
			getPs().setString(1, t.getDepNombre());
			getPs().setInt(2, t.getDepId());
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
	public Boolean delete(Departamento t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " DEP_ID = ?"));
			getPs().setInt(1, t.getDepId());
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
	public Departamento finById(Integer id) throws SQLException {
		Departamento g =  null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "DEP_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Departamento(getRs().getInt(1), getRs().getString(2));
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public Departamento finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
