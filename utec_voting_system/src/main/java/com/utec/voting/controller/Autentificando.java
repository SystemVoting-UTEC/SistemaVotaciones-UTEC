package com.utec.voting.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.utec.voting.modelo.Usuario;
import com.utec.voting.service.DepartamentoService;
import com.utec.voting.service.EstadoFamiliarService;
import com.utec.voting.service.GeneroService;
import com.utec.voting.service.UsuarioService;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Autentificando extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(Autentificando.class);
	
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
		try {
			UsuarioService usuarioService = new UsuarioService();
			DepartamentoService departamentoService = new DepartamentoService();
			GeneroService generoService = new GeneroService();
			EstadoFamiliarService estadoFamiliarService = new EstadoFamiliarService(); 
			response.setContentType("text/html;charset=UTF-8");
			String dui = request.getParameter("usuario");
			String pass = request.getParameter("pass");
			Usuario usr =  usuarioService.findByCredentials(dui, pass);
			if (usr != null) {
				Integer tipor = 1;
				if (usr.getUsTusId().getTusId() == tipor) {
					HttpSession sesion = request.getSession(true);
					sesion.setAttribute("usuario", usr);
					sesion.setAttribute("departamento", usr.getUsPerDui().getPerDepId());
					request.setAttribute("mosDepa", departamentoService.getAll());
					request.setAttribute("mosEsta", estadoFamiliarService.getAll());
					request.setAttribute("mosGene", generoService.getAll());
					response.sendRedirect("administracion.jsp");
				} else {
					HttpSession sesion = request.getSession(true);
					sesion.setAttribute("departamento", usr.getUsPerDui().getPerDepId());
					sesion.setAttribute("usuario", usr);
					sesion.setAttribute("diputado", dui);
					response.sendRedirect("votante.jsp");
				}
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

}
