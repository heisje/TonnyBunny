package com.tonnybunny.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("#Interceptor PreHandle Method Req URI : " + request.getRequestURI());
		/**
		 * 이 영역에서 인증여부를 판단하여 로그인 페이지로 보낼 로직을 구현
		 *
		 *         HttpSession session = request.getSession(false);
		 *         if(session != null) {
		 *             Object obj = session.getAttribute("member");
		 *             if(obj != null)
		 *                 return true;
		 *         }
		 *
		 *         response.sendRedirect(request.getContextPath() + "/");
		 *         return false;
		 */
		return true;
	}

}
