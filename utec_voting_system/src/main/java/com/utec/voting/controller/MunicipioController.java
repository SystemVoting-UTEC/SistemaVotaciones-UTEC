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
import com.utec.voting.modelo.Municipio;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author Manuel Cardona
 *
 */
@WebServlet(name = "municipio.do", urlPatterns = { "/municipio.do" })
public class MunicipioController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URI = "http://34.70.70.109/utec_voting_system_webservice/service/";
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(MunicipioController.class);
	private List<Departamento> depList = new ArrayList<Departamento>();
	
	private List<Municipio>  munList = new ArrayList<Municipio>();
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
		Municipio MunicipioSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			//Validando si existe la variable de sesion principal
			if(us != null) {
				if(request.getParameter("btnModificar")!=null){
					 if(request.getParameter("munIdEdi") != null && request.getParameter("munNombreEdi") != null  && request.getParameter("munDepIdEdi") != null) {
						 Municipio MunicipioEdit = new Municipio();
						 Departamento departamentoEdit = new Departamento();
						 MunicipioEdit.setMunId(Integer.parseInt(request.getParameter("munIdEdi")));
						 MunicipioEdit.setMunNombre(request.getParameter("munNombreEdi"));
						 departamentoEdit.setDepId(Integer.parseInt(request.getParameter("munDepIdEdi")));
						 MunicipioEdit.setMunDepId(departamentoEdit);
						 JSONObject object = new JSONObject(MunicipioEdit);
						object = new JSONObject(new ClientWebService().clienteWS(URI+"municipio",object, "PUT"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
						 
					 }
				 }
				 
				 if(request.getParameter("btnInsertarMunicipio")!=null){
					 if(request.getParameter("munNombre") != null  && request.getParameter("munDepId") != null) {
						 Municipio MunicipioInsert = new Municipio();
						 Departamento departamentoEdit = new Departamento();
						 MunicipioInsert.setMunNombre(request.getParameter("munNombre"));
						 departamentoEdit.setDepId(Integer.parseInt(request.getParameter("munDepId")));
						 MunicipioInsert.setMunDepId(departamentoEdit);
						 JSONObject object = new JSONObject(MunicipioInsert);
						object = new JSONObject(new ClientWebService().clienteWS(URI+"municipio",object, "POST"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
					 }
				 }
				
				if(request.getParameter("id") == null) {
					munList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"municipio", "GET"),Municipio[].class);
					depList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"departamento", "GET"),Departamento[].class);
					request.setAttribute("munList", munList);
					request.setAttribute("depList", depList);
					res=request.getRequestDispatcher("mtmMunicipio.jsp");
					res.forward(request, response);
				} else {
					for (Municipio mun : munList) {
						if(mun.getMunId() == Integer.parseInt(request.getParameter("id"))) {
							MunicipioSelected = new Municipio(); 
							MunicipioSelected.setMunId(mun.getMunId());
							MunicipioSelected.setMunNombre(mun.getMunNombre());
							MunicipioSelected.setMunDepId(mun.getMunDepId());
						}
					}
					String jsonArray = gson.toJson(MunicipioSelected);
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
	public List<Municipio> getMunList() {
		return munList;
	}

	/**
	 * @param genList the genList to set
	 */
	public void setMunList(List<Municipio> munList) {
		this.munList = munList;
	}
	public List<Departamento> getDepList() {
		return depList;
	}

	/**
	 * @param depList the depList to set
	 */
	public void setDepList(List<Departamento> depList) {
		this.depList = depList;
	}

}
