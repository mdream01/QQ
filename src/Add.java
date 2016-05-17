

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class Add extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
	
		int x = 0;
		int y = 0;		
		int sum = 0;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		if(request.getMethod().equals("POST")){
			String _btn = request.getParameter("btn");
			String _x = request.getParameter("x");
			String _y = request.getParameter("y");
			String _sum = request.getParameter("sum");
			
			String btn = "add";
			
			if(_btn != null && !_btn.equals("")){
				btn = _btn;
			}
			
			if(_x != null && !_x.equals("")){
				x = Integer.parseInt(_x);
			}

				
			if(_y != null && !_y.equals("") ){
				y = Integer.parseInt(_y);
			}
			
			if(_sum != null && !_sum.equals("") ){
				sum = Integer.parseInt(_sum);
			}
			
			
			//--버튼에 따른 초리로직-------------------------------------------------
			
			
			if(btn.equals("application")){
				ServletContext application = request.getServletContext();
				application.setAttribute("sum", sum);
			}
			else if(btn.equals("session")){
				HttpSession session = request.getSession();
				session.setAttribute("sum", sum);
			}
			else if(btn.equals("cookie")){
				Cookie cookie = new Cookie("sum",String.valueOf(sum));
				cookie.setMaxAge(5*60);
				response.addCookie(cookie);
			}
			else{
				sum = x+y;
			}
		}
		
			
			PrintWriter out = response.getWriter();
			
			
			  out.write("<!DOCTYPE html>");
		      out.write("<html>");
		      out.write("<head>");
		      out.write("<meta charset=\"UTF-8\">");
		      out.write("<title>Insert title here</title>");
		      out.write("</head>");
		      out.write("<body>");
		      out.write("   <form action=\"add\" method=\"post\">");
		      out.write("      <ul>");
		      out.write("         <li>");
		      out.write("         <label for=\"x\" > X : </label>");
		      out.write("         <input name=\"x\" />");
		      out.write("         </li>");
		               
		      out.write("         <li>");
		      out.write("         <label for=\"y\" > Y : </label>");
		      out.write("         <input name=\"y\" />");
		      out.write("         </li>");
		      out.write("      </ul>");
		            
		      out.write("      <p>");
		      out.printf("     <input type=\"hidden\" name=\"sum\" value=\"%d\" />",sum);
		      out.write("      <input type=\"submit\" value=\"덧셈\" />");
		      out.write("      <input type=\"submit\" name=\"btn\" value=\"application\" />");
		      out.write("      <input type=\"submit\" name=\"btn\" value=\"session\" />");
		      out.write("      <input type=\"submit\" name=\"btn\" value=\"Cookie\" />");
		      out.write("      </p>");
		      out.write("   </form>");
		      out.println("<p> result : ");
		      out.println(x+y);
		      out.println("</p>");
		      out.println("<p><a href=\"mypage\">마이페이지</p>");
		      out.write("</body>");
		      out.write("</html>");

			
	}
}