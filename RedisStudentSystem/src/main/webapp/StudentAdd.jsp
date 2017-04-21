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
	<form action="save.do" method="post" accept-charset="iso-8859-1">
    	<h1>学生注册</h1>
        <p class="p0">
        	&nbsp;&nbsp;&nbsp;ID&nbsp;&nbsp;&nbsp;：<input type="text" name="id"/>
      	</p>
      	<p class="p1"> 
       		姓&nbsp;&nbsp;&nbsp;名：<input type="text" name="name">
     	 </p>
      	<p class="p2"> 
       		生&nbsp;&nbsp;&nbsp;日：<input type="date" name="birthday"/>
      	</p>
      	<p class="p3">
      		平均分：<input type="text" name="avagscore"/>
      	</p>
      	<p class="p4">
      		备&nbsp;&nbsp;&nbsp;注：<input type="text" name="description"/>
       	</p>
     	<div><input type="submit" value="注册"/></div>
     	<a id="giveUp" href="studentList.jsp">放弃注册</a>
    </form>
</body>
</html>