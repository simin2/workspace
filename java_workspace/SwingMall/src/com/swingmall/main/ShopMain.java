package com.swingmall.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.swingmall.board.QnA;
import com.swingmall.cart.Cart;
import com.swingmall.home.Home;
import com.swingmall.member.Login;
import com.swingmall.member.MyPage;
import com.swingmall.member.RegistForm;
import com.swingmall.product.Product;
import com.swingmall.product.ProductDetail;
import com.swingmall.util.db.DBManager;

public class ShopMain extends JFrame{
	//���
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	
	//�ֻ��� ������
	public static final int HOME= 0;
	public static final int PRODUCT = 1;
	public static final int QNA = 2;
	public static final int MYPAGE = 3;
	public static final int LOGIN = 4;
	public static final int PRODUCT_DETAIL = 5;
	public static final int MEMBER_REGIST = 6;
	public static final int CART = 7;
	
	JPanel user_container;  //������, ����� ȭ���� ���� ���� �� �ִ� �����̳�
	JPanel p_content;  //���⿡ ��� �������� �̸� �پ��� ���� ����
		//���� showPage�޼���� ������ ���� ���� ����
	JPanel p_navi;  //�� ����Ʈ�� �� �޴��� ������ �����̳� �г�
	
	String[] navi_title = {"Home", "Product", "QnA", "MyPage", "Login"}; 
	public JButton[] navi = new JButton[navi_title.length];  //[][][][][] �迭 ����
	
	//������ ����
	Page[] page = new Page[8];  //��������
	
	JLabel la_footer;  //������ �ϴ��� copyright����
	private DBManager dbManager;
	private Connection con;

	//�α��� �������� ���θ� �� �� �ִ� ����
	private boolean hasSession = false;
	
	public ShopMain() {
		dbManager= new DBManager();
		user_container = new JPanel();
		p_content = new JPanel();
		p_navi = new JPanel();
		la_footer = new JLabel("SwingMall All reights reserved", SwingConstants.CENTER);
		
		con = dbManager.connect();
		if(con == null) {
			JOptionPane.showMessageDialog(this, "�����ͺ��̽��� ������ �� �����ϴ�.");
			System.exit(0);
		}else {
			this.setTitle("SwingShop�� ���� ���� ȯ���մϴ�.");
		}

		//���� �׺���̼� ����
		for(int i=0; i<navi.length; i++) {
			navi[i] = new JButton(navi_title[i]);
			p_navi.add(navi[i]);  //�гο� �׺���̼� ����
		}
		
		//������ ����
		page[0] = new Home(this);
		page[1] = new Product(this);
		page[2] = new QnA(this);
		page[3] = new MyPage(this);
		page[4] = new Login(this);
		page[5] = new ProductDetail(this);
		page[6] = new RegistForm(this);  //ȸ������ ��
		page[7] = new Cart(this);
		
		//��Ÿ��
		user_container.setPreferredSize(new Dimension(WIDTH, HEIGHT-50));
		user_container.setBackground(Color.WHITE);
		la_footer.setPreferredSize(new Dimension(WIDTH, 50));
		la_footer.setFont(new Font("Arial Black", Font.BOLD, 19));

		//����
		user_container.setLayout(new BorderLayout());
		user_container.add(p_navi, BorderLayout.NORTH);
		for(int i=0; i<page.length; i++) {
			p_content.add(page[i]);			
		}  //��� �������� �̸� �ٿ�����
		
		user_container.add(p_content);
		
		this.add(user_container);
		this.add(la_footer, BorderLayout.SOUTH);
		
		setSize(1200, 900);
		setVisible(true);
		setLocationRelativeTo(null);
		
		//�����Ӱ� ������ ����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
				System.exit(0);
			}
		});
		
		//�׺���̼� ��ư�� ������ ����
		for(int i=0; i<navi.length; i++) {
			navi[i].addActionListener((e)-> {
				Object obj = e.getSource();
				if(obj == navi[0]) {
					showPage(0);
				}else if(obj == navi[1]) {
					showPage(1);
				}else if(obj == navi[2]) {
					showPage(2);
				}else if(obj == navi[3]) {
					//mypage�� ������ �����༭�� �ȵǰ�, �α����� ������Ը� ������� ��
					if(hasSession == false) {
						JOptionPane.showMessageDialog(ShopMain.this, "�α����� �ʿ��� �����Դϴ�.");
					}else {
						showPage(3);						
					}
				}else if(obj==navi[4]) {
					//�α����� ��û����, �α׾ƿ��� ��û������ ��������
					//hasSession�� ���� true �϶��� �α����� �����̹Ƿ�, �α׾ƿ��� ��û�ؾ� �Ѵ�..
					if(hasSession) {
						int ans = JOptionPane.showConfirmDialog(ShopMain.this, "�α׾ƿ� �Ͻðڽ��ϱ�?");
						
						if(ans==JOptionPane.OK_OPTION) {  //'��'�� ��������
							Login loginPage = (Login)page[ShopMain.LOGIN];
							loginPage.logout();
						}
					}else {
						showPage(4);  //�α���						
					}
				}
			});						
		}
	}
	
	//������ �������� �� ������ �������� �����ϴ� �޼���
	public void showPage(int showIndex) {  //�Ű������δ� �����ְ� ���� ������ �ѹ��� �Ѱ��ش�
		for(int i=0; i<page.length; i++) {  //��� �������� �������
			if(i == showIndex) {
				page[i].setVisible(true);  //���̰� �� ������
			}else {
				page[i].setVisible(false);  //�� ���ư� �� ������
			}			
		}
	}
	
	//������ ����Ʈ�� ������ ����Ʈ�� �����ϴ� �޼���
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj);  //���ŵ� ��
		this.add(addObj);  //������ ��
		((JPanel)addObj).updateUI();
	}
	
	public DBManager getDbManager() {
		return dbManager;
	}	
	public Connection getCon() {
		return con;
	}
	public Page[] getPage() {
		return page;
	}
	public boolean isHasSession() {
		return hasSession;
	}
	public void setHasSession(boolean hasSession) {
		this.hasSession = hasSession;
	}

	public static void main(String[] args) {
		new ShopMain();
	}

}