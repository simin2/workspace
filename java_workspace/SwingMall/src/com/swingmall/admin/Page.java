//��� �������� ���������� �������� �Ӽ�, �޼������ �����ϱ����� �ֻ��� ������ Ŭ����
//���� Home, Product, Q&A, Mypage, Login ���� ����������
//�� Ŭ��������ӹ��� ���, �ڵ带 �ߓ��ؼ� �ۼ����� �ʾƵ� �ȴ�

package com.swingmall.admin;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	private AdminMain adminMain;
	
	public AdminMain getAdminMain() {
		return adminMain;
	}
	
	public Page(AdminMain adminMain) {
		this.adminMain = adminMain;
		this.setPreferredSize(new Dimension(AdminMain.WIDTH, AdminMain.HEIGHT-100));
	}
}