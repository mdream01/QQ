import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.answeris.web.model.Notice;


@WebServlet("/customer/notice-detail")
public class NoticeDetailController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//컨트롤러(Controller)
		String c = request.getParameter("c");
				
      	String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
      	String sql = "SELECT * FROM NOTICE WHERE CODE='"+c+"'";
      	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
	        Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
	      	Statement st = con.createStatement();
	      	ResultSet rs = st.executeQuery(sql);

			
/*	       	String code="";
	      	String title="";
	        String writer="";
	        String content="";
	      	Date regdate = null;
	      	int hit=0;	*/
	      	
	      	Notice n = new Notice();
	      	
	      if(rs.next()){
/*	    	  code=rs.getString("CODE");
	    	  title=rs.getString("TITLE");
	    	  regdate=rs.getDate("REGDATE");
	    	  content=rs.getString("CONTENT");
	    	  writer=rs.getString("WRITER");
	    	  hit=rs.getInt("HIT");*/
	    	  
	    	  n.setCode(rs.getString("CODE"));
	    	  n.setTitle(rs.getString("TITLE"));
	    	  n.setRegdate(rs.getDate("REGDATE"));
	    	  n.setWriter(rs.getString("writer"));
	    	  n.setContent(rs.getString("CONTENT"));    	  
	    	  n.setHit(rs.getInt("HIT"));    	  
	      }
	      rs.close();
	      st.close();
	      con.close();
/*	      
	      request.setAttribute("code",n.get);
	      request.setAttribute("title",title);
	      request.setAttribute("regdate",regdate);
	      request.setAttribute("writer",writer);
	      request.setAttribute("content",content);
	      request.setAttribute("hit",hit);*/
	      request.setAttribute("n",n);
	      
	      RequestDispatcher dispatcher = request.getRequestDispatcher("notice-detail.jsp");
	      dispatcher.forward(request, response);
	      
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
      
	}
	
}
