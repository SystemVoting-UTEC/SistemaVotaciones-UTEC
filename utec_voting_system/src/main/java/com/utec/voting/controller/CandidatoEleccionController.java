/**
 * 
 */
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
import com.utec.voting.modelo.Candidato;
import com.utec.voting.modelo.CandidatoEleccion;
import com.utec.voting.modelo.Eleccion;
import com.utec.voting.modelo.OptionTipoUsuario;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author Manuel Cardona
 *
 *
 */
@WebServlet(name = "candidatoElecion.do", urlPatterns = { "/candidatoElecion.do" })
public class CandidatoEleccionController extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URI = "http://localhost:8080/utec_voting_system_webservice/service/";
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(CandidatoEleccionController.class);

	private List<CandidatoEleccion> canElecList = new ArrayList<>();
	private List<Candidato> canList = new ArrayList<Candidato>();
	private List<Eleccion> elecList = new ArrayList<Eleccion>();

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
		doPost(request, response);
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			if (us != null) {
				CandidatoEleccion candidatoElcSelect=null;
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				RequestDispatcher res = null;
				response.setContentType("application/json");
				if (request.getParameter("btnModificar") != null) {
					if (request.getParameter("celIdEdi") != null && request.getParameter("elcIdEdi") != null && request.getParameter("canIdEdi") != null) {
						CandidatoEleccion CandidatoEleccionEdit = new CandidatoEleccion();
						Eleccion EleccionEdit = new Eleccion();
						Candidato CandidatoEdit = new Candidato();
						CandidatoEleccionEdit.setCelId(Integer.parseInt(request.getParameter("celIdEdi")));
						EleccionEdit.setElcId(Integer.parseInt(request.getParameter("elcIdEdi")));
						CandidatoEleccionEdit.setElcId(EleccionEdit);
						CandidatoEdit.setCanId(Integer.parseInt(request.getParameter("canIdEdi")));
						CandidatoEleccionEdit.setCanId(CandidatoEdit);
						JSONObject object = new JSONObject(CandidatoEleccionEdit);
						object = new JSONObject(new ClientWebService().clienteWS(URI + "CandidatoEleccion", object, "DELETE"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj", resp);

					}
				}

				if (request.getParameter("btnInsertarCandidatoEleccion") != null) {
					if (request.getParameter("elcId") != null && request.getParameter("canId") != null) {
						CandidatoEleccion CandidatoEleccion = new CandidatoEleccion();
						Eleccion Eleccion = new Eleccion();
						Candidato Candidato = new Candidato();
						Eleccion.setElcId(Integer.parseInt(request.getParameter("elcId")));
						CandidatoEleccion.setElcId(Eleccion);
						Candidato.setCanId(Integer.parseInt(request.getParameter("canId")));
						CandidatoEleccion.setCanId(Candidato);
						JSONObject object = new JSONObject(CandidatoEleccion);
						object = new JSONObject(new ClientWebService().clienteWS(URI + "CandidatoEleccion", object, "POST"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj", resp);
					}
				}
				if(request.getParameter("id") == null) {
					canElecList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI + "CandidatoEleccion", "GET"), CandidatoEleccion[].class);
					canList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI + "candidato", "GET"), Candidato[].class);
					elecList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI + "eleccion/all/1", "GET"), Eleccion[].class);
					request.setAttribute("canElecList", canElecList);
					request.setAttribute("canList", canList);
					request.setAttribute("elecList", elecList);
					res = request.getRequestDispatcher("mtmCandidatoEleccion.jsp");
					res.forward(request, response);
				}else {
					for (CandidatoEleccion can : canElecList) {
						if(can.getCelId() == Integer.parseInt(request.getParameter("id"))) {
							candidatoElcSelect = new CandidatoEleccion();
							candidatoElcSelect.setCelId(can.getCelId());
							candidatoElcSelect.setCanId(can.getCanId());
							candidatoElcSelect.setElcId(can.getElcId());
						}
					}
					String jsonArray = gson.toJson(candidatoElcSelect);
					response.getWriter().print(jsonArray);
				}
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			logger.error("Error en el metodo doPost()",e);
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
	 * @return the canList
	 */
	public List<CandidatoEleccion> getcanElecList() {
		return canElecList;
	}

	/**
	 * @param canList the genList to set
	 */
	public void setCanElecList(List<CandidatoEleccion> canElecList) {
		this.canElecList = canElecList;
	}

	/**
	 * @return the canList
	 */
	public List<Candidato> getCanList() {
		return canList;
	}

	/**
	 * @param canList the genList to set
	 */
	public void setCanList(List<Candidato> canList) {
		this.canList = canList;
	}

	/**
	 * @return the canList
	 */
	public List<Eleccion> getElecList() {
		return elecList;
	}

	/**
	 * @param canList the genList to set
	 */
	public void setElecList(List<Eleccion> elecList) {
		this.elecList = elecList;
	}

}
