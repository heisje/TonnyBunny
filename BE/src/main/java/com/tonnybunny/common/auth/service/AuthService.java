package com.tonnybunny.common.auth.service;


import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.tonnybunny.exception.ErrorCode.ACCESS_TOKEN_ERROR;
import static com.tonnybunny.exception.ErrorCode.REFRESH_TOKEN_ERROR;


@RequiredArgsConstructor
@Service
public class AuthService {

	private final String SECRET_KEY = "dG9ubnlidW5ueXRvbm55YnVubnljYXJyb3RjYXJyb3QK";
	// tonnybunnytonnybunnycarrotcarrot base64로 변환한 키. 근데 공개되면 안돼서 분리시켜야 함
	private final String REFRESH_KEY = "Y2h1bmdoeWVvbmhlaWplc2V1bmd0YWVqZW9uZ2FjaGF3b25rd29ubWluCg";
	// chunghyeonheijeseungtaejeongachawonkwonmin
	private final String DATA_KEY = "seq";


	/**
	 * 액세스 토큰 생성
	 *
	 * @param userEntity
	 * @return
	 */
	public String generateJwtToken(UserEntity userEntity) {
		return Jwts.builder()
		           .setSubject(userEntity.getEmail()) // 들어가는 정보 세팅 - 토큰 제목
		           .setHeader(createHeader()) // 헤더 : 알고리즘, 토큰타입, 생성시간
		           .setClaims(createClaims(userEntity)) // 내부에 들어가는 정보 설정. 현재는 seq. 다른 정보 추가 가능
		           .setExpiration(createExpireDate(1000 * 60 * 5)) // 만료시간 설정
		           .signWith(SignatureAlgorithm.HS256, createSigningKey(SECRET_KEY)) // 알고리즘과 시크릿키 설정
		           .compact();
	}


	/**
	 * 리프레시 토큰 생성
	 *
	 * @param usersEntity
	 * @return
	 */
	public String saveRefreshToken(UserEntity usersEntity) {
		return Jwts.builder()
		           .setSubject(usersEntity.getEmail())
		           .setHeader(createHeader())
		           .setClaims(createClaims(usersEntity))
		           .setExpiration(createExpireDate(1000 * 60 * 10)) // 만료시간 설정
		           .signWith(SignatureAlgorithm.HS256, createSigningKey(REFRESH_KEY))
		           .compact();
	}


	/**
	 * 액세스 토큰 유효성 검증
	 *
	 * @param token
	 * @return
	 */
	public boolean isValidToken(String token) {
		System.out.println("isValidToken is : " + token);
		try {
			Claims accessClaims = getClaimsAccessToken(token);
			System.out.println("Access expireTime: " + accessClaims.getExpiration());
			System.out.println("Access User Seq: " + accessClaims.get("seq"));
			return true;
		} catch (Exception e) { // 에러 발생 시 자세한 내용은 주지 않고, 하나의 에러로 제공
			throw new CustomException(ACCESS_TOKEN_ERROR);
		}
	}
	//	public boolean isValidToken(String token) {
	//		System.out.println("isValidToken is : " + token);
	//		try {
	//			Claims accessClaims = getClaimsAccessToken(token);
	//			System.out.println("Access expireTime: " + accessClaims.getExpiration());
	//			System.out.println("Access User Seq: " + accessClaims.get("seq"));
	//			return true;
	//		} catch (ExpiredJwtException exception) { // 토큰이 만료되었을 경우
	//			System.out.println("Token Expired seq : " + exception.getClaims().getSubject());
	//			return false;
	//		} catch (JwtException exception) { // 이상한 토큰 들어왔을 경우
	//			System.out.println("Token Tampered");
	//			return false;
	//		} catch (NullPointerException exception) { // 널 값 들어온 경우
	//			System.out.println("Token is null");
	//			return false;
	//		}
	//	}


	/**
	 * 리프레시 토큰 유효성 검증
	 *
	 * @param token
	 * @return
	 */
	public boolean isValidRefreshToken(String token) {
		try {
			Claims accessClaims = getClaimsRefreshToken(token);
			System.out.println("Access expireTime: " + accessClaims.getExpiration());
			System.out.println("Access User Seq: " + accessClaims.get("seq"));
			return true;
		} catch (Exception e) { // 에러 발생 시 자세한 내용은 주지 않고, 하나의 에러로 제공
			throw new CustomException(REFRESH_TOKEN_ERROR);
		}
	}
	//	public boolean isValidRefreshToken(String token) {
	//		try {
	//			Claims accessClaims = getClaimsRefreshToken(token);
	//			System.out.println("Access expireTime: " + accessClaims.getExpiration());
	//			System.out.println("Access User Seq: " + accessClaims.get("seq"));
	//			return true;
	//		} catch (ExpiredJwtException exception) {
	//			System.out.println("Token Expired seq : " + exception.getClaims().getSubject());
	//			return false;
	//		} catch (JwtException exception) {
	//			System.out.println("Token Tampered");
	//			return false;
	//		} catch (NullPointerException exception) {
	//			System.out.println("Token is null");
	//			return false;
	//		}
	//	}


	/**
	 * 토큰 만료시간 설정
	 *
	 * @param expireDate
	 * @return
	 */
	private Date createExpireDate(long expireDate) {
		long curTime = System.currentTimeMillis();
		return new Date(curTime + expireDate);
	}


	/**
	 * 토큰 헤더 생성
	 *
	 * @return
	 */
	private Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();

		header.put("typ", "ACCESS_TOKEN");
		header.put("alg", "HS256");
		header.put("regDate", System.currentTimeMillis());

		return header;
	}


	/**
	 * 토큰 클레임(내용부) 생성
	 *
	 * @param usersEntity
	 * @return
	 */
	private Map<String, Object> createClaims(UserEntity usersEntity) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(DATA_KEY, usersEntity.getSeq());
		return claims;
	}


	/**
	 * 토큰 확인부(암호화부) 생성
	 *
	 * @param key
	 * @return
	 */
	private Key createSigningKey(String key) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
		return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
	}


	/**
	 * 액세스 토큰 내용부 정보 확인
	 *
	 * @param token
	 * @return
	 */
	public Claims getClaimsAccessToken(String token) { // Access Token 정보 확인
		return Jwts.parser()
		           .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
		           .parseClaimsJws(token)
		           .getBody();
	}


	/**
	 * 리프레시 토큰 내용부 정보 확인
	 *
	 * @param token
	 * @return
	 */
	public Claims getClaimsRefreshToken(String token) { // Refresh Token 정보 확인
		return Jwts.parser()
		           .setSigningKey(DatatypeConverter.parseBase64Binary(REFRESH_KEY))
		           .parseClaimsJws(token)
		           .getBody();
	}

}
