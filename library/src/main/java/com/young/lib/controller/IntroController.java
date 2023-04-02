package com.young.lib.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.young.lib.dto.UserDTO;
import com.young.lib.service.UserService;

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
		
		String menu = request.getParameter("menu");
		if ("login".equals(menu)) {
			response.sendRedirect("/library/intro/login.jsp");		
			
		} else if ("signUp".equals(menu)) {
			response.sendRedirect("/library/intro/signUp.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
