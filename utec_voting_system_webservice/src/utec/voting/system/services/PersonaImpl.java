package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.Departamento;
import utec.voting.system.entities.EstadoFamiliar;
import utec.voting.system.entities.Genero;
import utec.voting.system.entities.Persona;

public class PersonaImpl extends Conexion implements Service<Persona>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GeneroImpl generoService =  new GeneroImpl();
	private DepartamentoImpl departamentoService =  new DepartamentoImpl();
	private EstadoFamiliarImpl estadoFamiliarService =  new EstadoFamiliarImpl();

	@Override
	public ArrayList<Persona> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean save(Persona t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Persona t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Persona t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona finById(String id) throws SQLException {
		System.out.println("Valor que envian: "+id);
		Persona g = null;
		Departamento depto = null;
		Genero genero =  null;
		EstadoFamiliar estadoF = null;
		try {
			String query = "{CALL SP_READ_ONE_PER(?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setString(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					genero = generoService.finById(getRs().getInt("PER_GEN_ID"));
					depto = departamentoService.finById(getRs().getInt("PER_DEP_ID"));
					estadoF =  estadoFamiliarService.finById(getRs().getInt("PER_EST_ID"));
					g = new Persona(getRs().getString(1),getRs().getString(2),getRs().getString(3),
							getRs().getString(4),getRs().getString(5)
							,getRs().getString(6),getRs().getDate(7),
							getRs().getInt(8)
							,genero,depto, estadoF,getRs().getString(12),getRs().getString(13));
				}
			}
		} catch (Exception e) {
			System.out.println("Error en metodo finByIdPersona"+e);
		}
		return g;
	}
}
