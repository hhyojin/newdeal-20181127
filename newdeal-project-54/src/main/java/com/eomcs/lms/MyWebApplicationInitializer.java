package com.eomcs.lms;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//WebApplicationInitializer 구현체는 웹애플리케이션이 시작될 때 실행된다
public class MyWebApplicationInitializer implements WebApplicationInitializer{
  
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException{
      
    //이 메소드가 호출될 때 할 일
    
    //1) 프론트 컨트롤러가 사용할 Spring IoC Container를 준비한다
    AnnotationConfigWebApplicationContext iocContainer = new AnnotationConfigWebApplicationContext();
    iocContainer.register(AppConfig.class);
    iocContainer.refresh();
    
  //내가 정의한 빈이 몇 개인지
    System.out.println(iocContainer.getBeanDefinitionCount());
       
    //내가 정의한 빈의 이름이 무엇인지
    String[] names = iocContainer.getBeanDefinitionNames();
    for(String name : names) {
      //빈 이름의 클래스 정보를 꺼내 거기서 뭔 또 이름을?...
      System.out.printf("%s ===> %s\n", name, iocContainer.getBean(name).getClass().getName());
    }
    
    
    //2) 프론트 컨트롤러(DiapatcherServlet.내가 만든 거 말고 스프링의 서블릿 컨테이너)를 
    //   서블릿 컨테이너에 등록
    //-> 프론터 컨트롤러를 생성할 때 이 객체가 사용할 Spring IoC Container를 알려준다.
    //-> import 할 때 스프링 거 선택해야 함
    DispatcherServlet servlet = new DispatcherServlet(iocContainer);
    
    //-> 서블릿 컨테이너 위에서 만든 프론트 컨트롤러 서블릿을 등록한다
    //   서블릿 객체를 app이란 이름으로 등록. 이건 서블릿 등록 정보를 리턴함
    ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
    
    //-> 등록한 서블릿에 정보를 설정한다
    // - 웹 애플리케이션이 시작될 때 프론트 컨트롤러를 생성하라고 지정 
    registration.setLoadOnStartup(1);
    
    // - 프론트 컨트롤러의 url을 지정
    registration.addMapping("/app/*");
  }
}
