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
import com.utec.voting.modelo.Departamento;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author kevin_orellana
 *
 */
@WebServlet(name = "departamento.do", urlPatterns = { "/departamento.do" })
public class DepartamentoController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URI = "http://localhost:8080/utec_voting_system_webservice/service/";
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PartidoController.class);
	
	private List<Departamento> depList = new ArrayList<Departamento>();
	
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		    RequestDispatcher res=null;
			Departamento departamentoSelected=null;
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			response.setContentType("application/json");
			try {
				HttpSession sesion = request.getSession(true);
				Usuario us = (Usuario) sesion.getAttribute("usuario");
				//Validando si existe la variable de sesion principal
				if(us != null) {
					if(request.getParameter("btnModificar")!=null){
						 if(request.getParameter("depIdEdi") != null && request.getParameter("depNombreEdi") != null) {
							 Departamento departamentoEdit = new Departamento();
							 departamentoEdit.setDepId(Integer.parseInt(request.getParameter("depIdEdi")));
							 departamentoEdit.setDepNombre(request.getParameter("depNombreEdi"));
							 
							 JSONObject object = new JSONObject(departamentoEdit);
							object = new JSONObject(new ClientWebService().clienteWS(URI+"departamento",object, "PUT"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
							 
						 }
					 }
					 
					 if(request.getParameter("btnInsertarDepartamento")!=null){
						 if(request.getParameter("depNombre") != null) {
							 Departamento departamentoInsert = new Departamento();
							 departamentoInsert.setDepNombre(request.getParameter("depNombre"));
							 
							 JSONObject object = new JSONObject(departamentoInsert);
							object = new JSONObject(new ClientWebService().clienteWS(URI+"departamento",object, "POST"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
						 }
					 }
					
					if(request.getParameter("id") == null) {
						depList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"departamento", "GET"),Departamento[].class);
						request.setAttribute("depList", depList);
						res=request.getRequestDispatcher("mtmDepartamento.jsp");
						res.forward(request, response);
					} else {
						for (Departamento dep : depList) {
							if(dep.getDepId() == Integer.parseInt(request.getParameter("id"))) {
								departamentoSelected = new Departamento(); 
								departamentoSelected.setDepId(dep.getDepId());
								departamentoSelected.setDepNombre(dep.getDepNombre());
								
							}
						}
						String jsonArray = gson.toJson(departamentoSelected);
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
		 * @return the genList
		 */
		public List<Departamento> getDepList() {
			return depList;
		}
		
		/**
		 * @param genList the genList to set
		 */
		public void setDepList(List<Departamento> depList) {
			this.depList = depList;
		}
}
