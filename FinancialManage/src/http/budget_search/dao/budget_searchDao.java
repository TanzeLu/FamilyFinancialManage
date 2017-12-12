package http.budget_search.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import http.budget_search.service.Budget_searchService;
import http.utils.JdbcUtils;

public class budget_searchDao implements Budget_searchService {

	public List<Map<String, Object>> budget_search(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		String sql="select * from budgetinfo where userid=?";
		try {
			result=utils.findMoreResult(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
