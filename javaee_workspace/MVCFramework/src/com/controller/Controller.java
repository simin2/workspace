//��� ���� ��Ʈ�ѷ��� ����ų �� �ִ� �ֻ��� ��Ʈ�ѷ� Ŭ���� ����

package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�߻�Ŭ������ ������ ������, ���� Ŭ�������� �̹� �ٸ� ��ü�� ��ӹ޾��� ���� �����Ƿ�,
//���� ��ӿ� ���ѹ��� �������� �������̽��� �ξ� �� �����ϴ�
public interface Controller {
	
	//�˸��� ���� ��ü�� �� ��Ű��
	public void execute(HttpServletRequest reqeust, HttpServletResponse response) throws ServletException, IOException;
	
	//� �並 �����������
	public String getViewPage();
	
}