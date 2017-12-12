package http.budget_search.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import http.budget.dao.BudgetDao;
import http.budget_search.dao.budget_searchDao;
import http.budget_search.service.Budget_searchService;
import http.utils.JsonTools;

/**
 * Servlet implementation class budget_searchAction
 */
@WebServlet("/budget_searchAction")
public class budget_searchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Budget_searchService service;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public budget_searchAction() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		service=new budget_searchDao();
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
		String userid=request.getParameter("userid");
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		List<Map<String,Object>> result=service.budget_search(params);
		if(result.isEmpty())
			out.print("null");
		else{
			String jsonstring="";
			jsonstring=JsonTools.createJsonString("budg", result);
			out.print(jsonstring);
		}
			
		
	}

}
