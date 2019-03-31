package cartoon.util;

import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;

public class JdbcUtil {
	private static final String drive = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://rm-2zes33v518q658og8.mysql.rds.aliyuncs.com:3306/yzf_test";
	private static final String userName = "root";
	private static final String password = "Y154589424!";
	// ����������ݿ������  
    private Connection connection; 
 // ����sql����ִ�ж���  
    private PreparedStatement pstmt;  
    // �����ѯ���صĽ������  
    private ResultSet resultSet; 
	
	public JdbcUtil() {  
        try {  
            Class.forName(drive);  
            System.out.println("ע�������ɹ�����");  
        } catch (ClassNotFoundException e) {   
            System.out.println("ע������ʧ�ܣ���");  
        }  
  
    }  
	
	// ���������ݿ������  
    public Connection getConnection() {  
  
        try {  
            connection = (Connection) DriverManager.getConnection(url, userName, password);  
  
        } catch (Exception e) {  
            System.out.println("Connection exception !");  
        }  
  
        return connection;  
  
    }  
	

    /** 
     * ��ɶ����ݿ�������ɾ�����޸ĵĲ��� 
     *  
     * @param sql 
     * @param params 
     * @return 
     * @throws SQLException 
     */  
    public boolean updateByPreparedStatement(String sql, List<Object> params)  
            throws SQLException {  
        boolean flag = false;  
        int result = -1;// ��ʾ���û�ִ������ɾ�����޸ĵĲ���Ӱ�������  
        int index = 1; // ��ʾ ռλ�� ����1��ʼ  
        pstmt = connection.prepareStatement(sql);  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i)); // ���ռλ��  
            }  
        }  
  
        result = pstmt.executeUpdate();  
        flag = result > 0 ? true : false;  
        return flag;  
  
    }  
  
    /** 
     * ��ѯ���ص�����¼ 
     *  
     * @param sql 
     * @param params 
     * @return 
     * @throws SQLException 
     */  
    public  Map<String, Object> findSimpleResult(String sql, List<Object> params)  
            throws SQLException {  
        Map<String, Object> map = new HashMap<String, Object>();  
        pstmt = connection.prepareStatement(sql);  
        int index = 1;  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        resultSet = pstmt.executeQuery(); // ���ز�ѯ���  
  
        ResultSetMetaData metaData = pstmt.getMetaData(); // ��ȡ ����У�һ�������еĽ��  
        int cols_len = metaData.getColumnCount(); // ����е�����  
  
        while (resultSet.next()) {  
            for (int i = 0; i < cols_len; i++) {  
                String col_name = metaData.getColumnName(i + 1); // ��õ�i�е��ֶ�����  
                Object col_value = resultSet.getObject(col_name);// ���� ��i�е�����ֵ  
                if (col_value == null) {  
                    col_value = "";  
                }  
                map.put(col_name, col_value);  
            }  
  
        }  
  
        return map;  
    }  
  
    /** 
     * ��ѯ���ض�����¼ 
     *  
     * @param sql 
     * @param params 
     * @return 
     * @throws SQLException 
     */  
    public List<Map<String, Object>> findMoreResult(String sql,  
            List<Object> params) throws SQLException {  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        pstmt = connection.prepareStatement(sql);  
        int index = 1; // ��ʾռλ��  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        resultSet = pstmt.executeQuery(); // ���ز�ѯ�������  
        ResultSetMetaData metaData = resultSet.getMetaData(); // ����еĽ��  
  
        while (resultSet.next()) {  
            Map<String, Object> map = new HashMap<String, Object>();  
            int cols_len = metaData.getColumnCount(); // ��ȡ�ܵ�����  
            for (int i = 0; i < cols_len; i++) {  
                String col_name = metaData.getColumnName(i + 1); // ��ȡ�� i�е��ֶ�����  
                                                                    // ,�м����1��ʼ  
                Object col_value = resultSet.getObject(col_name); // ��ȡ��i�е�����ֵ  
                if (col_value == null) {  
                    col_value = "";  
                }  
  
                map.put(col_name, col_value);  
            }  
            list.add(map);  
        }  
  
        return list;  
  
    }  
  
    /** 
     * ��ѯ���ص���JavaBean(ʹ��java�������) 
     *  
     * @param sql 
     * @param params 
     * @param cls 
     * @return 
     * @throws Exception 
     */  
    public <T> T findSimpleRefResult(String sql, List<Object> params,  
            Class<T> cls) throws Exception {  
        T resultObject = null;  
        int index = 1; // ռλ��  
        pstmt = connection.prepareStatement(sql);  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i)); // ���ռλ��  
            }  
        }  
        resultSet = pstmt.executeQuery(); // ��ȡ��ѯ���  
  
        ResultSetMetaData metaData = resultSet.getMetaData(); // ��ȡ�е���Ϣ  
        int cols_len = metaData.getColumnCount(); // ��ȡ�ܵ�����  
        while (resultSet.next()) {  
            // ͨ��������ƴ���ʵ��  
            resultObject = cls.newInstance(); // java�������  
            for (int i = 0; i < cols_len; i++) {  
                String col_name = metaData.getColumnName(i + 1); // ��ȡ��i�е�����  
                Object col_value = resultSet.getObject(col_name); // ��ȡ��i�е�ֵ  
                if (col_value == null) {  
                    col_value = "";  
                }  
                Field field = cls.getDeclaredField(col_name);  
                field.setAccessible(true);// �� JavaBean�ķ��� privateȨ��  
                field.set(resultObject, col_value);  
            }  
  
        }  
  
        return resultObject;  
    }  
  
    /** ��ѯ���ض��JavaBean(ͨ��java�������) 
     * @param sql 
     * @param params 
     * @param cls 
     * @return 
     * @throws Exception 
     */  
    public <T> List<T> findMoreRefResult(String sql, List<Object> params,  
            Class<T> cls) throws Exception {  
        List<T> list = new ArrayList<T>();  
        int index = 1; //ռλ��  
        pstmt = connection.prepareStatement(sql);  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        resultSet = pstmt.executeQuery(); // ���ز�ѯ�������  
  
        ResultSetMetaData metaData = resultSet.getMetaData(); // �����е���Ϣ  
        int cols_len = metaData.getColumnCount(); // ��������ܵ�����  
        while (resultSet.next()) {  
            // ͨ��������ƴ���һ��javaʵ��  
            T resultObject = cls.newInstance();  
            for (int i = 0; i < cols_len; i++) {  
                String col_name = metaData.getColumnName(i + 1); // ��õ�i�е�����  
                Object col_value = resultSet.getObject(col_name); // ��õ�i�е�����  
                if (col_value == null) {  
                    col_value = "";  
                }  
                Field field = cls.getDeclaredField(col_name);  
                field.setAccessible(true); // ��JavaBean�ķ���privateȨ��  
                field.set(resultObject, col_value);  
            }  
            list.add(resultObject);  
  
        }  
  
        return list;  
    }  
      
    /**�ر����ݿ���� 
     * @throws SQLException 
     */  
    public void releaseConn() throws SQLException{  
        if (resultSet!=null) {  
            resultSet.close();  
        }  
        if (pstmt!=null) {  
            pstmt.close();  
        }  
        if (connection!=null) {  
            connection.close();  
        }  
    }

}