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
    
    response.sendRedirect("notice-Detail.jsp?c="+code);
}
%>