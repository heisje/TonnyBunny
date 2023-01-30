package com.tonnybunny.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {
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

		/**
		 *     System.out.println("JwtToken 호출");
		 *     String accessToken = request.getHeader("ACCESS_TOKEN");
		 *     System.out.println("AccessToken:" + accessToken);
		 *     String refreshToken = request.getHeader("REFRESH_TOKEN");
		 *     System.out.println("RefreshToken:" + refreshToken);
		 *
		 *     if (accessToken != null) {
		 *       if (tokenUtils.isValidToken(accessToken)) {
		 *         return true;
		 *       }
		 *     }
		 *     response.setStatus(401);
		 *     response.setHeader("ACCESS_TOKEN", accessToken);
		 *     response.setHeader("REFRESH_TOKEN", refreshToken);
		 *     response.setHeader("msg", "Check the tokens.");
		 *     return false;
		 */

	}

}
