import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage")
public class MyPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*super.doGet(req, resp);*/
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		
		//쿠키를 읽어오는 로직
		Cookie[] cookies = request.getCookies();
		int sum = 0;
		if(cookies != null){
			for(Cookie cookie : cookies){
				if("sum".equals(cookie.getName())){
					sum = Integer.parseInt(cookie.getValue());
				}
			}
		}
		
		out.printf("application : %d <br/>",application.getAttribute("sum"));
		out.printf("session : %d <br/>",session.getAttribute("sum"));
/*		out.write("session : 0 <br/>");*/
		out.printf("cookies : %d <br/>",sum);
		
		out.write("<a href=\"add\">계산하기</a>");
/*		out.write("<input type=\"button\" name=\"add\">계산하기</a>");*/
	}
	
	
	

}
