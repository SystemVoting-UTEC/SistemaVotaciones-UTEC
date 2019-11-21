/**
 * 
 */
package com.utec.voting.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Kevin Orellana
 *
 */
public class Formateador {
	
	public static Date getDate(String formato, String fecha) {
		Date date =  null;
		SimpleDateFormat d = new SimpleDateFormat(formato);//"dd-MM-yy"
		try {
			date = (Date) d.parse(fecha);//"31-03-2016"
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
