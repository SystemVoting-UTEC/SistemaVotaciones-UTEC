package com.utec.voting.service;

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
	 */
	ArrayList<T> getAll() throws SQLException;

	/**
	 * @param t
	 * @return Boolean True Or False
	 * @throws SQLException
	 */
	Boolean save(T t) throws SQLException;

	/**
	 * @param t
	 * @return Boolean True Or False
	 * @throws SQLException
	 */
	Boolean update(T t) throws SQLException;

	/**
	 * @param t
	 * @return Boolean True Or False
	 * @throws SQLException
	 */
	Boolean delete(T t) throws SQLException;

	/**
	 * @param Integer Id
	 * @return Object
	 * @throws SQLException
	 */
	T finById(Integer id) throws SQLException;

	/**
	 * @param String Id
	 * @return Object
	 * @throws SQLException
	 */
	T finById(String id) throws SQLException;
}
