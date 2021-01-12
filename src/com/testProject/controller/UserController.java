package com.testProject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.testProject.dto.JoinReqDto;
import com.testProject.dto.UserDto;
import com.testProject.service.UserService;
import com.testProject.util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();

		if (cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			UserDto dto = new UserDto();

			dto = userService.로그인(username, password);

			if (dto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", dto); // 인증주체
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			} else {
				Script.back(response, "로그인실패");
			}
		} else if (cmd.equals("join")) {
			// 서비스 호출
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);

			System.out.println("회원가입 : " + dto);

			int result = userService.회원가입(dto);
			if (result == 1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			} else {
				Script.back(response, "회원가입 실패");
			}
		} else if (cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate(); //로그아웃 시키기
			System.out.println("완료");
			RequestDispatcher dispatcher = request.getRequestDispatcher("user/login.jsp");
			dispatcher.forward(request, response);
		} else if(cmd.equals("userList")) {

			List<UserDto> dtos = userService.목록보기();

			
			request.setAttribute("users", dtos);
			// request에 담고
			// RequestDispathcer 만들어서 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("user/userList.jsp");
			dispatcher.forward(request, response);
		} else if(cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			String status;
			int result = userService.유저삭제(id);
			
			if(result == 1) {
				status = "ok";
			}else {
				status = "fail";
			}
			Gson gson = new Gson();
			String respData = gson.toJson(status); //json데이터로 변환
			PrintWriter out = response.getWriter();
			out.print(respData); // json이 아닌 String로 보내면 작동 하징 ㅏㄶ음
			out.flush();
		}
	}
}
