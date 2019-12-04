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
import com.utec.voting.modelo.Departamento;
import com.utec.voting.modelo.Municipio;
import com.utec.voting.modelo.Partido;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.TipoCandidato;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author manuel cardona
 *
 */
@WebServlet(name = "candidato.do", urlPatterns = { "/candidato.do" })
public class CandidatoController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URI = "http://34.70.70.109/utec_voting_system_webservice/service/";
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(CandidatoController.class);
	
	private List<Candidato> canList = new ArrayList<Candidato>();
	private List<Partido> parList = new ArrayList<Partido>();
	private List<Persona> perList = new ArrayList<Persona>();
	private List<Departamento> depList = new ArrayList<Departamento>();
	private List<TipoCandidato> tpcList = new ArrayList<TipoCandidato>();
	private List<Municipio> munList = new ArrayList<Municipio>();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			if (us != null) {
				RequestDispatcher res=null;
				Candidato candidatoSelected=null;
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				response.setContentType("application/json");
					 if(request.getParameter("btnModificarCandidato")!=null){
						 if(request.getParameter("canIdEdi") != null && request.getParameter("canPerDuiEdi") != null  && request.getParameter("canParIdEdi") != null && request.getParameter("canDepIdEdi") != null && request.getParameter("canTcaIdEdi") != null) {
							 Candidato candidatoEdit = new Candidato();
							 Persona personaEdit = new Persona();
							 Partido partidoEdit = new Partido();
							 Departamento departamentoEdit = new Departamento();
							 TipoCandidato tpcEdit = new TipoCandidato();
							 candidatoEdit.setCanId(Integer.parseInt(request.getParameter("canIdEdi")));
							 personaEdit.setPerDui(request.getParameter("canPerDuiEdi") );
							 candidatoEdit.setCanPerDui(personaEdit);
							 partidoEdit.setParId(Integer.parseInt(request.getParameter("canParIdEdi")));
							 candidatoEdit.setCanParId(partidoEdit);
							 departamentoEdit.setDepId(Integer.parseInt(request.getParameter("canDepIdEdi")));
							 candidatoEdit.setCanDepId(departamentoEdit);
							 tpcEdit.setTcaId(Integer.parseInt(request.getParameter("canTcaIdEdi")));
							 candidatoEdit.setCanTcaId(tpcEdit);
							 candidatoEdit.setCanEstado(Integer.parseInt(request.getParameter("canEstadoEdi")));
							 JSONObject object = new JSONObject(candidatoEdit);
							object = new JSONObject(new ClientWebService().clienteWS(URI+"candidato",object, "PUT"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
							 
						 }
					 }
					 
					 if(request.getParameter("btnInsertarCandidato")!=null){
						 if(request.getParameter("canPerDui") != null  && request.getParameter("canParId") != null && request.getParameter("canDepId") != null && request.getParameter("canTcaId") != null ) {
							 Candidato candidato = new Candidato();
							 Persona persona = new Persona();
							 Partido partido = new Partido();
							 Departamento depto = new Departamento();
							 TipoCandidato tpcEdit = new TipoCandidato();
							 persona.setPerDui(request.getParameter("canPerDui") );
							 candidato.setCanPerDui(persona);
							 partido.setParId(Integer.parseInt(request.getParameter("canParId")));
							 candidato.setCanParId(partido);
							 depto.setDepId(Integer.parseInt(request.getParameter("canDepId")));
							 candidato.setCanDepId(depto);
							 tpcEdit.setTcaId(Integer.parseInt(request.getParameter("canTcaId")));
							 candidato.setCanTcaId(tpcEdit);
							 candidato.setCanEstado(Integer.parseInt(request.getParameter("canEstado")));
							 JSONObject object = new JSONObject(candidato);
							object = new JSONObject(new ClientWebService().clienteWS(URI+"candidato",object, "POST"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
						 }
					 }
					
					if(request.getParameter("id") == null) {
						canList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"candidato", "GET"),Candidato[].class);
						parList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"partido", "GET"),Partido[].class);
						perList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"persona", "GET"),Persona[].class);
						depList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"departamento", "GET"),Departamento[].class);
						tpcList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"tipo_candidato", "GET"),TipoCandidato[].class);
//						munList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"municipio", "GET"),Municipio[].class);
						request.setAttribute("canList", canList);
						request.setAttribute("parList", parList);
						request.setAttribute("perList", perList);
						request.setAttribute("depList", depList);
						request.setAttribute("tpcList", tpcList);
						request.setAttribute("munList", munList);
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
								candidatoSelected.setCanMunId(can.getCanMunId());
								candidatoSelected.setCanEstado(can.getCanEstado());
							}
						}
						String jsonArray = gson.toJson(candidatoSelected);
						response.getWriter().print(jsonArray);
					}
			} else {
				response.sendRedirect("index.jsp");
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

	/**
	 * @return the perList
	 */
	public List<Persona> getPerList() {
		return perList;
	}

	/**
	 * @param perList the perList to set
	 */
	public void setPerList(List<Persona> perList) {
		this.perList = perList;
	}

	/**
	 * @return the depList
	 */
	public List<Departamento> getDepList() {
		return depList;
	}

	/**
	 * @param depList the depList to set
	 */
	public void setDepList(List<Departamento> depList) {
		this.depList = depList;
	}

	/**
	 * @return the tpcList
	 */
	public List<TipoCandidato> getTpcList() {
		return tpcList;
	}

	/**
	 * @param tpcList the tpcList to set
	 */
	public void setTpcList(List<TipoCandidato> tpcList) {
		this.tpcList = tpcList;
	}

	/**
	 * @return the munList
	 */
	public List<Municipio> getMunList() {
		return munList;
	}

	/**
	 * @param munList the munList to set
	 */
	public void setMunList(List<Municipio> munList) {
		this.munList = munList;
	}
}
