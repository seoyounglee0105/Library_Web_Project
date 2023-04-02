package com.young.lib.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.young.lib.dto.CheckoutDTO;
import com.young.lib.service.CheckoutService;

@WebServlet("/myPage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = (String) request.getSession().getAttribute("id");
		String menu = request.getParameter("menu");
		
		if ("updateInfo".equals(menu)) {
			
			request.getRequestDispatcher("myPage/updateInfo.jsp").forward(request, response);
			
		} else if ("recordCheckout".equals(menu)) {
			CheckoutService checkoutService = new CheckoutService();
			ArrayList<CheckoutDTO> checkoutList = checkoutService.viewCheckoutByUserId(userId);
			request.setAttribute("checkoutList", checkoutList);
			
			request.getRequestDispatcher("myPage/recordCheckout.jsp").forward(request, response);
			
		} else if ("manageReview".equals(menu)) {
			
			request.getRequestDispatcher("myPage/manageReview.jsp").forward(request, response);
			
		} else {
			response.sendRedirect("/library/myPage/main.jsp");		
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
