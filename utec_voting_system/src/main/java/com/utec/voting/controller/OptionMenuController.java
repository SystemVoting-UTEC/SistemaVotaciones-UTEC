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
import com.utec.voting.modelo. OptionMenu;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author Manuel Cardona
 *
 */
@WebServlet(name = "optMenu.do", urlPatterns = { "/optMenu.do" })
public class OptionMenuController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(OptionMenuController.class);
	
	private List<OptionMenu> optMenuList = new ArrayList<OptionMenu>();
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
		 OptionMenu  optionMenuSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			//Validando si existe la variable de sesion principal
			if(us != null) {
				if(request.getParameter("btnModificarOptionMenu")!=null){
					if(request.getParameter("optNombreEdi") != null && request.getParameter("optURLEdi") != null && request.getParameter("optIconoEdi") != null) {
						  OptionMenu  optionMenuEdit = new  OptionMenu();
						  optionMenuEdit.setOptId(Integer.parseInt(request.getParameter("optIdEdi")));
                          optionMenuEdit.setOptNombre(request.getParameter("optNombreEdi"));
                          optionMenuEdit.setOptURL(request.getParameter("optURLEdi"));
                          optionMenuEdit.setOptIcono(request.getParameter("optIconoEdi"));
                          JSONObject object = new JSONObject(optionMenuEdit);
						object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/option_menu",object, "PUT"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
						 
					 }
				 }
				 
				 if(request.getParameter("btnInsertarOptionMenu")!=null){
					 if(request.getParameter("optNombre") != null && request.getParameter("optURL") != null && request.getParameter("optIcono") != null) {
						  OptionMenu  optionMenuInsert = new  OptionMenu();
						  optionMenuInsert.setOptNombre(request.getParameter("optNombre"));
						  optionMenuInsert.setOptURL(request.getParameter("optURL"));
						  optionMenuInsert.setOptIcono(request.getParameter("optIcono"));
						 JSONObject object = new JSONObject(optionMenuInsert);
						object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/option_menu",object, "POST"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
					 }
				 }
				
				if(request.getParameter("id") == null) {
					optMenuList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/option_menu", "GET"), OptionMenu[].class);
					request.setAttribute("optMenuList", optMenuList);
					res=request.getRequestDispatcher("mtmOptionMenu.jsp");
					res.forward(request, response);
				} else {
					for ( OptionMenu opt : optMenuList) {
						if(opt.getOptId() == Integer.parseInt(request.getParameter("id"))) {
							 optionMenuSelected = new  OptionMenu(); 
						}
					}
					String jsonArray = gson.toJson(optionMenuSelected);
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
}
