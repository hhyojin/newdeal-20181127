package com.eomcs.lms.listner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.AppConfig;


//규칙에 따라 리스너를 만들었으면 톰캣 서버에 등록해야 실행됨
@WebListener
public class ContextLoaderListner implements ServletContextListener {
  //웹 애플리케이션이 시작되거나 종료될 때 호출되는 메소드를 정의한 것

  AnnotationConfigApplicationContext iocContainer; //자원 해제 위해 공유 자원으로 만듦
  
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("웹 애플리케이션이 시작됨"); //톰캣서버가 시작될 때 웹어플리케이션 시작 & iocContainer 생성

    //AppConfig 클래스가 메모리에 로딩되어 있지 않다면, Spring IoC 컨테이너 준비하기
    iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig.class);

    //내가 정의한 빈이 몇 개인지
    System.out.println(iocContainer.getBeanDefinitionCount());
       
    //내가 정의한 빈의 이름이 무엇인지
    String[] names = iocContainer.getBeanDefinitionNames();
    for(String name : names) {
      //빈 이름의 클래스 정보를 꺼내 거기서 뭔 또 이름을?...
      System.out.printf("%s ===> %s\n", name, iocContainer.getBean(name).getClass().getName());
    }

    //Spring IoC 컨테이너를 서블릿이 사용할 수 있도록
    //'SeveltContext'라는 보관소에 저장한다
    ServletContext sc = sce.getServletContext();
    sc.setAttribute("iocContainer", iocContainer);

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("웹 애플리케이션이 종료됨"); //톰캣서버 종료시 웹앱 종료 & iocContainer 소멸
    
    //Spring IoC 컨테이너의 자원을 해제시킨다.
    this.iocContainer.close();
  }  

}




