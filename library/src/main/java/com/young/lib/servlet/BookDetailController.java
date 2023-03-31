package com.young.lib.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.young.lib.controller.BookController;
import com.young.lib.dto.BookDTO;

/**
 * Servlet implementation class bookDetail
 */
@WebServlet("/bookDetail")
public class BookDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookController bookController = new BookController();
		request.setCharacterEncoding("UTF-8");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookDTO targetBook = bookController.requestBookInfo(bookId);
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
