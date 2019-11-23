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
import com.utec.voting.modelo.EstadoFamiliar;
import com.utec.voting.modelo.Genero;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.ClientWebService;

/**
 * Servlet implementation class PersonaController
 */
@WebServlet(name = "persona.do", description = "Controller for table person", urlPatterns = { "/persona.do" })
public class PersonaController extends HttpServlet implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PersonaController.class);
    private List<Persona> perList = new ArrayList<Persona>();
    private List<Genero> genList = new ArrayList<Genero>();
    private List<Departamento> depList = new ArrayList<Departamento>();
    private List<EstadoFamiliar> stdList = new ArrayList<EstadoFamiliar>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonaController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession sesion = request.getSession(true);
			Usuario us = (Usuario) sesion.getAttribute("usuario");
			if(us != null) {
				RequestDispatcher res=null;
				Persona personaSelected=null;
				Genero gen = new Genero();
				Departamento dep = new Departamento();
				EstadoFamiliar std = new EstadoFamiliar();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				response.setContentType("application/json");
				
				if(request.getParameter("btnEditarPersona")!=null){
					 Persona persona = new Persona();
					 persona.setPerDui(request.getParameter("perDuiEdi"));
					 persona.setPerPNombre(request.getParameter("perPNombreEdi"));
					 persona.setPerSNombre(request.getParameter("perSNombreEdi"));
					 persona.setPerTNombre(request.getParameter("perTNombreEdi"));
					 persona.setPerPApellido(request.getParameter("perPApellidoEdi"));
					 persona.setPerSApellido(request.getParameter("perSApellidoEdi"));
					 persona.setPerFechaNac(request.getParameter("perFechaNacEdi"));
					 gen.setGenId(Integer.parseInt(request.getParameter("perGenIdEdi")));
					 persona.setPerGenId(gen);
					 dep.setDepId(Integer.parseInt(request.getParameter("perDepIdEdi")));
					 persona.setPerDepId(dep);
					 std.setEstId(Integer.parseInt(request.getParameter("perEstIdEdi")));
					 persona.setPerEstId(std);
					 persona.setPerMadre(request.getParameter("perMadreEdi"));
					 persona.setPerPadre(request.getParameter("perPadreEdi"));
					 persona.setPerEstado(Integer.parseInt(request.getParameter("perEstadoEdi")));
					 JSONObject object = new JSONObject(persona);
					object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/persona",object, "PUT"));
					Integer resp = Integer.parseInt(object.get("response").toString());
					request.setAttribute("msj",resp);
				 }
				 
				 if(request.getParameter("btnInsertarPersona")!=null){
					 Persona persona = new Persona();
					 persona.setPerDui(request.getParameter("perDui"));
					 persona.setPerPNombre(request.getParameter("perPNombre"));
					 persona.setPerSNombre(request.getParameter("perSNombre"));
					 persona.setPerTNombre(request.getParameter("perTNombre"));
					 persona.setPerPApellido(request.getParameter("perPApellido"));
					 persona.setPerSApellido(request.getParameter("perSApellido"));
					 persona.setPerFechaNac(request.getParameter("perFechaNac"));
					 gen.setGenId(Integer.parseInt(request.getParameter("perGenId")));
					 persona.setPerGenId(gen);
					 dep.setDepId(Integer.parseInt(request.getParameter("perDepId")));
					 persona.setPerDepId(dep);
					 std.setEstId(Integer.parseInt(request.getParameter("perEstId")));
					 persona.setPerEstId(std);
					 persona.setPerMadre(request.getParameter("perMadre"));
					 persona.setPerPadre(request.getParameter("perPadre"));
					 persona.setPerEstado(Integer.parseInt(request.getParameter("perEstado")));
					 JSONObject object = new JSONObject(persona);
					object = new JSONObject(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/persona",object, "POST"));
					Integer resp = Integer.parseInt(object.get("response").toString());
						request.setAttribute("msj",resp);
				 }
				
				if(request.getParameter("id") == null) {
					perList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/persona", "GET"),Persona[].class);
					request.setAttribute("perList", perList);
					genList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/genero", "GET"),Genero[].class);
					request.setAttribute("genList", genList);
					depList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/departamento", "GET"),Departamento[].class);
					request.setAttribute("depList", depList);
					stdList = ClientWebService.stringToArray(new ClientWebService().clienteWS("http://localhost:8080/utec_voting_system_webservice/service/Estado_Familiar", "GET"),EstadoFamiliar[].class);
					request.setAttribute("stdList", stdList);
					res=request.getRequestDispatcher("mtmPersona.jsp");
					res.forward(request, response);
				} else {
					for (Persona p : perList) {
						if(p.getPerDui().equals(request.getParameter("id"))) {
							personaSelected = new Persona(p.getPerDui(),
									p.getPerPNombre(),
									p.getPerSNombre(),
									p.getPerTNombre(),
									p.getPerPApellido(),
									p.getPerSApellido(),
									p.getPerFechaNac(),
									p.getPerEdad(),
									p.getPerGenId(),
									p.getPerDepId(),
									p.getPerEstId(),
									p.getPerMadre(),
									p.getPerPadre(),
									p.getPerEstado());
						}
					}
					String jsonArray = gson.toJson(personaSelected);
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
	 * @return the perList
	 */
	public List<Persona> getPerList() {
		return perList;
	}

	/**
	 * @param perList the perList to set
	 */
	public void setPerList(List<Persona> perList) {
		this.perList = perList;
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

	/**
	 * @return the depList
	 */
	public List<Departamento> getDepList() {
		return depList;
	}

	/**
	 * @param depList the depList to set
	 */
	public void setDepList(List<Departamento> depList) {
		this.depList = depList;
	}

	/**
	 * @return the stdList
	 */
	public List<EstadoFamiliar> getStdList() {
		return stdList;
	}

	/**
	 * @param stdList the stdList to set
	 */
	public void setStdList(List<EstadoFamiliar> stdList) {
		this.stdList = stdList;
	}
}
