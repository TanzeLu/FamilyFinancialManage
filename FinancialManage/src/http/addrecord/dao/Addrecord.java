package http.addrecord.dao;

import java.util.List;

import http.addrecord.service.AddrecordService;
import http.utils.JdbcUtils;

public class Addrecord implements AddrecordService {

	@Override
	public boolean Addrecord(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		String sql="insert into financialinfo(userid,Time,type,Divide,num,about) values(?,now(),?,?,?,?)";
		flag=utils.updateByPreparedStatement(sql, params);
		return flag;
	}

}
