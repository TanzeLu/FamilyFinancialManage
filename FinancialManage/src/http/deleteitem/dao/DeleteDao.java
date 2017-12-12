package http.deleteitem.dao;

import http.deleteitem.service.DeleteService;
import http.utils.JdbcUtils;

public class DeleteDao implements DeleteService {

	@Override
	public boolean DeleteItem(String id) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JdbcUtils utils=new JdbcUtils();
		utils.getConnection();
		String sql="delete from financialinfo where id='"+id+"'";
		flag=utils.updateByPreparedStatement(sql, null);
		return flag;
	}

}
