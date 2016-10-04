package util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class DbUtil {

	// 创建连接池
	public static Vector<Connection> connectionpool = new Vector<Connection>();

	// 静态块
	static {
		Properties properties = new Properties();
		try {
			properties.load(DbUtil.class.getResourceAsStream("/db.properties"));
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			String driver = properties.getProperty("driver");

			Class.forName(driver);
			for (int i = 0; i < 10; i++) {
				Connection connection = DriverManager.getConnection(url,
						username, password);
				connectionpool.add(connection);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 取连接
	public static Connection getConnection() {
		Connection connection = connectionpool.get(0);
		connectionpool.remove(0);
		return connection;
	}

	// 释放连接
	public static void releaseconnection(Connection connection) {
		connectionpool.add(connection);
	}

	// 增删改
	public static int zsg(String sql, Object... p) {
		Connection connection = getConnection();

		int n = 0;
		//
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			// 将p里面的值赋值给外面
			if (p != null) {
				for (int i = 0; i < p.length; i++) {
					// 下标从1开始
					ps.setObject(i + 1, p[i]);
				}
			}
			// 执行sql语句
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseconnection(connection);
		}

		return n;
	}

	// 查询
	public static List query(Class c, String sql, Object... p) {
		List list = new ArrayList();
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			if (p != null) {
				for (int i = 0; i < p.length; i++) {
					ps.setObject(i + 1, p[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获得字段总数
			int n = rsmd.getColumnCount();
			while (rs.next()) {
				Object object = c.newInstance();
				for (int i = 1; i <= n; i++) {
					String fieldname = rsmd.getColumnName(i);
					Object value = rs.getObject(fieldname);
					Field field = c.getDeclaredField(fieldname);
					field.setAccessible(true); // 设置成可以更改
					field.set(object, value);
				}
				list.add(object);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseconnection(connection);
		}
		return list;
	}

	// 聚集查询
	public static int uniqueQuery(String sql, Object... p) {
		int n = 0;
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			if (p != null) {
				for (int i = 0; i < p.length; i++) {
					ps.setObject(i + 1, p[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			// 把结果集下移到有结果的行（即跳过表头）
			rs.next();
			n = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseconnection(connection);
		}

		return n;
	}

}
