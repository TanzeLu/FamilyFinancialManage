package http.register.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import http.register.dao.RegisterDao;
import http.register.service.RegisterService;

/**
 * Servlet implementation class RegisterAction
 */
@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       RegisterService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAction() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
    	service=new RegisterDao();
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
		String tel=request.getParameter("tel");
		List<Object> params=new ArrayList<Object>();
		params.add(username);
		params.add(password);
		params.add(tel);
		
	/*	List<Object> params=new ArrayList<Object>();
		params.add("lj");
		params.add("111");
		params.add("13688363621");
		System.out.println(params.get(0));*/
		result=service.Register(params);
		out.print(result);
		
		out.flush();
	    out.close();
	}

}
