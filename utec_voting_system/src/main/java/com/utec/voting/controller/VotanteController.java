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

/**
 * Servlet implementation class VotanteController
 */
@WebServlet(name = "votante.do", urlPatterns = { "/votante.do" })
public class VotanteController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		Votante votanteSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			 if(request.getParameter("btnModificarVotante")!=null){
				 
			 }
			 
			if (request.getParameter("btnInsertarVotante") != null) {
				Votante votanteInsert = new Votante();
				Persona personaInert = new Persona();
				personaInert.setPerDui(request.getParameter("votPerDui"));
				votanteInsert.setVotPerDui(personaInert);
				JSONObject object = new JSONObject(votanteInsert);
				object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/votante", object, "POST"));
				Integer resp = Integer.parseInt(object.get("response").toString());
				request.setAttribute("msj", resp);
			}
			
			if(request.getParameter("id") == null) {
				votList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/votante", "GET"),Votante[].class);
				perList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/persona", "GET"),Persona[].class);
				request.setAttribute("votList", votList);
				request.setAttribute("perList", perList);
				res=request.getRequestDispatcher("mtmVotante.jsp");
				res.forward(request, response);
			} else {
				String voto = request.getParameter("id");
				for (Votante v : votList) {
					if(v.getVotPerDui().getPerDui().equals(request.getParameter("id"))) {
						votanteSelected = new Votante(); 
						votanteSelected.setVotFechaExp(v.getVotFechaExp());
						votanteSelected.setVotFechaVence(v.getVotFechaVence());
						votanteSelected.setVotPerDui(v.getVotPerDui());
					}
				}
				String jsonArray = gson.toJson(votanteSelected);
				response.getWriter().print(jsonArray);
			}
		} catch (Exception e) {
			logger.error("Error en el metodo doPost()");
		}
	}

}
