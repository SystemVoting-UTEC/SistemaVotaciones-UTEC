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

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utec.voting.modelo.TipoCandidato;
import com.utec.voting.util.ClientWebService;

/**
 * @author manuel cardona
 *
 */
@WebServlet(name = "tipoCandidato.do", urlPatterns = { "/tipoCandidato.do" })
public class TipoCandidatoController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URI = "http://34.70.70.109/utec_voting_system_webservice/service/";
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoCandidatoController.class);
	
	private List<TipoCandidato> tpcList = new ArrayList<TipoCandidato>();
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
		TipoCandidato tpcSelected=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			 if(request.getParameter("btnModificar")!=null){
				 if(request.getParameter("tcaIdEdi") != null && request.getParameter("tcaTipoEdi") != null) {
					 TipoCandidato tpcEdit = new TipoCandidato();
					 tpcEdit.setTcaId(Integer.parseInt(request.getParameter("tcaIdEdi")));
					 tpcEdit.setTcaTipo(request.getParameter("tcaTipoEdi"));
					 JSONObject object = new JSONObject(tpcEdit);
					object = new JSONObject(new ClientWebService().clienteWS(URI+"tipo_candidato",object, "PUT"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj",resp);
					 
				 }
			 }
			 
			 if(request.getParameter("btnInsertarTipoCandidato")!=null){
				 if(request.getParameter("tcaTipo") != null) {
					 TipoCandidato tpcInsert = new TipoCandidato();
					 tpcInsert.setTcaTipo(request.getParameter("tcaTipo"));
					 JSONObject object = new JSONObject(tpcInsert);
					object = new JSONObject(new ClientWebService().clienteWS(URI+"tipo_candidato",object, "POST"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj",resp);
				 }
			 }
			
			if(request.getParameter("id") == null) {
				tpcList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"tipo_candidato", "GET"),TipoCandidato[].class);
				request.setAttribute("tpcList", tpcList);
				res=request.getRequestDispatcher("mtmTipoCandidato.jsp");
				res.forward(request, response);
			} else {
				for (TipoCandidato tpc : tpcList) {
					if(tpc.getTcaId() == Integer.parseInt(request.getParameter("id"))) {
						tpcSelected = new TipoCandidato(); 
						tpcSelected.setTcaId(tpc.getTcaId());
						tpcSelected.setTcaTipo(tpc.getTcaTipo());
						
					}
				}
				String jsonArray = gson.toJson(tpcSelected);
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
	public List<TipoCandidato> getTpcList() {
		return tpcList;
	}

	/**
	 * @param tpcList the tpcList to set
	 */
	public void setTpcList(List<TipoCandidato> tpcList) {
		this.tpcList = tpcList;
	}

}
