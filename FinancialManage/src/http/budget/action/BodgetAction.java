package http.budget.action;

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

import http.budget.dao.BudgetDao;
import http.budget.service.BudgetService;

/**
 * Servlet implementation class BodgetAction
 */
@WebServlet("/BodgetAction")
public class BodgetAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BudgetService service;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BodgetAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		service=new BudgetDao();
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
		String Ex_budget=request.getParameter("Ex_budget");
		String Edu_budget=request.getParameter("Edu_budget");
		String Live_budget=request.getParameter("Live_budget");
		String shop_budget=request.getParameter("shop_budget");
		String travel_budget=request.getParameter("travel_budget");
		String userid=request.getParameter("userid");
		List<Object> params=new ArrayList<Object>();
		params.add(Integer.parseInt(Ex_budget));
		params.add(Integer.parseInt(Edu_budget));
		params.add(Integer.parseInt(Live_budget));
		params.add(Integer.parseInt(shop_budget));
		params.add(Integer.parseInt(travel_budget));
		params.add(userid);
		
	/*	List<Object> params=new ArrayList<Object>();
		params.add(200);
		params.add(200);
		params.add(200);
		params.add(200);
		params.add(200);
		params.add("qyn");*/
		boolean result=service.AddBudget(params);
		if(result)
			out.println("修改成功");
		else
			out.print("修改失败");
	}

}
