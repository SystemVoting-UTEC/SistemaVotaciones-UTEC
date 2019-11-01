/**
 * 
 */
package com.utec.voting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.Genero;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author kevin_orellana
 *
 */
public class GeneroController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(GeneroController.class);
	
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
	
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
    @SuppressWarnings("unchecked")
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action = request.getParameter("action");
		List<Genero> genList = new ArrayList<Genero>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");

		if (action != null) {
			try {
				if (action.equals("list")) {
					// Fetch Data from Student Table
					genList = gson.fromJson(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero", "GET"), ArrayList.class);

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", genList);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				} else if (action.equals("create") || action.equals("update")) {
					Genero genero = new Genero();
					if (request.getParameter("genId") != null) {
						int genId = Integer.parseInt(request.getParameter("genId"));
						genero.setGenId(genId);
					}
					if (request.getParameter("genGenero") != null) {
						String genGenero = request.getParameter("genGenero");
						genero.setGenGenero(genGenero);
					}
					JSONObject object = new JSONObject(genero);
					if (action.equals("create")) {
						// Create new record
						genero = gson.fromJson(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero",object, "POST"), Genero.class);
					} else if (action.equals("update")) {
						genero = gson.fromJson(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero",object, "PUT"), Genero.class);
					}
					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", genero);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					
				}
			} catch (Exception ex) {
				logger.error("Error en el metodo doPost: ",ex);
				JSONROOT.put("Result", "ERROR");
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				response.getWriter().print(error);
			}
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