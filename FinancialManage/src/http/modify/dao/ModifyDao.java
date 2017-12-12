package http.modify.dao;

import java.util.List;

import http.modify.service.Modifyservice;
import http.utils.JdbcUtils;

public class ModifyDao implements Modifyservice {

	public boolean Modifyrecord(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		System.out.println(params);
		String sql="update financialinfo set type=?,Divide=?,num=?,about=? where id=?";
		flag=utils.updateByPreparedStatement(sql, params);
		return flag;
	}

}
