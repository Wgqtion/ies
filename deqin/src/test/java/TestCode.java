import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

public class TestCode {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		PreparedStatement  sql;	
		ResultSet rs;

		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动  

		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=wlbx_2015_v51"; //连接服务器和数据库sample  

		String userName = "sa"; //默认用户名  

		String userPwd = "111111"; //密码  

		Connection dbConn;
  
		String xmls= FileUtils.readFileToString(new File("Z:/xxxx.xml"));
		System.out.println(xmls);
		try {

			Class.forName(driverName);

			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);

			sql = dbConn.prepareStatement("update   EDOC_FORM set CONTENT=? where id=6184614307311078429");
			sql.setString(1,xmls);
			 
			int re= sql.executeUpdate();
			System.out.println(re);
			System.out.println("Connection Successful!"); //如果连接成功 控制台输出Connection Successful! 
//int n=0;
//			while (rs.next())
//
//			{
//				
//				sql.executeQuery("update   EDOC_FORM set CONTENT=? where id=-1257142185204563822");
//				System.out.println(n++);
//			}

			dbConn.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
