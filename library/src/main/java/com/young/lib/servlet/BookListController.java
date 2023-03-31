package com.young.lib.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.young.lib.controller.BookController;
import com.young.lib.dto.BookDTO;

@WebServlet("/bookList")
public class BookListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookController bookController = new BookController();
		request.setCharacterEncoding("UTF-8");
		ArrayList<BookDTO> resultList = null;
		
		String menu = request.getParameter("menu");
		String orderby = request.getParameter("orderby");
		if ("all".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByAll("b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByAll("b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByAll("check_out_count DESC");				
			} else {
				resultList = bookController.requestViewAll();				
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);
			
		} else if ("it".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(1, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(1, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(1, "check_out_count DESC");				
			} else {
				resultList = bookController.requestViewCategory(1);
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			
			
		} else if ("novel".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(2, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(2, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(2, "check_out_count DESC");	
			} else {
				resultList = bookController.requestViewCategory(2);
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("hobby".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(3, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(3, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(3, "check_out_count DESC");	
			} else {
				resultList = bookController.requestViewCategory(3);
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("economy".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(4, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(4, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(4, "check_out_count DESC");	
			} else {
				resultList = bookController.requestViewCategory(4);
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("improvement".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(5, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(5, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(5, "check_out_count DESC");	
			} else {
				resultList = bookController.requestViewCategory(5);
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("cook".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(6, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(6, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(6, "check_out_count DESC");	
			} else {
				resultList = bookController.requestViewCategory(6);
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("child".equals(menu)) {
			if ("bookName".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(7, "b.name");				
			} else if ("index".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(7, "b.id DESC");				
			} else if ("count".equals(orderby)) {
				resultList = bookController.requestOrderByCategory(7, "b.check_out_count DESC");	
			} else {
				resultList = bookController.requestViewCategory(7);
			}
			request.setAttribute("list", resultList);
			
			// 페이지 수 구할 예정
			// int pageCount = resultList.size();
			// request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			

		} else if ("search".equals(menu)) {
			String search = request.getParameter("searchBook");
			
			// 공백으로만 이뤄진 문자열이 입력되었다면
			if (search.replace(" ", "").equals("") == false) {
				resultList = bookController.requestViewSearch(search);
				
				// 페이지 수 구할 예정
				// int pageCount = resultList.size();
				// request.setAttribute("pageCount", pageCount);				
			}
			
			request.setAttribute("list", resultList);
			request.getRequestDispatcher("bookView/bookList.jsp").forward(request, response);			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
