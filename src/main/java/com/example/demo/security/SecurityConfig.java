package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.example.demo.configurer.UrlConfing;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// ログイン以降の認証認可のためのFilter
	@Autowired
    JWTAuthenticationFilter filter;
	@Autowired
	AuthenticationEntryPointImp authenticationEntryPointImp;

    @Override
    public void configure(WebSecurity web) throws Exception { 
    	web
    		.ignoring()
	    		.antMatchers(UrlConfing.ROOT_URL + "/user/login")
	            .antMatchers(UrlConfing.ROOT_URL + "/user/insert");
    }
    
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
	                .httpBasic().disable()
	                .csrf().disable()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
	                //ログイン不要でアクセス可能に設定
	                .antMatchers(UrlConfing.ROOT_URL + "/user/login").permitAll()
	                .antMatchers(UrlConfing.ROOT_URL + "/user/insert").permitAll()
	                //上記以外は直リンク禁止
	                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                	.authenticationEntryPoint(authenticationEntryPointImp)
                .and()
                // デフォルトのFilter設定を変える
                .addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    //パスワードのアルゴリズムをBCryptに設定
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public HttpStatusReturningLogoutSuccessHandler httpStatusReturningLogoutSuccessHandler() {
    	return new HttpStatusReturningLogoutSuccessHandler();
    }
    
}