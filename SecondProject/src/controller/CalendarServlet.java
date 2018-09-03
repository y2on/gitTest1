package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Impl.CalendarImpl;
import Impl.MemberImpl;
import dao.CalendarDao;
import dao.MemberDao;
import dto.memberDto;

public class CalendarServlet extends HttpServlet {
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Ä¶¸°´õ¼­ºí¸´ 1");
		doProcess(req, resp);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doProcess(req, resp);
	}

	

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Ä¶¸°´õ¼­ºí¸´ 2 ");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		CalendarImpl dao = CalendarDao.getInstance();
		MemberImpl mdao = MemberDao.getInstance();
		
		
		String command = req.getParameter("command");
		
		//´º½ºÇÇµå --¼­ºí¸´ -- Ä¶¸°´õwrite
		if(command.equals("gocal")){
			System.out.println("Ä¶¸°´õ¼­ºí¸´ gocal");
			
			 String id = req.getParameter("id");
	         String pw = req.getParameter("pw");

	         memberDto dto = mdao.doLogin(id, pw);
	         if (dto != null) {
	             req.setAttribute("dto", dto);
	             dispatch("CalendarWrite.jsp", req, resp);
	         }

		}
		// Ä¶¸°´õwrite --¼­ºí¸´ -- DiaryWrite
		else if(command.equals("writeDiary")){
			System.out.println("Ä¶¸°´õ¼­ºí¸´ writeDiary");
			
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
