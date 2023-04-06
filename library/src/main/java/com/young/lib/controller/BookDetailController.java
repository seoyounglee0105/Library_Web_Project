package com.young.lib.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.young.lib.dto.BookDTO;
import com.young.lib.dto.CheckoutDTO;
import com.young.lib.dto.ReviewDTO;
import com.young.lib.service.BookService;
import com.young.lib.service.CheckoutService;
import com.young.lib.service.ReviewService;

@WebServlet("/bookDetail")
public class BookDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService = new BookService();
		CheckoutService checkoutService = new CheckoutService();
		ReviewService reviewService = new ReviewService();
		String userId = (String) request.getSession().getAttribute("id");		
		String action = request.getParameter("action");
		request.setAttribute("action", action); // checkout 메시지를 띄우기 위함
		
		// 리뷰 상세
		if ("reviewDetail".equals(action)) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			ReviewDTO review = reviewService.reviewInfo(reviewId);
			request.setAttribute("review", review);
			
			String bookName = bookService.bookInfo(review.getBookId()).getName();
			request.setAttribute("bookName", bookName);
			
			try {
				String imageName = reviewService.selectReviewImage(reviewId).getUuidImage();
				request.setAttribute("imageName", imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("bookView/bookReviewDetail.jsp").forward(request, response);
			
		} else {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			
			// 대여하기
			if ("checkout".equals(action)) {
				CheckoutDTO checkoutDTO = new CheckoutDTO(userId, bookId);
				checkoutService.checkoutBook(checkoutDTO);
				
			} 
			
			BookDTO targetBook = bookService.bookInfo(bookId);
			request.setAttribute("book", targetBook);
			
			ArrayList<ReviewDTO> reviewList = reviewService.viewReviewByBook(bookId);
			request.setAttribute("reviewList", reviewList);
			
			// 리뷰 평균 평점
			int sum = 0;
			double avg = 0.0;
			if (reviewList.size() != 0) {
				for (ReviewDTO r : reviewList) {
					sum += r.getStar();
				}
				avg = ((double) sum / reviewList.size());
			}
			String avgStar = String.format("%.2f", avg); // 소숫점 둘째자리까지만 표시
			
			// 별점 이미지 (반올림)
			int imgStar = 0; 
			imgStar = (int) Math.round(avg);
			
			request.setAttribute("avgStar", avgStar);
			request.setAttribute("imgStar", imgStar);
			
			// 도서별 리뷰 조회
			if ("selectReview".equals(action)) {
				request.getRequestDispatcher("bookView/bookReview.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("bookView/bookDetail.jsp").forward(request, response);
			}		
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
