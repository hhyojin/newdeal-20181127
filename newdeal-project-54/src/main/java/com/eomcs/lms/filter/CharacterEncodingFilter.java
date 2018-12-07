package com.eomcs.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/HTML;charset=utf-8");
    
    //이 필터 다음에 또 다른 필터가 있다면 그 필터를 실행한다.
    //없다면 원래 목적지인 서블릿을 실행한다.
    chain.doFilter(request, response); 
  }  
}
