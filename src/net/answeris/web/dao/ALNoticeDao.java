package net.answeris.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.answeris.web.model.Notice;

public class ALNoticeDao {

	public List<Notice> getNotices(){
		
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
/*		String sql = "SELECT * FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC)"
						+ "WHERE ROWNUM BETWEEN 1 AND 10";*/
		
		String sql = "SELECT * FROM NOTICE_VIEW";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		
/*			String code;
			String title;
			String writer;
			Date regdate;
			int hit;*/
			
			List<Notice> list = new ArrayList<>();


				while(rs.next()){
				
				Notice n = new Notice();
				
				n.setCode(rs.getString("CODE"));
				n.setTitle(rs.getString("TITLE"));
				n.setRegdate(rs.getDate("REGDATE"));
				n.setWriter(rs.getString("WRITER"));
				n.setHit(rs.getInt("hit"));
				n.setWriterName(rs.getString("WRITER_NAME"));
				n.setCmtCnt(rs.getInt("CMT_CNT"));
				
				list.add(n);
				}
				
				rs.close();
				st.close();
				con.close();
				
				return list;	
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
	
