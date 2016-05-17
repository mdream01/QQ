import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/noticeEdit")
public class NoticeEditController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse responese) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*super.doPost(req, resp);*/
		
		if(request.getMethod()=="POST"){
			
		 	String code="";
		 	String writer="aaaa";
		/* 	String code =request.getParameter("code"); */
		/*  	String writer = request.getParameter("writer"); */
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
		  	String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		  	Class.forName("oracle.jdbc.driver.OracleDriver");
		  	
		    Connection con = DriverManager.getConnection(url, "c##sist", "dclass");/* 하나만  만들어줘야 한다 */
		    
		/*     con.setAutoCommit(false); */
		    
		/* -----------CODE 생성--------------------- */
			String sqlCode = "SELECT MAX(TO_NUMBER(CODE)+1) AS CODE FROM NOTICE";
			Statement st = con.createStatement(); /* 각각 만들어줘야 한다 */
			ResultSet rs = st.executeQuery(sqlCode);
			rs.next();
			code = rs.getString("code");

		/* ---------게시글 등록--------------- */
		 	String sql = "UPDATE NOTICE SET TITLE=?,CONTENT=? WHERE CODE = ?";
			PreparedStatement pst = con.prepareStatement(sql); /* 각각 만들어줘야 한다 */

			pst.setString(1,title);
			pst.setString(2,content);
			pst.setString(3,code);
			
			pst.executeUpdate();

		   /*  con.commit(); */
		    
		/*    response.sendRedirect("notice-Detail.jsp?c="+code);*/
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//컨트롤러(Controller)
/*		String c = request.getParameter("c");
				
      	String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
      	String sql = "SELECT * FROM NOTICE WHERE CODE='"+c+"'";
      	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
	        Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
	      	Statement st = con.createStatement();
	      	ResultSet rs = st.executeQuery(sql);

			
	       	String code="";
	      	String title="";
	        String writer="";
	        String content="";
	      	Date regdate = null;
	      	int hit=0;	
	      	
	      if(rs.next()){
	    	  code=rs.getString("CODE");
	    	  title=rs.getString("TITLE");
	    	  regdate=rs.getDate("REGDATE");
	    	  content=rs.getString("CONTENT");
	    	  writer=rs.getString("WRITER");
	    	  hit=rs.getInt("HIT");
	      }
	      rs.close();
	      st.close();
	      con.close();
	      
	      request.setAttribute("code",code);
	      request.setAttribute("title",title);
	      request.setAttribute("regdate",regdate);
	      request.setAttribute("writer",writer);
	      request.setAttribute("content",content);
	      request.setAttribute("hit",hit);
	      
	      RequestDispatcher dispatcher = request.getRequestDispatcher("notice-detail.jsp");
	      dispatcher.forward(request, response);
	      
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}*/
		
		if(request.getMethod()=="POST"){
			
			String c = request.getParameter("c");
			String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		    Connection con = DriverManager.getConnection(url, "c##sist", "dclass");/* 하나만  만들어줘야 한다 */
		    
		 	String code="";
		 	String title="";
		 	String writer="";
		 	String content="";
		    
			/* -----------CODE 생성--------------------- */
			String sqlCode = "SELECT MAX(TO_NUMBER(CODE)+1) AS CODE FROM NOTICE";
			
			/* ---------게시글 등록--------------- */
		 	String sql = "UPDATE NOTICE SET TITLE='"+title+"' WRITER='"+writer+"' CONTENT='"+content+
						"' WHERE CODE = '"+code+"'";
			
		/* 	String code =request.getParameter("code"); */
		/*  	String writer = request.getParameter("writer"); */
/*			String title = request.getParameter("title");*/
/*			String content = request.getParameter("content");*/
			
/*		  	String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";*/
		  	Class.forName("oracle.jdbc.driver.OracleDriver");
		  	
/*		    Connection con = DriverManager.getConnection(url, "c##sist", "dclass"); 하나만  만들어줘야 한다 */
		    
		    con.setAutoCommit(false);
		    
		/* -----------CODE 생성--------------------- */
			Statement st = con.createStatement(); /* 각각 만들어줘야 한다 */
			ResultSet rs = st.executeQuery(sqlCode);
			rs.next();
			code = rs.getString("code");

			PreparedStatement pst = con.prepareStatement(sql); /* 각각 만들어줘야 한다 */

			pst.setString(1,title);
			pst.setString(2,writer);
			pst.setString(3,content);
			
			pst.executeUpdate();

		    con.commit();
		    
		    response.sendRedirect("noticeDetail.jsp");
		}
      
	}
	
}

}
