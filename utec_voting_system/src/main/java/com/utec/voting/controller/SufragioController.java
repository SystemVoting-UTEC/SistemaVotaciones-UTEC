/**
 * 
 */
package com.utec.voting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.Partido;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * @author Kevin Orellana
 *
 */
@WebServlet(value = "/sufragio.do") 
public class SufragioController extends HttpServlet  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(SufragioController.class);
	Usuario usr = new Usuario();
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
		HttpSession sesion = request.getSession(true);
		List<Partido> parList = new ArrayList<Partido>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			response.setContentType("text/html;charset=UTF-8");
			usr = (Usuario) sesion.getAttribute("usuario");
			if (usr != null) {
				logger.error("Objeto"+usr);
				response.sendRedirect("sufragio.jsp");
				parList = gson.fromJson(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/partido", "GET"), ArrayList.class);
				sesion.setAttribute("parList", parList);

			} else {
				response.sendRedirect("graficoVotaciones.jsp");
			}
		} catch (Exception e) {
			logger.error("Error en el servlet Autentificando en el método processRequest: ", e);
			response.sendRedirect("graficoVotaciones.jsp");
		}
		
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
        processRequest(request, response);
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
        processRequest(request, response);
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
	 * @return the usr
	 */
	public Usuario getUsr() {
		return usr;
	}

	/**
	 * @param usr the usr to set
	 */
	public void setUsr(Usuario usr) {
		this.usr = usr;
	}
}
