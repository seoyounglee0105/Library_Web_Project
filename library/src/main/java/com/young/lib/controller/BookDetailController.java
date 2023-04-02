package com.young.lib.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.young.lib.dto.BookDTO;
import com.young.lib.dto.CheckoutDTO;
import com.young.lib.service.BookService;
import com.young.lib.service.CheckoutService;

@WebServlet("/bookDetail")
public class BookDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService = new BookService();
		CheckoutService checkoutService = new CheckoutService();
		request.setCharacterEncoding("UTF-8");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String action = request.getParameter("action");
		request.setAttribute("action", action);
		
		String userId = (String) request.getSession().getAttribute("id");
		
		// 대여하기
		if ("checkout".equals(action)) {
			CheckoutDTO checkoutDTO = new CheckoutDTO(userId, bookId);
			checkoutService.checkoutBook(checkoutDTO);
		}
		
		BookDTO targetBook = bookService.bookInfo(bookId);
		request.setAttribute("book", targetBook);
		request.getRequestDispatcher("bookView/bookDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
