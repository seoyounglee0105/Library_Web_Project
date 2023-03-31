package com.young.lib.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.young.lib.controller.UserController;
import com.young.lib.dto.UserDTO;

// 회원가입, 로그인

@WebServlet("/intro")
public class IntroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IntroController() {
        super();
    }

    // 주소
    // http://localhost:8080/library/intro
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserController userController = new UserController();
		
		String menu = request.getParameter("menu");
		if ("login".equals(menu)) {
			// 로그인된 상태랑 로그인 안 된 상태 구분하기
			response.sendRedirect("/library/intro/login.jsp");
			
			
		} else if ("signUp".equals(menu)) {
			response.sendRedirect("/library/intro/signUp.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserController userController = new UserController();
		
		String action = request.getParameter("action");
		if ("signUp".equals(action)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("userName");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			UserDTO userDTO = new UserDTO(id, password, name, phoneNumber, address, email);
			
			int responseType = userController.requestSignUp(userDTO);
			
			if (responseType == 1) {
				
			}
			
			
		}
	}

}
