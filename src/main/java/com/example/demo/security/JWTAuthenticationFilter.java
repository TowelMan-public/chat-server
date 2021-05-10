package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * このサーバにアクセスしてきたときに、かなり最初の方に実行する共通処理を書くFilterクラスの一つである。<br>
 * GenericFilterBeanを実装することにより、認証されているかのフィルタリングを行う。つまり、認証されているかを実際に判定するところである。<br>
 * 
 * @see org.springframework.web.filter.GenericFilterBean
 */
@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

    // トークンを検証するためのProvider
	@Autowired
    private JWTProvider provider;

    /**
     * 認証されているかのフィルタリング処理を実際に書くところ。
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) 
    		throws IOException, ServletException {
    	
        final String token = provider.resolveToken((HttpServletRequest) request);
        
        if (token != null && provider.validateToken(token)) {
            final Authentication auth = provider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        filterChain.doFilter(request, response);
    }
}