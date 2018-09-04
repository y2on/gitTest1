package controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Impl.MemberImpl;
import dao.MemberDao;
import dto.memberDto;

public class MemberServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		MemberImpl dao = MemberDao.getInstance();

		String command = req.getParameter("command");

		if (command.equals("login")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");

			memberDto dto = dao.doLogin(id, pw);

			if (dto != null) {
				//req.setAttribute("memDto", dto);
				req.getSession().setAttribute("user", dto);
				dispatch("Newspeed.jsp", req, resp);
			}else {
				PrintWriter out = resp.getWriter();

				out.println("<script>");

				out.println("alert('아이디와 비밀번호를 입력해주세요.');");

				out.println("history.back();");

				out.println("</script>");

			}
			
		}
		
			// 회원가입
		else if(command.equals("signup")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String name = req.getParameter("name");
			String nick = req.getParameter("nick");
			System.out.println(id+pw+name+nick);
			
			int sign = dao.dosignup(id, pw, name, nick);
			
			if(sign == 1) {
				resp.sendRedirect("Main.jsp");
			}else {
				PrintWriter out = resp.getWriter();
				
				out.println("<script>");

				out.println("alert('다시 가입해주세요.');");

				out.println("history.back();");

				out.println("</script>");

			}
			
			// 아이디 중복체크			
		}else if(command.equals("signPage")) {
			List<String> list = dao.idcheck();
			
			req.setAttribute("idck", list);
			dispatch("Signup.jsp", req, resp);
			
		}
		else if(command.equals("update")) {
	         String id = req.getParameter("id");
	         String pwd = req.getParameter("pwd");
	         String name = req.getParameter("name");
	         String nick = req.getParameter("nick");

	         memberDto dto = new memberDto();
	         dto.setId(id);
	         dto.setPw(pwd);
	         dto.setName(name);
	         dto.setNick(nick);
	         System.out.println(dto.toString());
	         boolean isS = dao.editMember(dto);
	         PrintWriter pw = resp.getWriter();
	         
	         pw.print(isS);
	      }
		
	}

	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}

}
