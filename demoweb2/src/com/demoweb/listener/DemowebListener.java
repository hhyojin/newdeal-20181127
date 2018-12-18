package com.demoweb.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//@WebListener : Container에게 이 클래스가 리스너임을 알려주는  기능 수행
@WebListener
public class DemowebListener 
	implements ServletContextListener,//Web Application Start, Stop Event 
	HttpSessionListener {//Session Start, Stop Event

    public void contextInitialized(ServletContextEvent arg0) {
    	System.out.println("contextInitialized");
        //Application Start
    	ServletContext context = arg0.getServletContext();    	
    	
    	//만약 존재한다면 파일 또는 DB에서 접속자 카운트 정보를 읽어서 초기화
    	ServletContext application = arg0.getServletContext();
    	String path = application.getRealPath("counter.txt");
    	//System.out.println(path);
    	int total = 0;
    	try {
    	File file = new File(path);
    	if (file.exists()) {
    		BufferedReader reader = new BufferedReader(
    			new InputStreamReader(
    				new FileInputStream(file)));
    		total = Integer.parseInt(reader.readLine());
    		reader.close();
    	}
    	} catch (Exception ex) {}
    	
    	
    	
    	context.setAttribute("total", total);
    	context.setAttribute("current", 0);
    }
    public void sessionCreated(HttpSessionEvent arg0) {
    	System.out.println("sessionCreated");
        //Session Start -> 접속자 정보 갱신 (total, current를 1증가)
    	ServletContext context = arg0.getSession().getServletContext();
    	int total = (int)context.getAttribute("total");
    	int current = (int)context.getAttribute("current");
    	
    	context.setAttribute("total", total + 1);
    	context.setAttribute("current", current + 1);
    }
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	System.out.println("sessionDestroyed : 세션 만료 타입 기준");
        //Session End -> 접속자 정보 갱신 (total, current를 1감소)
    	ServletContext context = arg0.getSession().getServletContext();    	
    	int current = (int)context.getAttribute("current");
    	
    	context.setAttribute("current", current - 1);
    }	
    public void contextDestroyed(ServletContextEvent arg0) {
        // Application End
    	// 접속자 카운트 정보를 파일 또는 DB에 저장
    	try {
    	//getRealPath : 가상경로 -> 물리경로
    	String path = 
    		arg0.getServletContext().getRealPath("counter.txt");
    	BufferedWriter writer = new BufferedWriter(
    		new OutputStreamWriter(
    			new FileOutputStream(path)));
    	writer.write(
    		arg0.getServletContext().getAttribute("total")
    		+ "\r\n");
    	writer.flush();
    	writer.close();
    	} catch (Exception ex) {}
    }
	
}












