package http.login.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import http.login.service.Loginservice;
import http.utils.JdbcUtils;

public class LoginDao implements Loginservice {

	@Override
	public String Login(List<Object> params) {
		// TODO Auto-generated method stub
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		String result=null;
		String sql="select * from UserInfo where username=? and password=?";
		try {
			Map<String,Object> list=new HashMap<String,Object>();
			list=utils.findSimpleResult(sql, params);
			if(list!=null&&!list.isEmpty()){
				result="登录成功";
			}else{
				String sql2="select * from UserInfo where username='"+params.get(0)+"'";
				list=utils.findSimpleResult(sql2, null);
				if(list!=null&&!list.isEmpty())
					result="密码错误";
				else
					result="用户名不存在";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

}
