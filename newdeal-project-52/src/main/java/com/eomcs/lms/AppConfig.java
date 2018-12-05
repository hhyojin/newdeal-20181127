package com.eomcs.lms;

import java.util.Scanner;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//Spring IoC 컨테이너에게 패키지 이름을 알려주면 
//그 패키지를 뒤져서 @Component가 붙은 클래스에 대해
//인스턴스를 자동으로 생성해줌
@ComponentScan("com.eomcs.lms")

//Spring IoC 컨테이너에게 프로퍼티 파일을 읽으라고 로딩할 것을 명령
@PropertySource("classpath:/com/eomcs/lms/conf/jdbc.properties")
public class AppConfig {

  //Spring IoC 컨테이너가 로딩한 프로퍼티 정보 가져오기
  //스프링 컨테이너에게 로딩한 프로퍼티 값 중에서 jdbc.driver라는 이름을 가진 값을 꺼내
  //jdbcDriver 변수에 넣어줘
  //env를 이용해 직접 값을 꺼내는 방법도 있음
  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.user}")
  String jdbcUsername;

  @Value("${jdbc.passwrod}")
  String jdbcPassword;

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(this.jdbcDriver);
    dataSource.setUrl(this.jdbcUrl);
    dataSource.setUsername(this.jdbcUsername);
    dataSource.setPassword(this.jdbcPassword);
    return dataSource;
  }

  //트랜잭션 객체를 생성할 때 기본 이름으로 transactionMamager라고 설정할 것
  //다른 이름으로 설정하면 트랜잭션 관련하여 다른 객체를 생성할 때
  //그 객체가 트랜잭션 관리자를 자동으로 찾지 못한다★★★
  public PlatformTransactionManager transactionMamager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }


  //Spring IoC 컨테이너에게 이 메소드를 호출하여 리턴값을 보관하라고 표시하는 것
  //리턴값을 저장할 때 사용할 이름을 따로 지정하지 않으면 메소드 이름으로 저장됨
  //그래서 이런 메소드의 이름은 보통 동사로 시작하지 않고, 객체의 이름인 명사형태로 명명
  @Bean 
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext iocContainer) throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

    //DataSource(DB 커넥션 풀) 객체 주입
    factoryBean.setDataSource(dataSource);

    //Domain 클래스(VO()의 별명 자동 생성
    factoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");

    //SQL 매퍼 파일 로딩
    // -> SQL 매퍼 파일의 위치 정보를 Resource 객체에 담아서 넘겨야 한다
    // -> Resource 객체는 Spring IoC 컨테이너를 통해 만들 수 있다
    // -> Spring IoC 객체를 얻는 방법: 이 메소드의 파라미터에 달라고 요청
    //                                (파라미터에 변수 선언하면 자동으로 줌)
    factoryBean.setMapperLocations(iocContainer.getResources("classpath:/com/eomcs/lms/mapper/*Mapper.xml"));

    return factoryBean.getObject();

  }

  @Bean
  public Scanner keyboard() {
    return new Scanner(System.in);
  }

}
