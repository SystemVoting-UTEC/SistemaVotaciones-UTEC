package com.utec.voting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.Eleccion;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * Servlet implementation class EleccionController
 */
@WebServlet(name = "eleccion.do", description = "Servlet for eleccion table", urlPatterns = { "/eleccion.do" })
public class EleccionController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(EleccionController.class);
	
	private List<Eleccion> elecList = new ArrayList<Eleccion>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EleccionController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher res=null;
		Eleccion eleccionSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			//Validando si existe la variable de sesion principal
			if(us != null) {
				if(request.getParameter("btnModificar")!=null){
					 if(request.getParameter("elcId") != null && request.getParameter("elcFechaInicioEdi") != null  && request.getParameter("elcFechaFinEdi") != null) {
						 Eleccion eleccionEdit = new Eleccion();
						 JSONObject object = new JSONObject(eleccionEdit);
						object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/eleccion",object, "PUT"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
						 
					 }
				 }
				 
				 if(request.getParameter("btnInsertarEleccion")!=null){
					 if(request.getParameter("elcDescripcion") != null  && request.getParameter("elcFechaInicio") != null) {
						 Eleccion eleccionInsert = new Eleccion();
						 JSONObject object = new JSONObject(eleccionInsert);
						object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/eleccion",object, "POST"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
					 }
				 }
				
				if(request.getParameter("id") == null) {
					elecList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/eleccion/1", "GET"),Eleccion[].class);
					request.setAttribute("elecList", elecList);
					res=request.getRequestDispatcher("mtmEleccion.jsp");
					res.forward(request, response);
				} else {
					for (Eleccion elc : elecList) {
						if(elc.getElcId() == Integer.parseInt(request.getParameter("id"))) {
							eleccionSelected = new Eleccion();
							eleccionSelected.setElcId(elc.getElcId());
							eleccionSelected.setElcDescripcion(elc.getElcDescripcion());
							eleccionSelected.setElcFechaInicio(elc.getElcFechaInicio());
							eleccionSelected.setElcFechaFin(elc.getElcFechaFin());
							eleccionSelected.setElcEstado(elc.getElcEstado());
						}
					}
					String jsonArray = gson.toJson(eleccionSelected);
					response.getWriter().print(jsonArray);
				}
			}else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			logger.error("Error en el metodo doPost()",e);
		}
	}

	/**
	 * @return the elecList
	 */
	public List<Eleccion> getElecList() {
		return elecList;
	}

	/**
	 * @param elecList the elecList to set
	 */
	public void setElecList(List<Eleccion> elecList) {
		this.elecList = elecList;
	}
}
