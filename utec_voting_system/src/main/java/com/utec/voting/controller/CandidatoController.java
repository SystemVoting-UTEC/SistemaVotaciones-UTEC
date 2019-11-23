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

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.TipoCandidato;
import com.utec.voting.modelo.Candidato;
import com.utec.voting.modelo.Departamento;
import com.utec.voting.modelo.Partido;
import com.utec.voting.util.ClientWebService;

/**
 * @author manuel cardona
 *
 */
@WebServlet(name = "genero.do", urlPatterns = { "/candidato.do" })
public class CandidatoController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(CandidatoController.class);
	
	private List<Candidato> canList = new ArrayList<Candidato>();
	private List<Partido> parList = new ArrayList<Partido>();
	private List<Persona> perList = new ArrayList<Persona>();
	private List<Departamento> depList = new ArrayList<Departamento>();
	private List<TipoCandidato> tpcList = new ArrayList<TipoCandidato>();
	
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
    	doPost(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher res=null;
		Candidato candidatoSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			 if(request.getParameter("btnModificar")!=null){
				 if(request.getParameter("canIdEdi") != null && request.getParameter("canPerDuiEdi") != null  && request.getParameter("canParIdEdi") != null && request.getParameter("canDepIdEdi") != null && request.getParameter("canTcaIdEdi") != null) {
					 Candidato candidatoEdit = new Candidato();
					 Persona personaEdit = new Persona();
					 Partido partidoEdit = new Partido();
					 Departamento departamentoEdit = new Departamento();
					 TipoCandidato tpcEdit = new TipoCandidato();
					 candidatoEdit.setCanId(Integer.parseInt(request.getParameter("canIdEdi")));
					 candidatoEdit.setCanPerDui(personaEdit);
					 candidatoEdit.setCanParId(partidoEdit);
					 candidatoEdit.setCanDepId(departamentoEdit);
					 candidatoEdit.setCanTcaId(tpcEdit);
					 JSONObject object = new JSONObject(candidatoEdit);
					object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero",object, "PUT"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj",resp);
					 
				 }
			 }
			 
			 if(request.getParameter("btnInsertarCandidato")!=null){
				 if(request.getParameter("canPerDui") != null  && request.getParameter("canParId") != null && request.getParameter("canDepId") != null && request.getParameter("canTcaId") != null ) {
					 Candidato candidatoInsert = new Candidato();
					 Persona personaInsert = new Persona();
					 Partido partidoInsert = new Partido();
					 Departamento departamentoInsert = new Departamento();
					 TipoCandidato tpcInsert = new TipoCandidato();
					 candidatoInsert.setCanPerDui(personaInsert);
					 candidatoInsert.setCanParId(partidoInsert);
					 candidatoInsert.setCanDepId(departamentoInsert);
					 candidatoInsert.setCanTcaId(tpcInsert);
					 JSONObject object = new JSONObject(candidatoInsert);
					object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero",object, "POST"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj",resp);
				 }
			 }
			
			if(request.getParameter("id") == null) {
				canList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/candidato", "GET"),Candidato[].class);
				parList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/partido", "GET"),Partido[].class);
				perList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/persona", "GET"),Persona[].class);
				depList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/departamento", "GET"),Departamento[].class);
				tpcList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/tipo_candidato", "GET"),TipoCandidato[].class);
				request.setAttribute("canList", canList);
				request.setAttribute("canList", parList);
				request.setAttribute("canList", perList);
				request.setAttribute("canList", depList);
				request.setAttribute("canList", tpcList);
				res=request.getRequestDispatcher("mtmCandidato.jsp");
				res.forward(request, response);
			} else {
				for (Candidato can : canList) {
					if(can.getCanId() == Integer.parseInt(request.getParameter("id"))) {
						candidatoSelected = new Candidato(); 
						candidatoSelected.setCanId(can.getCanId());
						candidatoSelected.setCanPerDui(can.getCanPerDui());
						candidatoSelected.setCanParId(can.getCanParId());
						candidatoSelected.setCanDepId(can.getCanDepId());
						candidatoSelected.setCanTcaId(can.getCanTcaId());
					}
				}
				String jsonArray = gson.toJson(candidatoSelected);
				response.getWriter().print(jsonArray);
			}
		} catch (Exception e) {
			logger.error("Error en el metodo doPost()");
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
	 * @return the genList
	 */
	public List<Candidato> getCanList() {
		return canList;
	}

	/**
	 * @param genList the genList to set
	 */
	public void setCanList(List<Candidato> canList) {
		this.canList = canList;
	}
}
