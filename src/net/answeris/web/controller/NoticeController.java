package net.answeris.web.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.answeris.web.dao.ALNoticeDao;
import net.answeris.web.dao.jdbcNoticeDao;
import net.answeris.web.model.Notice;

@WebServlet("/customer/notice")
public class NoticeController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		
		String _page = request.getParameter("pg");
		String _field = request.getParameter("f");
		String _query = request.getParameter("q");
		
		int page=1;
		String field="TITLE";
		String query="";
		
		if(_page != null && !_page.equals("")){
			page = Integer.parseInt(_page);
		}
		
		if(_field != null && !_field.equals("")){
			field =_field;
		}
		
		if(_query != null && !_query.equals("")){
			query =_query;
		}
		
		List<Notice> list = new jdbcNoticeDao().getNotices(field,query,page);
					
		request.setAttribute("list", list);
					
		RequestDispatcher dispatcher = request.getRequestDispatcher("notice.jsp");
		dispatcher.forward(request, response);
					
	}
	
}
