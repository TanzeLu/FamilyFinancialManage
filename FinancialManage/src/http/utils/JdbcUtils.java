package http.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtils {
	//定义数据库的用户名
	private final String USERNAME="root";
	//定义数据库的密码
	private final String PASSWORD="122026";
	//定义数据库的驱动
	private final String DRIVER="com.mysql.jdbc.Driver";
	//定义访问数据库的地址
    private final String URL="jdbc:mysql://localhost:3306/Financialdb";
    //定义数据库的连接
    private Connection connection;
    //定义sql语句的执行对象
    private PreparedStatement pstmt;
    //定义结果集合
    private ResultSet resultSet;

	public JdbcUtils() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(DRIVER);
			//System.out.println("注册驱动成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//定义获得数据库的连接
	public Connection getConnection(){
		try {
			connection=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	//
	public boolean updateByPreparedStatement(String sql,List<Object> params){
		boolean flag=false;
		int result=-1;//表示当用户执行语句所影响数据库的行数
		try {
			pstmt=connection.prepareStatement(sql);
			int index=1;
			if(params!=null&&!params.isEmpty()){
				for(int i=0;i<params.size();i++){
					pstmt.setObject(index++, params.get(i));
				}
			}
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag=result>0?true:false;
		return flag;
	}
	
	//查询返回单条记录
	public Map<String,Object> findSimpleResult(String sql,List<Object> params) throws SQLException{
		Map<String,Object> map=new HashMap<String,Object>();
		int index=1;
		pstmt=connection.prepareStatement(sql);
		if(params!=null&&!params.isEmpty()){
			for(int i=0;i<params.size();i++){
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet=pstmt.executeQuery();//返回查询结果
		ResultSetMetaData metadata=resultSet.getMetaData();
		int col_len=metadata.getColumnCount();//获得列的长度
		while(resultSet.next()){
			for(int i=0;i<col_len;i++){
				String cols_name=metadata.getColumnName(i+1);
				Object cols_value=resultSet.getObject(cols_name);
				if(cols_value==null){
					cols_value="";
				}
				map.put(cols_name, cols_value);
			}
		}
		System.out.println(map);
		return map;
	}
	
	//显示查询结果
	public List<Map<String, Object>> showresult(String sql) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		pstmt = connection.prepareStatement(sql);
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i=0; i<cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}
	//查询返回多条记录
	public List<Map<String, Object>> findMoreResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i=0; i<params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i=0; i<cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}
	
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
