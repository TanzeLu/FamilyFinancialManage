package http.addrecord.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import http.addrecord.dao.Addrecord;
import http.addrecord.service.AddrecordService;

/**
 * Servlet implementation class AddrecordAction
 */
@WebServlet("/AddrecordAction")
public class AddrecordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddrecordService service;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddrecordAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		service=new  Addrecord();
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
		boolean result=false;
		String userid=request.getParameter("userid");
		//System.out.println("-username->"+sel_name);
		String type=request.getParameter("type");
		//System.out.println("-password->"+sel_pword);
		String divide=request.getParameter("divide");
		String num=request.getParameter("num");
		String about=request.getParameter("about");
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(type);
		params.add(Integer.parseInt(divide));
		params.add(Integer.parseInt(num));
		params.add(about);
		
	/*	List<Object> params=new ArrayList<Object>();
		params.add("qyn");
		params.add("学费");
		params.add(0);
		params.add(200);
		params.add("第一学期学费");*/
		result=service.Addrecord(params);
		if(result)
			out.print("添加记录成功！");
		else
			out.println("添加记录失败！");
		
		out.flush();
	    out.close();
	}

}
