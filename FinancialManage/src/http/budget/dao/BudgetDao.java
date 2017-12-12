package http.budget.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import http.budget.service.BudgetService;
import http.utils.JdbcUtils;

public class BudgetDao implements BudgetService {

	@Override
	public boolean AddBudget(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		String sql="select * from budgetinfo  where userid='"+params.get(5)+"'";
		Map<String, Object> map=new HashMap<String,Object>();
		try {
			map = utils.findSimpleResult(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map.isEmpty()){
			String sql2="insert into budgetinfo(Ex_budget,Edu_budget,Live_budget,shop_budget,travel_budget,userid) values(?,?,?,?,?,?)";
			flag=utils.updateByPreparedStatement(sql2, params);
		}else{
			String sql3="update budgetinfo set Ex_budget=?,Edu_budget=?,Live_budget=?,shop_budget=?,travel_budget=? where userid=?";
			flag=utils.updateByPreparedStatement(sql3, params);
		}
		return flag;
	}

}
