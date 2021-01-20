package com.kh.toy.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.toy.member.model.service.MemberService;
import com.kh.toy.member.model.vo.Member;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberService();
   
    public MemberController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		
		switch(uriArr[uriArr.length-1]){
			case "join": join(request,response);
				break;
			case "joinimpl": joinImpl(request,response);
				break;
			case "login": login(request,response);
				break;
			case "loginimpl": loginImpl(request, response);
				break;
			case "logout": logout(request, response);
				break;
			case "idcheck": idCheck(request,response);
				break;
			case "mypage" : myPage(request,response);
			    break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/join.jsp")
		.forward(request, response);
	}
	
	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자의 요청을 받아서, 권한 확인 및 데이터들을 가공하고 
		//service 로 넘겨준다.
		String userId = request.getParameter("id");
		String password = request.getParameter("pw");
		String tell = request.getParameter("tell");
		String email = request.getParameter("email");
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setTell(tell);
		member.setEmail(email);
		
		//성공 시 1, 실패 시 0이 반환
		int res = memberService.insertMember(member);
		//성공!
		if(res > 0) {
			request.setAttribute("alertMsg", "회원가입을 축하드립니다.");
			request.setAttribute("url", "/index/index");
			
			request
			.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
			
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/view/member/login.jsp")
		.forward(request, response);
	}
	
	private void loginImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 파라미터로 보낸 아이디와 비밀번호를 받아서
		//memberService의 memberAuthenticate 메서드로 전달
		//String userId = request.getParameter("id");
		//String password = request.getParameter("pw");
		
		String data = request.getParameter("data");
		Gson gson = new Gson();
		Map parsedData = gson.fromJson(data, Map.class);
		
		//parsedData에 담겨저온
		//문자
		//숫자
		//배열
		//객체
		//출력해보기
		
		
		//gson json파싱 타입 매치
		//String => String
		//object => map
		//array => ArrayList
		//number => Double
		//null => null
		String userId = (String)parsedData.get("id");
		String password = (String)parsedData.get("pw");
		
		//결과값으로 회원정보가 반환 
		Member user = memberService.memberAuthenticate(userId, password);
		  //결과에 따라서 view 페이지를 결정 
		  //로그인 성공 
		if(user != null) { //회원정보를 session에 저장
		    //회원정보를 session scope 동안 보관하기 위해서 
			request.getSession().setAttribute("user",user); 
			response.getWriter().print("success"); 
		    //로그인 실패 
		}else {
		  response.getWriter().print("fail"); 
	    }
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		request
		.getRequestDispatcher("/WEB-INF/view/index/index.jsp")
		.forward(request, response);
	}
	
	private void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 application/x-www-form-urlencoded 컨텐츠타입으로 보낼 것이기 때문에 
		//이전과 동일하게 서블릿 코드 작성 가능
		String userId = request.getParameter("userId");
		Member member = memberService.selectMemberById(userId);
		//사용 가능한 아이디
		if(member == null) {
			//println은 줄바꿈문자가 뒤에 포함이 되기 때문에 실제로 넘어가는 값이 'success\n' 이다.
			response.getWriter().print("success");
			//사용 불가능한 아이디
		}else {
			response.getWriter().print("fail");
		}
	}
	private void myPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/view/member/mypage.jsp")
		.forward(request, response);
	}	
	
	

	
	
}
