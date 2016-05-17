import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/hello")
public class Nana extends HttpServlet{
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
/*		OutputStream os = response.getOutputStream();
		PrintStream out = new PrintStream(os);*/
		
		response.setCharacterEncoding("UTF-8");//서버에 제공
		response.setContentType("text/html); charset=UTF-8");//사용자에게 제공
		
		PrintWriter out = response.getWriter();
		
		String _cnt = request.getParameter("cnt");
		int cnt = 100;
		if(_cnt != null && !_cnt.equals("")){ //빈문자열경우에 따른 조건도 만들어 줘야함
			cnt = Integer.parseInt(_cnt);
		}
		
		for(int i=0; i<100; i++){
			/*out.println((i+1)+": Hello-Servlet!!<br/>");*/
			out.println((i+1)+": 안녕 Servlet!!<br/>");
		}
		
		

		   /* 1.자바 콘솔 프로그램과 자바 웹 프로그램의 차이점은? 콘솔에서 웹으로 입/출력 방법이 달라짐
			  2.자바 웹 프로그램은 서버와 클라이언트로 나누어졌는데 자바로 작성하는 서버 프로그램의 특징은? 서버프로그램의 조각을 서블릿이라고 한다.
			  3.그렇다면 조각이 난 프로그램들이 서로 어떤 값을 공유할 일이 있으면 어떻게 해야 할까요? 각 서블릿들이 값을 공유하는 방법? 
		   */
	}
}
