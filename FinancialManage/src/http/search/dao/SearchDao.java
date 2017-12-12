package http.search.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import http.search.service.SearchService;
import http.utils.JdbcUtils;

public class SearchDao implements SearchService {

	@Override
	public List<Map<String, Object>> Search(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		String sql="select * from financialinfo where userid=? and date_format(Time,'%Y-%m')=?";
		try {
			result=utils.findMoreResult(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
