package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ContatoDAO;
import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/Controller", "/main" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JavaBeans contato=new JavaBeans();
	DAO contatoDAO=new ContatoDAO();
    /**
     * Default constructor. 
     */
    public Controller() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<JavaBeans> contatos=new ArrayList<JavaBeans>();
		if(request.getParameter("id")==null) {//Pega Todos
			contatos=contatoDAO.recuperarContato();
		}else{//Pega apenas um
			preencher(request);
			contatos=contatoDAO.recuperarContato(contato);
		}
		request.setAttribute("contatos", contatos);
		RequestDispatcher rd=request.getRequestDispatcher("view.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preencher(request);
		contatoDAO.inserirContato(contato);
		response.sendRedirect("main");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preencher(getFields(request));
		contatoDAO.alterarContato(contato);
		response.sendRedirect("main");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preencher(getFields(request));
		contatoDAO.deletarContato(contato);
		response.sendRedirect("main");
	}
	/**
	 * @param request
	 * @return Hashmap<String,String> 
	 */
	private Map<String,String> getFields(HttpServletRequest request) {
		Map<String,String>campos=new HashMap<String,String>();
		BufferedReader br = null;
		try {
			InputStreamReader reader = new InputStreamReader(request.getInputStream());
	        br = new BufferedReader(reader);

	        String data = URLDecoder.decode(br.readLine(),"UTF-8");

	        String[] split=data.split("&");
	        
	        for(int i=0;i<split.length;i++) {
	        	String[] param=split[i].split("=");
	        	campos.put(param[0],param[1]);
	        }
	        return campos;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private void preencher(Map<String,String> data) {
		contato.setNome(data.get("nome"));
		contato.setFone(data.get("fone"));
		contato.setEmail(data.get("email"));
		contato.setId(data.get("id"));
	}
	
	private void preencher(HttpServletRequest request) {
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		contato.setId(request.getParameter("id"));
	}
}
