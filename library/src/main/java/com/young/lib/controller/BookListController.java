package com.young.lib.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.young.lib.dto.BookDTO;
import com.young.lib.service.BookService;

@WebServlet("/bookList")
public class BookListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService = new BookService();
		ArrayList<BookDTO> resultList = new ArrayList<>();
		request.setCharacterEncoding("UTF-8");
		
		String menu = request.getParameter("menu");
		String orderby = request.getParameter("orderby");
		if ("all".equals(menu)) {
			if ("bookName".equals(orderby)) { 
				resultList = bookService.orderByAllBooks("b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByAllBooks("b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByAllBooks("check_out_count DESC");				
			} else {
				resultList = bookService.viewAllBooks();				
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);
			
		} else if ("it".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(1, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(1, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(1, "check_out_count DESC");				
			} else {
				resultList = bookService.viewCategoryBooks(1);
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			
			
		} else if ("novel".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(2, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(2, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(2, "check_out_count DESC");	
			} else {
				resultList = bookService.viewCategoryBooks(2);
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("hobby".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(3, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(3, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(3, "check_out_count DESC");	
			} else {
				resultList = bookService.viewCategoryBooks(3);
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("economy".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(4, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(4, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(4, "check_out_count DESC");	
			} else {
				resultList = bookService.viewCategoryBooks(4);
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("improvement".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(5, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(5, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(5, "check_out_count DESC");	
			} else {
				resultList = bookService.viewCategoryBooks(5);
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("cook".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(6, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(6, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(6, "check_out_count DESC");	
			} else {
				resultList = bookService.viewCategoryBooks(6);
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("child".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(7, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(7, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookService.orderByCategoryBooks(7, "b.check_out_count DESC");	
			} else {
				resultList = bookService.viewCategoryBooks(7);
			}
			request.setAttribute("list", resultList);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("search".equals(menu)) {
			String search = request.getParameter("searchBook");
			
			// 공백으로만 이뤄진 문자열이 입력되었다면 실행 X
			if (search.replace(" ", "").equals("") == false) {
				resultList = bookService.searchBooks(search);
			
			}
			
			request.setAttribute("list", resultList);
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
