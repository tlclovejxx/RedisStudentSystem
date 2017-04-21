<!DOCTYPE HTML>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>studentadd</title>
<link rel="stylesheet" type="text/css" href="/RedisStudentSystem/css/studentAdd.css"/> 
</head>

<body>
	<form action="update.do" method="post" accept-charset="iso-8859-1">
    	<h1>修改学生信息</h1>
        <p class="p0">
        	ID：<c:out value="${student.id}"></c:out>
        	<input type="hidden" name="id" value="${student.id}">
      	</p>
      	<p class="p1"> 
       		姓&nbsp;&nbsp;&nbsp;名：<input type="text" name="name" value="${student.name }">
     	 </p>
      	<p class="p2"> 
       		生&nbsp;&nbsp;&nbsp;日：<input type="date" name="birthday" value=
       			"<fmt:formatDate value="${student.birthday}" type="date"/>">
      	</p>
      	<p class="p3">
      		平均分：<input type="text" name="avagscore" value="${student.avagscore}"/>
      	</p>
      	<p class="p4">
      		备&nbsp;&nbsp;&nbsp;注：<input type="text" name="description" value="${student.description}"/>
       	</p>
     	<div><input type="submit" value="修改"/></div>
     	<div class="giveUp"><a href="studentList.jsp">放弃修改</a></div>
    </form>
</body>
</html>