package http.register.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import http.register.service.RegisterService;
import http.utils.JdbcUtils;

public class RegisterDao implements RegisterService {

	public String Register(List<Object> params) {
		// TODO Auto-generated method stub
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		String result=null;
		String sql="insert into UserInfo(username,password,tel) values(?,?,?)";
		Map<String,Object> list=new HashMap<String,Object>();
		String sql2="select * from UserInfo where username='"+params.get(0)+"';";
		try {
			list=utils.findSimpleResult(sql2, null);
			if(list.isEmpty()){
				boolean flag=utils.updateByPreparedStatement(sql, params);
				if(flag){
					result="ע��ɹ�";
				}else
					result="ע��ʧ��";
			}
			else
				result="�û����Ѵ��ڣ�";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
