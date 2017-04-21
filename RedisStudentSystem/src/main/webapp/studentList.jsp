<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="dao.StudentDao"%>
<%@page import="entity.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	StudentDao dao = new StudentDao();
	List<Student> students = dao.findAll();
	String pagecount = request.getParameter("page");
	request.setAttribute("page",pagecount != null && pagecount.length()>0 ? pagecount: 1);
	request.setAttribute("students", students);
%>
<html>
<head>
<link href="/RedisStudentSystem/css/sutdentList.css" type="text/css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StudentList</title>
</head>
<body>
	<div class='tablediv'>
		<table>
			<tr><td>排名</td>
			<td>ID</td>
			<td>姓名</td>
			<td>出生年月</td>
			<td>平均分</td>
			<td>备注</td>
			<td colspan="3">操作</td></tr>
			<c:forEach var="student" items="${students}" begin="${(requestScope.page-1)*10}"
			 end="${requestScope.page*10-1}" varStatus="status">
				<tr class="${status.count%2==0?'row1':'row2'}">
				<td>${(requestScope.page-1)*10+status.count}</td>
				<td>${student.id}</td>
				<td>${student.name}</td>
				<td><fmt:formatDate value="${student.birthday}" type="date"/></td>
				<td>${student.avagscore}</td>
				<td>${student.description}</td>
				<td><a href ='delete.do?id=${student.id}'>删除</a></td>
				<td><a href='change.do?id=${student.id}'>修改</a></td>
				<c:if test="${status.count==1}">
					<td rowspan="30" >
					<a href='add.do'>注册新生</a></td>
				</c:if>
				<tr>
			</c:forEach>
			<c:if test="${fn:length(students)==0}">
					<tr><td rowspan="${(requestScope.page-1)*10}" ><a href='add.do'>注册新生</a></td></tr>
			</c:if>
		</table>
		<fmt:parseNumber var="result" integerOnly="true" value="${fn:length(students)/10}"></fmt:parseNumber>
		<c:set var="pageEnd" value="${fn:length(students)%10 == 0 ? result : result+1 }"></c:set>
		<c:if test="${(requestScope.page) !=1}">
			<a href="studentList.jsp?page=1">首页</a>
		</c:if>
		<c:if test="${(requestScope.page) != pageEnd&&fn:length(students) !=0}">
			<a href="studentList.jsp?page=${(requestScope.page)+1}">下一页</a>
		</c:if>
		<c:forEach var="page" begin="1" end="${pageEnd}" step="1">
			<a class="${requestScope.page!=page ? 'noback' : 'yesback'}" href="studentList.jsp?page=${page}">${page}</a>
		</c:forEach>
		<c:if test="${(requestScope.page) !=1}">
			<a href="studentList.jsp?page=${(requestScope.page)-1}">上一页</a>
		</c:if>
		<c:if test="${(requestScope.page) != pageEnd&&fn:length(students) !=0}">
			<a href="studentList.jsp?page=${pageEnd}">尾页</a>
		</c:if>
	</div>
</body>
</html>