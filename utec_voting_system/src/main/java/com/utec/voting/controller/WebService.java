package com.utec.voting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.utec.voting.jdbc.Conexion;
/**
 *
 * @author KevinManuel
 */
public class WebService extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            PreparedStatement ps;//para preparar sentencias SQL
            ResultSet rs;
            Conexion c = new Conexion();
            ArrayList<Integer> l1 = new ArrayList<Integer>();
            ArrayList<String> l2 = new ArrayList<String>();
            try {
                String sql = "SELECT public.\"CandidatoDiputado\".candidatodiputado_id , candidatodiputado_nombre, (select count(*) from public.\"VotoCandidatoDiputado\"\n" +
"                                                                            where public.\"VotoCandidatoDiputado\".candidatodiputado_id = public.\"CandidatoDiputado\".candidatodiputado_id ) as voto FROM public.\"CandidatoDiputado\";";
               ps = c.getConnection().prepareStatement(sql);
               rs = ps.executeQuery();
               while (rs.next()) {
                        l1.add(rs.getInt(3));
                        l2.add(rs.getString(2));
              }
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
            // crear un array donde 0 sean los nombres y 1 los votos
			Map<String, ArrayList> datos = new HashMap<String, ArrayList>();
            datos.put("votos", l1);
            datos.put("candidatos", l2);
            JSONArray gson = new JSONArray();
            gson.put(datos);
            out.print(gson);
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
    }// </editor-fold>
}
