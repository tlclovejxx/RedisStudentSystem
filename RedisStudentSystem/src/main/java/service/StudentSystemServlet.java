package service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import entity.Student;

public class StudentSystemServlet extends HttpServlet {

	private static final long serialVersionUID = 3811556969337250650L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - 3);
		Method method;
		try {
			method = getClass().getDeclaredMethod(methodName,
			        HttpServletRequest.class, HttpServletResponse.class);
	        method.invoke(this, request, response);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		StudentDao dao = new StudentDao();
		String id = request.getParameter("id");
		Student student = new Student();
		student.setId(id);
		dao.delete(student);
		try {
			response.sendRedirect("studentList.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void change(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        StudentDao dao = new StudentDao();
        try {
			Student student = dao.findStudent(id);
			request.setAttribute("student", student);
			request.getRequestDispatcher("StudentUpdate.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) {
    	Student student = new Student();
    	student.setId(request.getParameter("id"));
    	String name;
		try {
			name = new String(request.getParameter("name"));
			student.setName(name);
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			student.setBirthday(dateFormat.parse(request.getParameter("birthday")));
	    	student.setAvagscore(Integer.valueOf(request.getParameter("avagscore")));
	    	String description = new String(request.getParameter("description"));
	    	student.setDescription(description);
	    	StudentDao dao = new StudentDao();
			dao.update(student);
			response.sendRedirect("studentList.jsp");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void add(HttpServletRequest request, HttpServletResponse response) {
    	try {
			response.sendRedirect("StudentAdd.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void save(HttpServletRequest request, HttpServletResponse response){
    	Student student = new Student();
    	student.setId(request.getParameter("id"));
    	String name;
		try {
			name = new String(request.getParameter("name"));
			student.setName(name);
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			student.setBirthday(dateFormat.parse(request.getParameter("birthday")));
	    	student.setAvagscore(Integer.valueOf(request.getParameter("avagscore")));
	    	String description = new String(request.getParameter("description"));
	    	student.setDescription(description);
	    	StudentDao dao = new StudentDao();
			dao.save(student);
			response.sendRedirect("studentList.jsp");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
