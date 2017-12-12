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
	//�������ݿ���û���
	private final String USERNAME="root";
	//�������ݿ������
	private final String PASSWORD="122026";
	//�������ݿ������
	private final String DRIVER="com.mysql.jdbc.Driver";
	//����������ݿ�ĵ�ַ
    private final String URL="jdbc:mysql://localhost:3306/Financialdb";
    //�������ݿ������
    private Connection connection;
    //����sql����ִ�ж���
    private PreparedStatement pstmt;
    //����������
    private ResultSet resultSet;

	public JdbcUtils() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(DRIVER);
			//System.out.println("ע�������ɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//���������ݿ������
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
		int result=-1;//��ʾ���û�ִ�������Ӱ�����ݿ������
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
	
	//��ѯ���ص�����¼
	public Map<String,Object> findSimpleResult(String sql,List<Object> params) throws SQLException{
		Map<String,Object> map=new HashMap<String,Object>();
		int index=1;
		pstmt=connection.prepareStatement(sql);
		if(params!=null&&!params.isEmpty()){
			for(int i=0;i<params.size();i++){
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet=pstmt.executeQuery();//���ز�ѯ���
		ResultSetMetaData metadata=resultSet.getMetaData();
		int col_len=metadata.getColumnCount();//����еĳ���
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
	
	//��ʾ��ѯ���
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
	//��ѯ���ض�����¼
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
