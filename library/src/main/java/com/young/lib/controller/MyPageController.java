package com.young.lib.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.young.lib.dao.UserDAO;
import com.young.lib.dto.CheckoutDTO;
import com.young.lib.dto.UserDTO;
import com.young.lib.service.BookService;
import com.young.lib.service.CheckoutService;
import com.young.lib.service.UserService;

@WebServlet("/myPage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckoutService checkoutService = new CheckoutService();
		UserService userService = new UserService();
		BookService bookService = new BookService();
		request.setCharacterEncoding("UTF-8");
		String userId = (String) request.getSession().getAttribute("id");
		String menu = request.getParameter("menu");
		String action = request.getParameter("action");
		
		// 회원 정보 수정
		if ("userInfo".equals(menu)) {
			int result = 2; // 비밀번호를 입력하지 않은 상태
			request.setAttribute("result", result);
			
			request.getRequestDispatcher("myPage/userInfo_check.jsp").forward(request, response);
		
		// 내 서재
		} else if ("myBooks".equals(menu)) {
	
			ArrayList<CheckoutDTO> checkoutList = checkoutService.viewCheckoutByUserId(userId);
			
			ArrayList<CheckoutDTO> notReturnList = new ArrayList<>();
			for (CheckoutDTO dto : checkoutList) {
				if (dto.getIsReturn() == false) {
					notReturnList.add(dto);
				}
			}
			request.setAttribute("allList", checkoutList); // 대여 내역
			request.setAttribute("notReturnList", notReturnList); // 대여 중인 도서
			request.getRequestDispatcher("myPage/myBooks.jsp").forward(request, response);	
			
		// 리뷰 관리
		} else if ("manageReview".equals(menu)) {
			
			request.getRequestDispatcher("myPage/manageReview.jsp").forward(request, response);
		
		// 회원 탈퇴
		} else if ("deleteUser".equals(action)) {
			// 탈퇴 전 해당 회원이 빌린 책들의 대여 가능 여부를 모두 true로
			ArrayList<CheckoutDTO> list = checkoutService.viewCheckoutByUserId(userId);
			for (CheckoutDTO checkoutDTO : list) {
				int bookId = checkoutDTO.getBookId();
				bookService.updateIsAvailable(true, bookId);
			}
			
			
			// 탈퇴
			userService.deleteUser(userId);
			
			// 로그아웃
			request.getSession().invalidate();
			response.sendRedirect("/library/index.jsp");
			
		} else {
			// 회원 정보
			UserDTO userDTO = userService.userInfo(userId);
			request.setAttribute("user", userDTO);
			
			// 대여 중인 도서 정보
			ArrayList<CheckoutDTO> checkoutList = checkoutService.viewCheckoutByUserId(userId);
			ArrayList<CheckoutDTO> notReturnList = new ArrayList<>();
			for (CheckoutDTO dto : checkoutList) {
				if (dto.getIsReturn() == false) {
					notReturnList.add(dto);
				}
			}
			int notReturnCount = notReturnList.size();
			request.setAttribute("checkoutCount", notReturnCount);
			
			request.getRequestDispatcher("myPage/main.jsp").forward(request, response);	
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckoutService checkoutService = new CheckoutService();
		UserService userService = new UserService();
		request.setCharacterEncoding("UTF-8");
		String userId = (String) request.getSession().getAttribute("id");
		String action = request.getParameter("action");
		
		// 반납하기
		if ("returnBook".equals(action)) {
			int checkoutId = Integer.parseInt(request.getParameter("checkoutId"));
			CheckoutDTO targetDto = checkoutService.viewCheckoutById(checkoutId);
			
			int result = checkoutService.returnBook(targetDto); // 반납
			
			ArrayList<CheckoutDTO> checkoutList = checkoutService.viewCheckoutByUserId(userId);
			
			ArrayList<CheckoutDTO> notReturnList = new ArrayList<>();
			for (CheckoutDTO dto : checkoutList) {
				if (dto.getIsReturn() == false) {
					notReturnList.add(dto);
				}
			}
			request.setAttribute("allList", checkoutList); // 대여 내역
			request.setAttribute("notReturnList", notReturnList); // 대여 중인 도서
			request.setAttribute("result", result);
			request.getRequestDispatcher("myPage/myBooks.jsp").forward(request, response);	
			
		} else if ("pwCheck".equals(action)) {
			String inputPw = request.getParameter("password");
			String correctPw = userService.userInfo(userId).getPassword();
			int result = 0;
			
			// 입력받은 비밀번호가 정확하다면
			if (inputPw.equals(correctPw)) {
				result = 1;
				UserDTO userDTO = userService.userInfo(userId);
				request.setAttribute("user", userDTO);
				request.getRequestDispatcher("myPage/userInfo_update.jsp").forward(request, response);
			} 
			// 입력받은 비밀번호가 틀렸다면 (result == 0)
			request.setAttribute("result", result);
			request.getRequestDispatcher("myPage/userInfo_check.jsp").forward(request, response);
			
		} else if ("updateInfo".equals(action)) {
			// 회원 정보 수정
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("userName");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			
			UserDTO updateDTO = new UserDTO(id, password, name, phoneNumber, address, email);
			int result = userService.updateInfo(updateDTO);
			System.out.println(result);
			
			// 마이페이지로 이동
			UserDTO userDTO = userService.userInfo(userId);
			request.setAttribute("user", userDTO);
			request.setAttribute("result", result);
			
			// 대여 중인 도서 정보
			ArrayList<CheckoutDTO> checkoutList = checkoutService.viewCheckoutByUserId(userId);
			ArrayList<CheckoutDTO> notReturnList = new ArrayList<>();
			for (CheckoutDTO dto : checkoutList) {
				if (dto.getIsReturn() == false) {
					notReturnList.add(dto);
				}
			}
			int notReturnCount = notReturnList.size();
			request.setAttribute("checkoutCount", notReturnCount);
			
			request.getRequestDispatcher("myPage/main.jsp").forward(request, response);
			
		}

	}
}
