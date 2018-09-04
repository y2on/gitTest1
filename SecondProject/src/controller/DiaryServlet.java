package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Impl.DiaryImpl;
import dao.DiaryDao;
import dto.DiaryDto;

public class DiaryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		DiaryImpl dao = DiaryDao.getInstance();
		
		String command = req.getParameter("command");
		
		if(command.equals("insert")) {
			String content = req.getParameter("content");
			String tday = req.getParameter("tday");
			String title = req.getParameter("title");
			//String id = req.getParemeter("id");
			String id = "gd";
			
			DiaryDto dto = new DiaryDto();
			
			dto.setContent(content);
			dto.setId(id);
			dto.setTitle(title);
			dto.setTday(tday);
			
			boolean b = dao.addDiary(dto);
			
			PrintWriter pw = resp.getWriter();
			pw.print(b);
		}
	}
}
