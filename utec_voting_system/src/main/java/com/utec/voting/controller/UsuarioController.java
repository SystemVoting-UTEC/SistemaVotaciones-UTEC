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
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.TipoUsuario;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;
import com.utec.voting.util.EmailClient;
import com.utec.voting.util.Encriptar;

/**
 * @author kevin_orellana
 *
 */
@WebServlet(name = "usuario.do", urlPatterns = { "/usuario.do" })
public class UsuarioController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URI = "http://localhost:8080/utec_voting_system_webservice/service/";
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(UsuarioController.class);
	
    private List<Usuario> usList = new ArrayList<Usuario>();
    private List<Persona> perList = new ArrayList<Persona>();
    private List<TipoUsuario> tpusList = new ArrayList<TipoUsuario>();
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
		Usuario usuarioSelected=null;
		String password="";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			//Validando si existe la variable de sesion principal
			if(us != null) {
				if(request.getParameter("btnModificar")!=null){
					 if(request.getParameter("usPerDuiEdi") != null) {
						 Usuario usuarioEdit = new Usuario();
                         Persona per = new Persona();
                         TipoUsuario tipoUsuario = new TipoUsuario();
                         per.setPerDui(request.getParameter("usPerDuiEdi"));
                         usuarioEdit.setUsPerDui(per);
                         tipoUsuario.setTusId(Integer.parseInt(request.getParameter("usTusIdEdi")));
                         usuarioEdit.setUsTusId(tipoUsuario);
                         password = Encriptar.getPassword(Encriptar.MINUSCULAS+Encriptar.MAYUSCULAS+Encriptar.NUMEROS,8);
                         logger.error("Password: "+password);
                         usuarioEdit.setUsPassword(Encriptar.sha1(password));
                         usuarioEdit.setUsEstado(Integer.parseInt(request.getParameter("usEstadoEdi")));
						 JSONObject object = new JSONObject(usuarioEdit);
						object = new JSONObject(new ClientWebService().clienteWS(URI+"Usuario",object, "PUT"));
						Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
						 
					 }
				 }
				 
				 if(request.getParameter("btnInsertarUsuario")!=null){
					 if(request.getParameter("usPerDui") != null) {
                         Usuario usuarioInsert = new Usuario();
                         Persona per = new Persona();
                         TipoUsuario tipoUsuario = new TipoUsuario();
                         per.setPerDui(request.getParameter("usPerDui"));
                         usuarioInsert.setUsPerDui(per);
                         tipoUsuario.setTusId(Integer.parseInt(request.getParameter("usTusId")));
                         usuarioInsert.setUsTusId(tipoUsuario);
                         password = Encriptar.getPassword(Encriptar.MINUSCULAS+Encriptar.MAYUSCULAS+Encriptar.NUMEROS,8);
                         usuarioInsert.setUsPassword(Encriptar.sha1(password));
                         usuarioInsert.setUsEstado(Integer.parseInt(request.getParameter("usEstado")));
						 JSONObject object = new JSONObject(usuarioInsert);
						object = new JSONObject(new ClientWebService().clienteWS(URI+"Usuario",object, "POST"));
						Integer resp = Integer.parseInt(object.get("response").toString());
					    String emailBody = "Credenciales <br/> <b>Usuario:</b> "+per.getPerDui()+" <br/> <b>Clave: </b>"+password+"<br/>***<b>Este es un correo generado automaticamente, favor no responder!</b>";
				        String[] toEmails = { request.getParameter("usMail") };
				        EmailClient javaEmail = new EmailClient();
				        javaEmail.setMailServerProperties();
				        javaEmail.sendEmail(javaEmail.draftEmailMessage(emailBody,toEmails));
						request.setAttribute("msj",resp);
					 }
				 }
				
				if(request.getParameter("id") == null) {
                    usList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"Usuario", "GET"),Usuario[].class);
                    perList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"persona", "GET"),Persona[].class);
                    tpusList = ClientWebService.stringToArray(new ClientWebService().clienteWS(URI+"TipoUsuario", "GET"),TipoUsuario[].class);
                    request.setAttribute("usList", usList);
                    request.setAttribute("perList", perList);
                    request.setAttribute("tpusList", tpusList);
					res=request.getRequestDispatcher("mtmUsuario.jsp");
					res.forward(request, response);
				} else {
					for (Usuario usu : usList) {
						if(usu.getUsPerDui().getPerDui().equals(request.getParameter("id"))) {
                            usuarioSelected = new Usuario();
                            usuarioSelected.setUsPerDui(usu.getUsPerDui());
                            usuarioSelected.setUsTusId(usu.getUsTusId());
                            usuarioSelected.setUsEstado(usu.getUsEstado());
						}
					}
					String jsonArray = gson.toJson(usuarioSelected);
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
	 * @return the usList
	 */
	public List<Usuario> getusList() {
		return usList;
	}

	/**
	 * @param usList the usList to set
	 */
	public void setusList(List<Usuario> usList) {
		this.usList = usList;
    }

    public List<Usuario> getUsList() {
        return usList;
    }

    public void setUsList(List<Usuario> usList) {
        this.usList = usList;
    }

    public List<Persona> getPerList() {
        return perList;
    }

    public void setPerList(List<Persona> perList) {
        this.perList = perList;
    }

    public List<TipoUsuario> getTpusList() {
        return tpusList;
    }

    public void setTpusList(List<TipoUsuario> tpusList) {
        this.tpusList = tpusList;
    }
}
