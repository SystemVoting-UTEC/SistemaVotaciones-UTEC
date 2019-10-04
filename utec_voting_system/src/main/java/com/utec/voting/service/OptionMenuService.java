package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.OptionMenu;
import com.utec.voting.modelo.TipoUsuario;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class OptionMenuService extends Conexion implements Service<OptionMenu>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TABLE = "OPTION_MENU";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(OptionMenuService.class);
	
	@Override
	public ArrayList<OptionMenu> getAll() throws SQLException {
		OptionMenu g;
		ArrayList<OptionMenu> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new OptionMenu(getRs().getInt(1), getRs().getString(2), getRs().getString(3));
					l1.add(g);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}
	
	public ArrayList<OptionMenu> getAllByRol(TipoUsuario tipo) throws SQLException {
		OptionMenu g;
		ArrayList<OptionMenu> l1 = new ArrayList<>();
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "OPT_ID = ?"));
			getPs().setInt(1, tipo.getTusId());
			setRs(getPs().executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new OptionMenu(getRs().getInt(1), getRs().getString(2), getRs().getString(3));
					l1.add(g);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}
	
	
	@Override
	public Boolean save(OptionMenu t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(OptionMenu t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(OptionMenu t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionMenu finById(Integer id) throws SQLException {
		OptionMenu g =  null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "TUS_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new OptionMenu(getRs().getInt(1), getRs().getString(2),getRs().getString(3));
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
	public OptionMenu finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
