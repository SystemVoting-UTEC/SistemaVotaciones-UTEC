/**
 * 
 */
package com.utec.voting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import com.utec.voting.modelo.Candidato;
import com.utec.voting.modelo.Eleccion;
import com.utec.voting.modelo.Partido;
import com.utec.voting.modelo.Sufragio;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author Kevin Orellana
 *
 */
@WebServlet(value = "/sufragio.do")
public class SufragioController extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(SufragioController.class);
	private Usuario usr = new Usuario();
	private List<Candidato> canList = new ArrayList<Candidato>();
	private List<Partido> parList = new ArrayList<Partido>();
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private Candidato getCandidato(Integer id) {
		try {
			for(Candidato can : canList)
				if(id == can.getCanId())
					return can;
		} catch (Exception e) {
			logger.error("Error en metodo getCandidato ", e);
		}
		return null;
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion ;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		String votos = request.getParameter("voto");
		String dui = request.getParameter("dui");
		Eleccion elc = new Eleccion();
		Integer res;
		try {
			sesion = request.getSession(true);
			usr = (Usuario) sesion.getAttribute("usuario");
			elc = (Eleccion) sesion.getAttribute("eleccion");
			canList = (List<Candidato>) sesion.getAttribute("canList");
			if (dui != null && votos != null && elc != null) {
				String[] l1 = votos.split(",");
				double sufragio = (1 / l1.length);
				for (String voto: l1) {
					Candidato can = getCandidato(new Double(voto).intValue());
					if(can != null) {
						Sufragio sufra = new Sufragio();
						sufra.setSufPerDui(usr.getUsPerDui());
						sufra.setSufSufragio(sufragio);
						sufra.setSufCanId(can);
						sufra.setElcId(elc);
						JSONObject object = new JSONObject(sufra);
						res = gson.fromJson(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/voto",object, "POST"), Integer.class);
					}
	            }
			} else {
				if (usr != null) {
					canList = new ArrayList<Candidato>();
					response.sendRedirect("sufragio.jsp");
					parList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/partido", "GET"),Partido[].class);
					canList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/candidato/"+ usr.getUsPerDui().getPerDepId().getDepId(),"GET"), Candidato[].class);
					sesion.setAttribute("parList", parList);
					sesion.setAttribute("canList", canList);
				} else {
					response.sendRedirect("graficoVotaciones.jsp");
				}
			}
		} catch (Exception e) {
			logger.error("Error en el servlet SufragioController en el método processRequest: ", e);
			response.sendRedirect("graficoVotaciones.jsp");
		}
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

	/**
	 * @return the usr
	 */
	public Usuario getUsr() {
		return usr;
	}

	/**
	 * @param usr the usr to set
	 */
	public void setUsr(Usuario usr) {
		this.usr = usr;
	}

	/**
	 * @return the canList
	 */
	public List<Candidato> getCanList() {
		return canList;
	}

	/**
	 * @param canList the canList to set
	 */
	public void setCanList(List<Candidato> canList) {
		this.canList = canList;
	}

	/**
	 * @return the parList
	 */
	public List<Partido> getParList() {
		return parList;
	}

	/**
	 * @param parList the parList to set
	 */
	public void setParList(List<Partido> parList) {
		this.parList = parList;
	}
}
