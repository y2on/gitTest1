package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Impl.CalendarImpl;
import dao.CalendarDao;

public class CalendarServlet extends HttpServlet {
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("캘린더서블릿 1");
		doProcess(req, resp);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doProcess(req, resp);
	}


	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("캘린더서블릿 2 ");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		CalendarImpl dao = CalendarDao.getInstance();

		String command = req.getParameter("command");
		
		if(command.equals("writeDiary")){
			System.out.println("캘린더서블릿 3");
			
			String tday = req.getParameter("tday");

			dispatch("DiaryWrite.jsp", req, resp);
		} 
		
		
		
	}

	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}
}
