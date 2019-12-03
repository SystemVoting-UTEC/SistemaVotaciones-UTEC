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
import com.utec.voting.modelo.TipoUsuario;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author Manuel Cardona
 *
 */
@WebServlet(name = "tipoUsuario.do", urlPatterns = { "/tipoUsuario.do" })
public class TipoUsuarioController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoUsuarioController.class);
	
	private List<TipoUsuario> tpcUsuarioList = new ArrayList<TipoUsuario>();
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
		TipoUsuario TipoUsuarioSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			//Validando si existe la variable de sesion principal
			if(us != null) {
				if(request.getParameter("btnModificar")!=null){
					 if(request.getParameter("tusIdEdi") != null && request.getParameter("tusTipoEdi") != null) {
						 TipoUsuario TipoUsuarioEdit = new TipoUsuario();
						 TipoUsuarioEdit.setTusId(Integer.parseInt(request.getParameter("tusIdEdi")));
						 TipoUsuarioEdit.setTusTipo(request.getParameter("tusTipoEdi"));
						 JSONObject object = new JSONObject(TipoUsuarioEdit);
						object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/TipoUsuario",object, "PUT"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
						 
					 }
				 }
				 
				 if(request.getParameter("btnInsertarTpcUsuario")!=null){
					 if(request.getParameter("tusTipo") != null) {
						 TipoUsuario TipoUsuarioInsert = new TipoUsuario();
						 TipoUsuarioInsert.setTusTipo(request.getParameter("tusTipo"));
						 JSONObject object = new JSONObject(TipoUsuarioInsert);
						object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/TipoUsuario",object, "POST"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
					 }
				 }
				
				if(request.getParameter("id") == null) {
					tpcUsuarioList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/TipoUsuario", "GET"),TipoUsuario[].class);
					request.setAttribute("tpcUsuarioList", tpcUsuarioList);
					res=request.getRequestDispatcher("mtmTipoUsuario.jsp");
					res.forward(request, response);
				} else {
					for (TipoUsuario tpcU : tpcUsuarioList) {
						if(tpcU.getTusId() == Integer.parseInt(request.getParameter("id"))) {
							TipoUsuarioSelected = new TipoUsuario(); 
							TipoUsuarioSelected.setTusId(tpcU.getTusId());
							TipoUsuarioSelected.setTusTipo(tpcU.getTusTipo());
						}
					}
					String jsonArray = gson.toJson(TipoUsuarioSelected);
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

	/**
	 * @return the tpcUsuarioList
	 */
	public List<TipoUsuario> getTpcUsuarioList() {
		return tpcUsuarioList;
	}

	/**
	 * @param tpcUsuarioList the tpcUsuarioList to set
	 */
	public void setTpcUsuarioList(List<TipoUsuario> tpcUsuarioList) {
		this.tpcUsuarioList = tpcUsuarioList;
	}
}
