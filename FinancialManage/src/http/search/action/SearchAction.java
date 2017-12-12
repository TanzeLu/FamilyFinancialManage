package http.search.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import http.search.dao.SearchDao;
import http.search.service.SearchService;
import http.utils.JsonTools;

/**
 * Servlet implementation class SearchAction
 */
@WebServlet("/SearchAction")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SearchService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
    	service=new SearchDao();
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
		String date=request.getParameter("date");
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(date);
	    
	/*	List<Object> params=new ArrayList<Object>();
		params.add("qyn");
		params.add("2016-11");*/
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=service.Search(params);
		Map<String,Object> map=new HashMap<String,Object>();
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		for(Map m:list){
			try {
				Date d=myfmt.parse(m.get("Time").toString());
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				String dd=c.get(Calendar.YEAR)+"-"+String.valueOf(c.get(Calendar.MONTH)+1)+"-"+String.valueOf(c.get(Calendar.DAY_OF_MONTH));
				m.put("date",dd);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String jsonString="";
		jsonString=JsonTools.createJsonString("record",list);
		//out.print(list);
		out.print(jsonString);
		out.flush();
		out.close();
	}

}
