package http.login.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import http.login.dao.LoginDao;
import http.login.service.Loginservice;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Loginservice service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException{
    	service=new LoginDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String result=null;
		String username=request.getParameter("username");
		//System.out.println("-username->"+sel_name);
		String password=request.getParameter("password");
		//System.out.println("-password->"+sel_pword);
		List<Object> params=new ArrayList<Object>();
		params.add(username);
		params.add(password);
	/*	List<Object> params=new ArrayList<Object>();
		params.add("qyn");
		params.add("122");*/
		result=service.Login(params);
		out.print(result);
		//out.println("hello");
		out.flush();
		out.close();
	}

}
