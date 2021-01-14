package com.kh.menu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/menu/*")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      String uri = request.getRequestURI();
	      String[] uriArr = uri.split("/");
	      
	      switch(uriArr[uriArr.length-1]){
	         case "menu": request.getRequestDispatcher("/WEB-INF/view/menu/menu.jsp").forward(request, response);
	            break;
	         case "order" : calcOrder(request,response);
	            break;
	      }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	
	
	
	private void calcOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// 클라이언트가 menu.jsp에서 food라는 이름으로 보내는 파라미터를 받아서 String 배열에 저장
		// request.getParameter로 안됩니다. 메서드 찾아보기 !
		String[] orders = request.getParameterValues("food");
		System.out.println(Arrays.toString(orders));
		// 사용자가 주문한 정보를 받아서 결제가격 계산하기
	    	List orderList = new ArrayList<Map>();
	        
	    	int total = 0;

		// 피자 : 10000원, 햄버거 : 5000원, 회 : 70000원, 치킨 : 18000원
		for (int i = 0; i < orders.length; i++) {
			Map mapList = new HashMap<String, Object>();
	    	int price=0;
			switch(orders[i]) {
			case "피자" : 
				price += 10000;
			    break;
			case "햄버거" :
			    price += 5000;
			    break;
			case "회" :
				price += 70000;
				break;
			case "치킨":
				price += 18000;
				break;
			}
            total += price;
			mapList.put("food", orders[i]);
			mapList.put("price", price);
			orderList.add(mapList);
		}
		
		request.setAttribute("orderList" , orderList);
        request.setAttribute("total", total);    

        request.getRequestDispatcher("/WEB-INF/view/menu/order_res.jsp").forward(request, response);
		
	}

}
