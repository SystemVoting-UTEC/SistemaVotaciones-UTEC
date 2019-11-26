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
import com.utec.voting.modelo.Partido;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author kevin_orellana
 *
 */
@WebServlet(value = "/partido.do")
public class PartidoController extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PartidoController.class);
	
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
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		    RequestDispatcher res=null;
			Partido PartidoSelected=null;
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			response.setContentType("application/json");
			try {
				HttpSession sesion = request.getSession(true);
				Usuario us = (Usuario) sesion.getAttribute("usuario");
				//Validando si existe la variable de sesion principal
				if(us != null) {
					if(request.getParameter("btnModificar")!=null){
						 if(request.getParameter("parIdEdi") != null && request.getParameter("parNombreEdi") != null  && request.getParameter("estadoEdi") != null) {
							 Partido PartidoEdit = new Partido();
							 PartidoEdit.setParId(Integer.parseInt(request.getParameter("parIdEdi")));
							 PartidoEdit.setParNombre(request.getParameter("parNombreEdi"));
							 PartidoEdit.setEstado(Integer.parseInt(request.getParameter("estadoEdi")));
							 JSONObject object = new JSONObject(PartidoEdit);
							object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/partido",object, "PUT"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
							 
						 }
					 }
					 
					 if(request.getParameter("btnInsertarPartido")!=null){
						 if(request.getParameter("parNombre") != null  && request.getParameter("estado") != null) {
							 Partido PartidoInsert = new Partido();
							 PartidoInsert.setParNombre(request.getParameter("parNombre"));
							 PartidoInsert.setEstado(Integer.parseInt(request.getParameter("estado")));
							 JSONObject object = new JSONObject(PartidoInsert);
							object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/partido",object, "POST"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
						 }
					 }
					
					if(request.getParameter("id") == null) {
						parList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/partido", "GET"),Partido[].class);
						request.setAttribute("parList", parList);
						res=request.getRequestDispatcher("mtmPartido.jsp");
						res.forward(request, response);
					} else {
						for (Partido par : parList) {
							if(par.getParId() == Integer.parseInt(request.getParameter("id"))) {
								PartidoSelected = new Partido(); 
								PartidoSelected.setParId(par.getParId());
								PartidoSelected.setParNombre(par.getParNombre());
								PartidoSelected.setEstado(par.getEstado());
							}
						}
						String jsonArray = gson.toJson(PartidoSelected);
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
