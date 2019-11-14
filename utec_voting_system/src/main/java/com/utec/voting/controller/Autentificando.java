package com.utec.voting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.Eleccion;
import com.utec.voting.modelo.OptionMenu;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.TipoUsuario;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;
import com.utec.voting.util.Encriptar;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Autentificando extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(Autentificando.class);
	
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	@SuppressWarnings({ "static-access", "unchecked" })
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Encriptar enc = new Encriptar();
		 Gson gson =  new GsonBuilder().setDateFormat(DateFormat.LONG).create();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		 Timestamp today = new Timestamp(new Date().getTime());
		try {
			HttpSession sesion;
			Persona pers = new Persona();
			Eleccion eleccion = null;
			TipoUsuario tpu = new TipoUsuario();
			List<OptionMenu> optList =  new ArrayList<>();
			response.setContentType("text/html;charset=UTF-8");
			pers.setPerDui(request.getParameter("usuario"));
			String pass = request.getParameter("pass");
			Usuario usr = new Usuario(); 
			usr.setUsPassword(enc.sha1(pass));
			usr.setUsPerDui(pers);
			usr.setUsTusId(tpu);
			JSONObject object = new JSONObject(usr);
			usr = gson.fromJson(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/login", object, "POST"), Usuario.class);
			if (usr != null) {
				object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/eleccion", "GET"));
				eleccion = new Eleccion();
				Timestamp timestampIni = new Timestamp(dateFormat.parse(object.get("elcFechaInicio").toString()).getTime());
				Timestamp timestampFin = new Timestamp(dateFormat.parse(object.get("elcFechaFin").toString()).getTime());
				eleccion.setElcFechaInicio(timestampIni);
				eleccion.setElcFechaFin(timestampFin);
				eleccion.setElcDescripcion(object.get("elcDescripcion").toString());
				eleccion.setElcId(Integer.parseInt(object.get("elcId").toString()));
				eleccion.setElcEstado(Integer.parseInt(object.get("elcEstado").toString()));
				Integer tipor = 1;
				if (usr.getUsTusId().getTusId() == tipor) {
					optList = gson.fromJson(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/option_menu/"+usr.getUsTusId().getTusId(), "GET"), ArrayList.class);
					sesion = request.getSession(true);
					sesion.setAttribute("usuario", usr);
					sesion.setAttribute("departamento", usr.getUsPerDui().getPerDepId());
					sesion.setAttribute("optList", optList);
					logger.error("Fecha de hoy: "+today.getTime()+" Ini: "+eleccion.getElcFechaInicio().getTime()+" Fin: "+eleccion.getElcFechaFin().getTime());
					
					if (today.after(eleccion.getElcFechaInicio()) && today.before(eleccion.getElcFechaFin()))
						sesion.setAttribute("eleccion", eleccion);
					response.sendRedirect("administracion.jsp");
				} else {
					sesion = request.getSession(true);
					sesion.setAttribute("usuario", usr);
					if (today.compareTo(eleccion.getElcFechaInicio()) == -1 && today.compareTo(eleccion.getElcFechaFin()) == 1 || (today.compareTo(eleccion.getElcFechaInicio())==0 || today.compareTo(eleccion.getElcFechaFin())==0))
						sesion.setAttribute("eleccion", eleccion);
					response.sendRedirect("votante.jsp");
				}
			} else {
				response.sendRedirect("graficoVotaciones.jsp");
			}
		} catch (Exception e) {
			logger.error("Error en el servlet Autentificando en el método processRequest: ", e);
			response.sendRedirect("graficoVotaciones.jsp");
		}
		
	}

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
