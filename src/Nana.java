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
		
		response.setCharacterEncoding("UTF-8");//������ ����
		response.setContentType("text/html); charset=UTF-8");//����ڿ��� ����
		
		PrintWriter out = response.getWriter();
		
		String _cnt = request.getParameter("cnt");
		int cnt = 100;
		if(_cnt != null && !_cnt.equals("")){ //���ڿ���쿡 ���� ���ǵ� ����� �����
			cnt = Integer.parseInt(_cnt);
		}
		
		for(int i=0; i<100; i++){
			/*out.println((i+1)+": Hello-Servlet!!<br/>");*/
			out.println((i+1)+": �ȳ� Servlet!!<br/>");
		}
		
		

		   /* 1.�ڹ� �ܼ� ���α׷��� �ڹ� �� ���α׷��� ��������? �ֿܼ��� ������ ��/��� ����� �޶���
			  2.�ڹ� �� ���α׷��� ������ Ŭ���̾�Ʈ�� ���������µ� �ڹٷ� �ۼ��ϴ� ���� ���α׷��� Ư¡��? �������α׷��� ������ �����̶�� �Ѵ�.
			  3.�׷��ٸ� ������ �� ���α׷����� ���� � ���� ������ ���� ������ ��� �ؾ� �ұ��? �� �������� ���� �����ϴ� ���? 
		   */
	}
}
