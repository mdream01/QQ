<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%

if(request.getMethod()=="POST"){
	
 	String code="";
 	String writer="aaaa";
/* 	String code =request.getParameter("code"); */
/*  	String writer = request.getParameter("writer"); */
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
  	String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
  	Class.forName("oracle.jdbc.driver.OracleDriver");
  	
    Connection con = DriverManager.getConnection(url, "c##sist", "dclass");/* �ϳ���  �������� �Ѵ� */
    
/*     con.setAutoCommit(false); */
    
/* -----------CODE ����--------------------- */
	String sqlCode = "SELECT MAX(TO_NUMBER(CODE)+1) AS CODE FROM NOTICE";
	Statement st = con.createStatement(); /* ���� �������� �Ѵ� */
	ResultSet rs = st.executeQuery(sqlCode);
	rs.next();
	code = rs.getString("code");

/* ---------�Խñ� ���--------------- */
 	String sql = "UPDATE NOTICE SET TITLE=?,CONTENT=? WHERE CODE = ?";
	PreparedStatement pst = con.prepareStatement(sql); /* ���� �������� �Ѵ� */

	pst.setString(1,title);
	pst.setString(2,content);
	pst.setString(3,code);
	
	pst.executeUpdate();

   /*  con.commit(); */
    
    response.sendRedirect("notice-Detail.jsp?c="+code);
}
%>