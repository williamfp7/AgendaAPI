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
import model.JavaBeansFactory;


@WebServlet({ "/Controller", "/main" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//JavaBeans contato=new JavaBeans();
	JavaBeansFactory factory=new JavaBeansFactory();
	DAO contatoDAO=new ContatoDAO();
	//DAO contatoDAO=new FakeDAO();//Dependence injection
    public Controller() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<JavaBeans> contatos=new ArrayList<JavaBeans>();
		if(request.getParameter("id")==null) {//Pega Todos
			contatos=contatoDAO.recuperarContato();
		}else{//Pega apenas um
			preencher(request);
			contatos=contatoDAO.recuperarContato(factory.create());
		}
		request.setAttribute("contatos", contatos);
		RequestDispatcher rd=request.getRequestDispatcher("view.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contatoDAO.inserirContato(preencher(request));
		response.sendRedirect("main");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contatoDAO.alterarContato(preencher(getFields(request)));
		response.sendRedirect("main");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contatoDAO.deletarContato(preencher(getFields(request)));
		response.sendRedirect("main");
	}

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
	
	private JavaBeans preencher(Map<String,String> data) {
		return factory.create(data.get("nome"),data.get("fone"),data.get("email"),data.get("id"));
	}
	
	private JavaBeans preencher(HttpServletRequest request) {
		return factory.create(request.getParameter("nome"),request.getParameter("fone"),request.getParameter("email"),request.getParameter("id"));
	}
}
