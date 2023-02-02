package com.tonnybunny.interceptor;


import com.tonnybunny.common.auth.service.AuthService;
import com.tonnybunny.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.tonnybunny.exception.ErrorCode.ACCESS_TOKEN_ERROR;
import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_TOKEN;


@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

	private final AuthService authService;


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

		System.out.println("JwtToken 호출");
		String accessToken = request.getHeader("ACCESS_TOKEN");
		System.out.println("AccessToken:" + accessToken);

		if (accessToken != null) {
			if (authService.isValidToken(accessToken)) {
				return true;
			} else {
				throw new CustomException(ACCESS_TOKEN_ERROR);
				
			}
		} else {
			throw new CustomException(NOT_FOUND_TOKEN);
		}

	}

}
