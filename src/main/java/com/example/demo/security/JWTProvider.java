package com.example.demo.security;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.exception.LoginException;
import com.example.demo.service.UserDetailsServiceImp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 認証用トークンを実際に扱う機能を提供するクラスである。
 */
@Component
public class JWTProvider {

    // Signatureのエンコードに使うシークレットキー
    public static final String TOKEN_SECRET_KEY = "aq347[@op6y@p-sz]";
    
    public static final String USER_NAME = "userName";
    public static final String USER_ID_NAME = "userIdName";
    
    // ユーザ情報を取得するためのサービスクラス
    @Autowired
    private UserDetailsServiceImp service;
    @Autowired
	PasswordEncoder passwordEncoder;

    /**
     * User情報からJWT（認証用トークン）を作成する
     * @param user Userオブジェクト
     * @return JWT（認証用トークン）
     */
    public String createToken(UserDetailsImp user) {
    	
        // ClaimとしてIDとユーザ名を載せる
        Claims claims = Jwts.claims().setSubject(user.getUserId().toString());
        claims.put(USER_NAME, user.getUsername());
        claims.put(USER_ID_NAME, user.getUserIdName());
        
        // トークンの開始時間と満了時間を決める
        Calendar calender = Calendar.getInstance();
        Date iat = new Date();
        calender.setTime(iat);
        calender.add(Calendar.HOUR, 1);
        Date exp = calender.getTime();
        // JWTの作成
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY)
                .compact();
    }

    /**
     * ログイン処理としてユーザーID名と生パスワードからJWT（認証用トークン）を提供する。<br>
     * @param userIdName ユーザーID名
     * @param password 生パスワード（暗号化等されていないパスワードのこと）
     * @return JWT（認証用トークン）
     * @throws LoginException　ログイン処理に失敗した
     */
    public String loginAndGetToken(String userIdName, String password) throws LoginException {
    	UserDetailsImp user;
    	
    	//ログイン処理
    	try {
	    	user = service.loadUserByUserIdName(userIdName);
	    	
	    	if( !passwordEncoder.matches(password, user.getPassword()) )
	    		throw new BadCredentialsException("passwords is not matches");
    	}
    	catch(Exception e) {
    		throw new LoginException(e.getMessage());
    	}
    	
    	return createToken(user);
    }
    
    /**
     * 認証用トークンからユーザー情報を取得する
     * @param token JWT（認証用トークン）
     * @return 成功ならユーザー情報を返す。<br>
     *	 失敗ならnullを返す。
     */
    public Authentication getAuthentication(final String token) {
    	try {
		    final UserDetails userDetails = service.loadUserByUserId(getUserId(token));
		    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    	}
    	catch(Exception e) {
    		return null;
    	}
    }
    
    /**
     * 認証用トークンからユーザーIDを取得する
     * @param token 認証用トークン
     * @return ユーザーID
     */
    public Integer getUserId(final String token) {
    	return Integer.valueOf(
    			Jwts.parser()
	    			.setSigningKey(TOKEN_SECRET_KEY)
	        		.parseClaimsJws(token)
		        		.getBody()
		        		.getSubject());
    }

    /**
     * リクエストのヘッダーから認証用トークンを取得する
     * @param request リクエスト
     * @return 認証用トークン
     */
    public String resolveToken(final HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    /**
     * 認証用トークンについて、機関と、ユーザーが有効なものであるかをチェックする
     * @param token 認証用トークン
     * @return 成功ならtrue<br>
     *	 失敗ならfalse
     */
    public boolean validateToken(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(TOKEN_SECRET_KEY).parseClaimsJws(token);
            
            return !claims.getBody().getExpiration().before(new Date()) &&
            		service.loadUserByUserId(getUserId(token)) != null;
        } catch (Exception e) {
            return false;
        }
    }
}