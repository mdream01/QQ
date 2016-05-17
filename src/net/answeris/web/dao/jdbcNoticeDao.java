package net.answeris.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.answeris.web.model.Notice;

public class jdbcNoticeDao implements NoticeDao {


	public List<Notice> getList(String field, String query, int page){
		
		//page = 1 , start=1,end=10
		//page = 2 ,start=11,end = 20
		//page = 3 , start=21, end = 30
		
		int pageSize = 10;
		int start = 1 +(page-1)*10;
		int end = page*10;
		
/*		String sql = "SELECT * FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC)"
						+ "WHERE ROWNUM BETWEEN 1 AND 10";*/
		
/*		String sql = "SELECT * FROM NOTICE_VIEW "
				+ "WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ?";*/
		
		String sql = "SELECT * FROM (SELECT ROWNUM NUM,NOTICE_VIEW.* FROM NOTICE_VIEW WHERE "+field+" LIKE '%?%') "
				+ "WHERE NUM BETWEEN ? AND ?";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,"%"+query+"%");
			st.setInt(2,start);
			st.setInt(3,end);
			
			ResultSet rs = st.executeQuery();
			
			/*ResultSet rs = st.executeQuery(sql);*/
		
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
	@Override
	public List<Notice> getList() {
		// TODO Auto-generated method stub	
		return getList(1);
	}

	@Override
	public List<Notice> getList(int page) {
		// TODO Auto-generated method stub		
		/*this.page = page;*/		
		return getList("TITLE","",page);
	}
	@Override
	public Notice get(String code) {
		// TODO Auto-generated method stub	
		String sql = "";
		return null;
	}
	@Override
	public Notice getNext(String code) {
		// TODO Auto-generated method stub
		
		String sql = "";
		return null;
	}
	@Override
	public Notice getPrev(String code) {
		// TODO Auto-generated method stub
		String sql = "";
		return null;
	}
	@Override
	public int add(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		return 0;
	}
}
	
