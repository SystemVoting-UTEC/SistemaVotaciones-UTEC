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
import com.utec.voting.modelo.OptionMenu;
import com.utec.voting.modelo.OptionTipoUsuario;
import com.utec.voting.modelo.TipoUsuario;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author Manuel Cardona
 *
 */
@WebServlet(name = "OptionTipoUsuario.do", urlPatterns = { "/OptionTipoUsuario.do" })
public class OptionTipoUsuarioController extends HttpServlet implements Serializable{
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Variable de logueo para errores.
		 */
		static final Logger logger = Logger.getLogger(OptionTipoUsuarioController.class);
		
		private List<OptionTipoUsuario> OpTpUsuarioList = new ArrayList<OptionTipoUsuario>();
		private List<OptionMenu> optMenuList = new ArrayList<OptionMenu>();
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
			OptionTipoUsuario OptionTipoUsuarioSelected=null;
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			response.setContentType("application/json");
			try {
				HttpSession sesion = request.getSession(true);
				Usuario us = (Usuario) sesion.getAttribute("usuario");
				//Validando si existe la variable de sesion principal
				if(us != null) {
					if(request.getParameter("btnEliminarTipoMenu")!=null){
						 if(request.getParameter("optIdEdi") != null && request.getParameter("tusIdEdi") != null) {
							 OptionTipoUsuario OptionTipoUsuarioEdit = null;
							 OptionMenu OptionMenuEdit = new OptionMenu();
							 TipoUsuario TipoUsuarioEdit = new TipoUsuario();
							 OptionMenuEdit.setOptId(Integer.parseInt(request.getParameter("optIdEdi")));
							 TipoUsuarioEdit.setTusId(Integer.parseInt(request.getParameter("tusIdEdi")));
							 OptionTipoUsuarioEdit = new OptionTipoUsuario(OptionMenuEdit,TipoUsuarioEdit);
							 JSONObject object = new JSONObject(OptionTipoUsuarioEdit);
							object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/OptionTipoUsuario",object, "DELETE"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
						 }
					 }
					 
					 if(request.getParameter("btnInsertarOTU")!=null){
						 if(request.getParameter("optId") != null && request.getParameter("tusId") != null) {
							 OptionTipoUsuario OptionTipoUsuarioInsert = null;
							 OptionMenu OptionMenuInsert = new OptionMenu();
							 TipoUsuario TipoUsuarioInsert = new TipoUsuario();
							 OptionMenuInsert.setOptId(Integer.parseInt(request.getParameter("optId")));
							 TipoUsuarioInsert.setTusId(Integer.parseInt(request.getParameter("tusId")));
							 OptionTipoUsuarioInsert = new OptionTipoUsuario(OptionMenuInsert, TipoUsuarioInsert);
							 JSONObject object = new JSONObject(OptionTipoUsuarioInsert);
							object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/OptionTipoUsuario",object, "POST"));
							Integer resp = Integer.parseInt(object.get("response").toString());
							request.setAttribute("msj",resp);
						 }
					 }
					
					if(request.getParameter("id") == null) {
						OpTpUsuarioList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/OptionTipoUsuario", "GET"),OptionTipoUsuario[].class);
						optMenuList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/option_menu", "GET"),OptionMenu[].class);
						tpcUsuarioList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/TipoUsuario", "GET"),TipoUsuario[].class);
						request.setAttribute("OpTpUsuarioList", OpTpUsuarioList);
						request.setAttribute("optMenuList", optMenuList);
						request.setAttribute("tpcUsuarioList", tpcUsuarioList);
						res=request.getRequestDispatcher("mtmOptionTipoUsuario.jsp");
						res.forward(request, response);
					} else {
						for (OptionTipoUsuario tpcU : OpTpUsuarioList) {
							if(tpcU.getTusId().getTusId() == Integer.parseInt(request.getParameter("idd")) &&
								tpcU.getOptId().getOptId() == Integer.parseInt(request.getParameter("id"))) {
								OptionTipoUsuarioSelected = new OptionTipoUsuario(); 
								OptionTipoUsuarioSelected.setOptId(tpcU.getOptId());
								OptionTipoUsuarioSelected.setTusId(tpcU.getTusId());
							}
						}
						String jsonArray = gson.toJson(OptionTipoUsuarioSelected);
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
		 * @return the OpTpUsuarioList
		 */
		public List<OptionTipoUsuario> getOpOpTpUsuarioList() {
			return OpTpUsuarioList;
		}

		/**
		 * @param OpTpUsuarioList the OpTpUsuarioList to set
		 */
		public void setOpOpTpUsuarioList(List<OptionTipoUsuario> OpTpUsuarioList) {
			this.OpTpUsuarioList = OpTpUsuarioList;
		}
		
		public List<TipoUsuario> getTpcUsuarioList() {
			return tpcUsuarioList;
		}

		/**
		 * @param tpcUsuarioList the tpcUsuarioList to set
		 */
		public void setTpcUsuarioList(List<TipoUsuario> tpcUsuarioList) {
			this.tpcUsuarioList = tpcUsuarioList;
		}
		
		public List<OptionMenu> getOptMenuList() {
			return optMenuList;
		}

		/**
		 * @param tpcUsuarioList the tpcUsuarioList to set
		 */
		public void setOptMenuList(List<OptionMenu> optMenuList) {
			this.optMenuList = optMenuList;
		}
}