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

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.Votante;
import com.utec.voting.util.ClientWebService;
import com.utec.voting.util.Formateador;

/**
 * Servlet implementation class VotanteController
 */
@WebServlet(name = "votante.do", urlPatterns = { "/votante.do" })
public class VotanteController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URI = "http://34.70.70.109/utec_voting_system_webservice/service/";
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(GeneroController.class);
	
	private List<Votante> votList = new ArrayList<Votante>();
	private List<Persona> perList = new ArrayList<Persona>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotanteController() {
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
		new Formateador();
		Votante votanteSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			if(request.getParameter("btnModificarVotante")!=null){
				if(request.getParameter("votPerDuiEdi") != null && request.getParameter("votFechaVenceEdi") != null && request.getParameter("votFechaExpEdi") != null && request.getParameter("estadoEdi") != null) {
					Votante votanteInsert = new Votante();
					Persona personaInert = new Persona();
					personaInert.setPerDui(request.getParameter("votPerDuiEdi"));
					votanteInsert.setVotPerDui(personaInert);
					votanteInsert.setVotFechaExp(request.getParameter("votFechaExpEdi"));
					votanteInsert.setVotFechaVence(request.getParameter("votFechaVenceEdi"));
					votanteInsert.setEstado(Integer.parseInt(request.getParameter("estadoEdi")));
					JSONObject object = new JSONObject(votanteInsert);
					object = new JSONObject(new ClientWebService().clienteWS(URI+"votante", object, "PUT"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj", resp);
				}
			}
			
			if (request.getParameter("btnInsertarVotante") != null) {
				if(request.getParameter("votPerDui") != null && request.getParameter("votFechaVence") != null && request.getParameter("votFechaExp") != null && request.getParameter("estado") != null) {
					Votante votanteInsert = new Votante();
					Persona personaInert = new Persona();
					personaInert.setPerDui(request.getParameter("votPerDui"));
					votanteInsert.setVotPerDui(personaInert);
					votanteInsert.setVotFechaExp( request.getParameter("votFechaExp"));
					votanteInsert.setVotFechaVence(request.getParameter("votFechaVence"));
					votanteInsert.setEstado(Integer.parseInt(request.getParameter("estado")));
					JSONObject object = new JSONObject(votanteInsert);
					object = new JSONObject(new ClientWebService().clienteWS(URI+"votante", object, "POST"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj", resp);
				}
			}
			
			if(request.getParameter("id") == null) {
				votList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"votante", "GET"),Votante[].class);
				perList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"persona", "GET"),Persona[].class);
				request.setAttribute("votList", votList);
				request.setAttribute("perList", perList);
				res=request.getRequestDispatcher("mtmVotante.jsp");
				res.forward(request, response);
			} 

			if(request.getParameter("id") != null){
				for (Votante v : votList) {
					if(v.getVotPerDui().getPerDui().equals(request.getParameter("id"))) {
						votanteSelected = new Votante(); 
						votanteSelected.setVotFechaExp(v.getVotFechaExp());
						votanteSelected.setVotFechaVence(v.getVotFechaVence());
						votanteSelected.setVotPerDui(v.getVotPerDui());
						votanteSelected.setEstado(v.getEstado());
					}
				}
				String jsonArray = gson.toJson(votanteSelected);
				response.getWriter().print(jsonArray);
			}
		} catch (Exception e) {
			logger.error("Error en el metodo doPost(): "+e);
			request.setAttribute("msj", 0);
		}
	}

}
