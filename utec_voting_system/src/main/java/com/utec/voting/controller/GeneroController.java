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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.Genero;
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
	
	private List<Genero> genList = new ArrayList<Genero>();
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
		Genero generoSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			 if(request.getParameter("btnModificar")!=null){
				 if(request.getParameter("genIdEdi") != null && request.getParameter("genGeneroEdi") != null  && request.getParameter("genNombreEdi") != null) {
					 Genero generoEdit = new Genero();
					 generoEdit.setGenId(Integer.parseInt(request.getParameter("genIdEdi")));
					 generoEdit.setGenGenero(request.getParameter("genGeneroEdi"));
					 generoEdit.setGenNombre(request.getParameter("genNombreEdi"));
					 JSONObject object = new JSONObject(generoEdit);
					object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero",object, "PUT"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj",resp);
					 
				 }
			 }
			 
			 if(request.getParameter("btnInsertarGenero")!=null){
				 if(request.getParameter("genGenero") != null  && request.getParameter("genNombre") != null) {
					 Genero generoInsert = new Genero();
					 generoInsert.setGenGenero(request.getParameter("genGenero"));
					 generoInsert.setGenNombre(request.getParameter("genNombre"));
					 JSONObject object = new JSONObject(generoInsert);
					object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero",object, "POST"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj",resp);
				 }
			 }
			
			if(request.getParameter("id") == null) {
				genList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero", "GET"),Genero[].class);
				request.setAttribute("genList", genList);
				res=request.getRequestDispatcher("mtmGenero.jsp");
				res.forward(request, response);
			} else {
				for (Genero gen : genList) {
					if(gen.getGenId() == Integer.parseInt(request.getParameter("id"))) {
						generoSelected = new Genero(); 
						generoSelected.setGenId(gen.getGenId());
						generoSelected.setGenGenero(gen.getGenGenero());
						generoSelected.setGenNombre(gen.getGenNombre());
					}
				}
				String jsonArray = gson.toJson(generoSelected);
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
	public List<Genero> getGenList() {
		return genList;
	}

	/**
	 * @param genList the genList to set
	 */
	public void setGenList(List<Genero> genList) {
		this.genList = genList;
	}
}
