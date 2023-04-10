package com.young.lib.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.young.lib.dao.ReviewImageDAO;
import com.young.lib.dto.BookDTO;
import com.young.lib.dto.CheckoutDTO;
import com.young.lib.dto.ReviewDTO;
import com.young.lib.dto.ReviewImageDTO;
import com.young.lib.dto.UserDTO;
import com.young.lib.service.BookService;
import com.young.lib.service.CheckoutService;
import com.young.lib.service.ReviewService;
import com.young.lib.service.UserService;

@WebServlet("/myPage")
@MultipartConfig
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckoutService checkoutService = new CheckoutService();
		UserService userService = new UserService();
		BookService bookService = new BookService();
		ReviewService reviewService = new ReviewService();
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
		
		// 리뷰 작성
		} else if ("writeReview".equals(menu)) {
			// 리뷰 권한
			// 해당 회원의 대여 기록에 있는 책들 중, 리뷰 작성 기록이 없는 책들의 id 가져오기
			
			ArrayList<Integer> bookIdList = new ArrayList<>(); // id
			
			ArrayList<CheckoutDTO> list = checkoutService.viewCheckoutByUserId(userId); // 회원의 대여 기록
			for (CheckoutDTO c : list) {		
				// 해당 도서에 대한 리뷰 작성 
				if (reviewService.selectReviewByUserAndBook(userId, c.getBookId()) == null) {
					// 리스트에 추가되지 않은 id라면 추가
					if (bookIdList.contains(c.getBookId()) == false) {
						bookIdList.add(c.getBookId());
					}
				}
			}
		
			// 리뷰 작성 가능한 도서 정보
			ArrayList<BookDTO> availableBookList = new ArrayList<>(); // 리뷰 작성 가능한 bookDTO들
			
			for (int bookId : bookIdList) {
				availableBookList.add(bookService.bookInfo(bookId));
			}
			request.setAttribute("bookList", availableBookList);
			
			request.getRequestDispatcher("myPage/writeReview.jsp").forward(request, response);	
			
		// 리뷰 관리
		} else if ("manageReview".equals(menu)) {
			ArrayList<ReviewDTO> userReviewList = reviewService.viewReviewByUser(userId);
			
			for (ReviewDTO r : userReviewList) {
				r.setName(bookService.bookInfo(r.getBookId()).getName());
			}
			request.setAttribute("userReviewList", userReviewList);
			
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
			
			// 리뷰 작성 가능한 도서 정보
			ArrayList<Integer> bookIdList = new ArrayList<>(); // id
			
			ArrayList<CheckoutDTO> list = checkoutService.viewCheckoutByUserId(userId); // 회원의 대여 기록
			for (CheckoutDTO c : list) {		
				// 해당 도서에 대한 리뷰 작성 기록이 없다면
				if (reviewService.selectReviewByUserAndBook(userId, c.getBookId()) == null) {
					// 리스트에 추가되지 않은 id라면 추가
					if (bookIdList.contains(c.getBookId()) == false) {
						bookIdList.add(c.getBookId());
					}
				}
			}
			// 리뷰 작성 가능한 도서 정보
			ArrayList<BookDTO> availableBookList = new ArrayList<>(); // 리뷰 작성 가능한 bookDTO들
			
			for (int bookId : bookIdList) {
				availableBookList.add(bookService.bookInfo(bookId));
			}
			
			int bookCount = availableBookList.size();
			request.setAttribute("reviewCount", bookCount);
			request.setAttribute("bookList", availableBookList);
			
			request.getRequestDispatcher("myPage/main.jsp").forward(request, response);	
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckoutService checkoutService = new CheckoutService();
		UserService userService = new UserService();
		BookService bookService = new BookService();
		ReviewService reviewService = new ReviewService();
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
			return;
			
		// 회원 정보 수정 전 비밀번호 확인
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
			}  else {
				// 입력받은 비밀번호가 틀렸다면 (result == 0)
				request.setAttribute("result", result);
				request.getRequestDispatcher("myPage/userInfo_check.jsp").forward(request, response);				
			}
			
		// 회원 정보 수정
		} else if ("updateInfo".equals(action)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("userName");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			
			UserDTO updateDTO = new UserDTO(id, password, name, phoneNumber, address, email);
			int responseUpdateInfo = userService.updateInfo(updateDTO);
			request.setAttribute("responseUpdateInfo", responseUpdateInfo);
			
			// main.jsp로
			
		// 리뷰 작성
		} else if ("writeReview".equals(action)) {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int star = Integer.parseInt(request.getParameter("star"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Part filePart = request.getPart("file");
			
			int responseType = 0;
			// 도서명을 선택하지 않았다면 (-1)
			if (bookId == -1) {
				responseType = 2;
			} else {
				ReviewDTO reviewDto = new ReviewDTO(userId, bookId, star, title, content);
				responseType = reviewService.writeReview(reviewDto);
				
				// 이미지가 있다면
				if (filePart.getSubmittedFileName().equals("") == false) {
					InputStream fileContent = filePart.getInputStream();
					OutputStream outputStream = null;
					try {
						UUID uuid = UUID.randomUUID();
						String uuidImage = uuid + "_" + filePart.getSubmittedFileName();
						
						String imgDir = getServletContext().getInitParameter("imgDir");
						
						File file = new File(imgDir, uuidImage);
						outputStream = new FileOutputStream(file);
						byte[] buffer = new byte[1024];
						int length;
						while ((length = fileContent.read(buffer)) != -1) {
							outputStream.write(buffer, 0, length);
						}
						
						// DB에 저장
						ReviewDTO dto = reviewService.selectReviewByUserAndBook(userId, bookId);
						int reviewId = dto.getId();
						String originImage = filePart.getSubmittedFileName();
						// uuidImage
						
						ReviewImageDTO reviewImageDTO = new ReviewImageDTO(reviewId, originImage, uuidImage);
						reviewService.insertReviewImage(reviewImageDTO);
						
					} catch (Exception e) {
						System.out.println("파일 이상");
					} finally {
						fileContent.close();
						outputStream.flush();
						
						if (outputStream != null) {
							outputStream.close();
						}
					}
				}
			}
			request.setAttribute("responseReview", responseType);
			// main.jsp로
			
		// 리뷰 삭제
		} else if ("deleteReview".equals(action)) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			int responseType = reviewService.deleteReview(reviewId);
			request.setAttribute("responseDelete", responseType);
			
			ArrayList<ReviewDTO> userReviewList = reviewService.viewReviewByUser(userId);
			
			for (ReviewDTO r : userReviewList) {
				r.setName(bookService.bookInfo(r.getBookId()).getName());
			}
			request.setAttribute("userReviewList", userReviewList);
			
			request.getRequestDispatcher("myPage/manageReview.jsp").forward(request, response);
			return;
		}
		
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
		
		// 리뷰 작성 가능한 도서 정보
		ArrayList<Integer> bookIdList = new ArrayList<>(); // id
		
		ArrayList<CheckoutDTO> list = checkoutService.viewCheckoutByUserId(userId); // 회원의 대여 기록
		for (CheckoutDTO c : list) {		
			// 해당 도서에 대한 리뷰 작성 기록이 없다면
			if (reviewService.selectReviewByUserAndBook(userId, c.getBookId()) == null) {
				// 리스트에 추가되지 않은 id라면 추가
				if (bookIdList.contains(c.getBookId()) == false) {
					bookIdList.add(c.getBookId());
					System.out.println(c.getBookId());
				}
			}
		}
	
		// 리뷰 작성 가능한 도서 정보
		ArrayList<BookDTO> availableBookList = new ArrayList<>(); // 리뷰 작성 가능한 bookDTO들
		
		for (int bookId : bookIdList) {
			availableBookList.add(bookService.bookInfo(bookId));
			System.out.println(bookService.bookInfo(bookId));
		}
		
		int bookCount = availableBookList.size();
		request.setAttribute("reviewCount", bookCount);
		request.setAttribute("bookList", availableBookList);
		try {
			request.getRequestDispatcher("myPage/main.jsp").forward(request, response);						
		} catch (Exception e) {
		}
	}
}
