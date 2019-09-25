package com.utec.voting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.utec.voting.modelo.Partido;
import com.utec.voting.service.PartidoService;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class PartidoController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PartidoController.class);
	
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
	 * @throws SQLException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             RequestDispatcher res=null;
            PartidoService ob=new PartidoService();
            Partido p;
            /*if(request.getParameter("btnMostrar")!=null){*/
            request.setAttribute("mosPart", ob.getAll());
            /*}*/
            //Insertar Partido
            if(request.getParameter("btnInsertar")!=null){
                p = new Partido(0,request.getParameter("txtPartido"));
                if(ob.save(p)){
                    request.setAttribute("msj", 1);
                }else{
                    request.setAttribute("msj", "Error");
                }
                request.setAttribute("mosPart", ob.getAll());
            }
            //Eliminar Partido
            if(request.getParameter("btnDelete")!=null){
                p = new Partido(Integer.parseInt(request.getParameter("txtID")),"");
                if(ob.delete(p)){
                    request.setAttribute("msj", 2);
                }else{
                    request.setAttribute("msj", "Error");
                }
                request.setAttribute("mosPart", ob.getAll());
            }
            //Modificar Partido
            if(request.getParameter("btnModificar")!=null){
                p = new Partido(Integer.parseInt(request.getParameter("txtId")),request.getParameter("txtPartido"));
                if(ob.update(p)){
                    request.setAttribute("msj", 3);
                }else{
                    request.setAttribute("msj", "Error");
                }
                request.setAttribute("mosPart", ob.getAll());
            }
            
            res=request.getRequestDispatcher("adminpart.jsp");
            res.forward(request, response);
        }catch (Exception e) {
        	logger.error("Error en processRequest : ",e);
		}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.error("Error en doGet : ",e);
		}
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
        try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.error("Error en doPost : ",e);
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
    }// </editor-fold>

}
