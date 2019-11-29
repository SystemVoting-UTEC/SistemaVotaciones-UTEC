package utec.voting.system.services;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public interface Service<T> {

	/**
	 * @return ArrayList<T>
	 * @throws SQLException
	 * 
	 * Desciption: Metodo generico que retorna los registros de una tabla
	 * en forma de Lista
	 */
	ArrayList<T> getAll() throws SQLException;

	/**
	 * @param t
	 * @return Boolean True Or False
	 * @throws SQLException
	 * 
	 * Description: Metodo generico para guardar un nuevo registro en
	 * base de datos y retorna True en caso de éxito y False en caso contrario
	 */
	Boolean save(T t) throws SQLException;

	/**
	 * @param t
	 * @return Boolean True Or False
	 * @throws SQLException
	 * 
	 * Description: Metodo generico para actualizar un nuevo registro en
	 * base de datos y retorna True en caso de éxito y False en caso contrario
	 */
	Boolean update(T t) throws SQLException;

	/**
	 * @param t
	 * @return Boolean True Or False
	 * @throws SQLException
	 * 
	 * Description: Metodo generico para eliminar un nuevo registro en
	 * base de datos y retorna True en caso de éxito y False en caso contrario
	 */
	Boolean delete(T t) throws SQLException;

	/**
	 * @param Integer Id
	 * @return Object
	 * @throws SQLException
	 * 
	 * Description: metodo que recibe como parametro
	 * un ID de tipo numerico para realizar una busqueda 
	 * filtrando por PK
	 */
	T finById(Integer id) throws SQLException;

	/**
	 * @param String Id
	 * @return Object
	 * @throws SQLException
	 * 
	 * Description: metodo que recibe como parametro
	 * un ID de tipo cadena para realizar una busqueda 
	 * filtrando por PK
	 */
	T finById(String id) throws SQLException;
}
