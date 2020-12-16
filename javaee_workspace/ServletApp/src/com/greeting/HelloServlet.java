package com.greeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�������̶�? �ڹ� Ŭ���� �� ���� ������������ �ؼ� �� ����Ǿ��� �� �ִ� Ŭ����
public class HelloServlet extends HttpServlet {
	
	//�� �޼���� servlet�� �¾ ���Ŀ�, �ʱ�ȭ �۾��� ����
	//init()�޼���� ������(tomcat)�� ���� web container�� ȣ���ϸ�, �����̷κ��� ���� config��ü�� ���� �ʱ�ȭ�Ѵ�
	//��, servlet�� ���� �� �����ֱ� �޼����� ȣ���ڴ� tomcat�̴�
	public void init(ServletConfig config) throws ServletException {
		
		String msg = config.getInitParameter("msg");
		System.out.println("init() ȣ�� �� �Ѱܹ��� �Ķ���� ������ " + msg);
		
		//jsp ���� ��ü �� ���ø����̼��� ������ ������ ���� ��ü application
		ServletContext context = config.getServletContext();  //jsp������ appliction ���尴ü����!
		System.out.println(context.getRealPath("/"));
	}
	
	//�������� �ϴ� ������ ��, �ʱ�ȭ���� ��ġ��, Ŭ���̾�Ʈ�� ��û�� ó���� �غ� �� ���̸�,
	//Ŭ���̾�Ʈ�� ��û�� ó���ϴ� �޼��尡 �ٷ�, service �޼����̴�
	//service �޼��尡 ��û�� ó���Ϸ���, Ŭ���̾�Ʈ�� ��û ������ Ŭ���̾�Ʈ���� ���� ���������� �ʿ���ϱ� ������
	//service() �޼����� �Ű������� request, response��ü�� ���޵Ǿ�� �Ѵ�(�����̰� ��)
	//service()�� ��ũ��Ʋ�� �����̾��� ���̴�
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ŭ���̾�Ʈ�� ������ ��û ���� �� �Ķ���͸� ������� ����غ���
		String id = request.getParameter("id");
		
		//Ŭ���̾�Ʈ�� ����
		response.setContentType("text/html;charset=utf-8");  //���ÿ����̾��� ��
		PrintWriter out = response.getWriter();
		out.print("����� ������ �Ķ���ʹ� " + id);
	}
	
	//servlet�� �Ҹ��� �� ȣ��Ǵ� �޼���
	//servlet�� ������ �ڿ��� �ݳ��� �� (��Ʈ��, db close)� ���
	private void dist() {
		System.out.println("�� �׾�� �̤�");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("hello servlet do!!");
	}
}

