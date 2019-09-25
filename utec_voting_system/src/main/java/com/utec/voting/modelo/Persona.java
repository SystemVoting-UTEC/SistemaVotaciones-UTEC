package com.utec.voting.modelo;

import java.sql.Date;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Persona {
	private String perDui;
	private String perPNombre;
	private String perSNombre;
	private String perTNombre;
	private String perPApellido;
	private String perSApellido;
	private Date perFechaNac;
	private Integer perEdad;
	private Genero perGenId;
	private Departamento perDepId;
	private EstadoFamiliar perEstId;
	private String perMadre;
	private String perPadre;
	
	public Persona() {
		
	}
	
	/**
	 * @param perDui
	 * @param perPNombre
	 * @param perSNombre
	 * @param perTNombre
	 * @param perPApellido
	 * @param perSApellido
	 * @param perFechaNac
	 * @param perEdad
	 * @param perGenId
	 * @param perDepId
	 * @param perEstId
	 * @param perMadre
	 * @param perPadre
	 */
	public Persona(String perDui, String perPNombre, String perSNombre, String perTNombre, String perPApellido, String perSApellido, Date perFechaNac, Integer perEdad, Genero perGenId, Departamento perDepId, EstadoFamiliar perEstId, String perMadre, String perPadre) {
		super();
		this.perDui = perDui;
		this.perPNombre = perPNombre;
		this.perSNombre = perSNombre;
		this.perTNombre = perTNombre;
		this.perPApellido = perPApellido;
		this.perSApellido = perSApellido;
		this.perFechaNac = perFechaNac;
		this.perEdad = perEdad;
		this.perGenId = perGenId;
		this.perDepId = perDepId;
		this.perEstId = perEstId;
		this.perMadre = perMadre;
		this.perPadre = perPadre;
	}

	/**
	 * @return the perDui
	 */
	public String getPerDui() {
		return perDui;
	}

	/**
	 * @param perDui the perDui to set
	 */
	public void setPerDui(String perDui) {
		this.perDui = perDui;
	}

	/**
	 * @return the perPNombre
	 */
	public String getPerPNombre() {
		return perPNombre;
	}

	/**
	 * @param perPNombre the perPNombre to set
	 */
	public void setPerPNombre(String perPNombre) {
		this.perPNombre = perPNombre;
	}

	/**
	 * @return the perSNombre
	 */
	public String getPerSNombre() {
		return perSNombre;
	}

	/**
	 * @param perSNombre the perSNombre to set
	 */
	public void setPerSNombre(String perSNombre) {
		this.perSNombre = perSNombre;
	}

	/**
	 * @return the perTNombre
	 */
	public String getPerTNombre() {
		return perTNombre;
	}

	/**
	 * @param perTNombre the perTNombre to set
	 */
	public void setPerTNombre(String perTNombre) {
		this.perTNombre = perTNombre;
	}

	/**
	 * @return the perPApellido
	 */
	public String getPerPApellido() {
		return perPApellido;
	}

	/**
	 * @param perPApellido the perPApellido to set
	 */
	public void setPerPApellido(String perPApellido) {
		this.perPApellido = perPApellido;
	}

	/**
	 * @return the perSApellido
	 */
	public String getPerSApellido() {
		return perSApellido;
	}

	/**
	 * @param perSApellido the perSApellido to set
	 */
	public void setPerSApellido(String perSApellido) {
		this.perSApellido = perSApellido;
	}

	/**
	 * @return the perFechaNac
	 */
	public Date getPerFechaNac() {
		return perFechaNac;
	}

	/**
	 * @param perFechaNac the perFechaNac to set
	 */
	public void setPerFechaNac(Date perFechaNac) {
		this.perFechaNac = perFechaNac;
	}

	/**
	 * @return the perEdad
	 */
	public Integer getPerEdad() {
		return perEdad;
	}

	/**
	 * @param perEdad the perEdad to set
	 */
	public void setPerEdad(Integer perEdad) {
		this.perEdad = perEdad;
	}

	/**
	 * @return the perGenId
	 */
	public Genero getPerGenId() {
		return perGenId;
	}

	/**
	 * @param perGenId the perGenId to set
	 */
	public void setPerGenId(Genero perGenId) {
		this.perGenId = perGenId;
	}

	/**
	 * @return the perDepId
	 */
	public Departamento getPerDepId() {
		return perDepId;
	}

	/**
	 * @param perDepId the perDepId to set
	 */
	public void setPerDepId(Departamento perDepId) {
		this.perDepId = perDepId;
	}

	/**
	 * @return the perEstId
	 */
	public EstadoFamiliar getPerEstId() {
		return perEstId;
	}

	/**
	 * @param perEstId the perEstId to set
	 */
	public void setPerEstId(EstadoFamiliar perEstId) {
		this.perEstId = perEstId;
	}

	/**
	 * @return the perMadre
	 */
	public String getPerMadre() {
		return perMadre;
	}

	/**
	 * @param perMadre the perMadre to set
	 */
	public void setPerMadre(String perMadre) {
		this.perMadre = perMadre;
	}

	/**
	 * @return the perPadre
	 */
	public String getPerPadre() {
		return perPadre;
	}

	/**
	 * @param perPadre the perPadre to set
	 */
	public void setPerPadre(String perPadre) {
		this.perPadre = perPadre;
	}

	@Override
	public String toString() {
		return "Persona [perDui=" + perDui + ", perPNombre=" + perPNombre + ", perSNombre=" + perSNombre + ", perTNombre=" + perTNombre + ", perPApellido=" + perPApellido + ", perSApellido=" + perSApellido + ", perFechaNac=" + perFechaNac + ", perEdad=" + perEdad + ", perGenId=" + perGenId + ", perDepId=" + perDepId + ", perEstId=" + perEstId + ", perMadre=" + perMadre + ", perPadre=" + perPadre + "]";
	}
}
