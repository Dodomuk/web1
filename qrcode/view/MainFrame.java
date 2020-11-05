package com.kh.qrcode.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.kh.qrcode.maker.CalculationMaker;
import com.kh.qrcode.maker.QRCodeMaker;
import java.awt.Panel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private final Action ButtonAction = new SwingAction();
	private JTextArea urlArea;
	private JTextArea titleArea;
	private JTextArea numArea;
	private JTextField numField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QRCodeMaker");
		lblNewLabel.setFont(new Font("Bodoni MT", Font.PLAIN, 17));
		lblNewLabel.setBounds(14, 12, 135, 53);
		contentPane.add(lblNewLabel);
		
		JMenu mnNewMenu = new JMenu("QR소스 모음");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		mnNewMenu.setBounds(137, 12, 127, 24);
		contentPane.add(mnNewMenu);
		
		JLabel label = new JLabel("네이버");
		mnNewMenu.add(label);
		
		JButton btnNewButton = new JButton("파일생성");
		btnNewButton.setAction(ButtonAction);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(40, 198, 105, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("초기화");
		btnNewButton_2.addMouseListener(new MouseAdapter() { //익명 클래스
			@Override
			public void mouseClicked(MouseEvent e) {
				urlArea.setText("");
				titleArea.setText("");
				numArea.setText("");
				numField.setText("");
			}
		});
		btnNewButton_2.setBackground(Color.YELLOW);
		btnNewButton_2.setBounds(159, 198, 105, 43);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("URL소스");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(53, 57, 62, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("TITLE");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setBounds(194, 57, 42, 24);
		contentPane.add(lblNewLabel_1_1);
		
		urlArea = new JTextArea();
		urlArea.setBounds(32, 77, 113, 109);
		contentPane.add(urlArea);
		
		titleArea = new JTextArea();
		titleArea.setBounds(159, 77, 113, 109);
		contentPane.add(titleArea);
		
		JButton btnNewButton_1 = new JButton("계산기");
		btnNewButton_1.addMouseListener(new MouseAdapter() { //계산기!!!
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CalculationMaker cm = new CalculationMaker();
				
				String inputNum = numArea.getText();		
				String[] numArr = inputNum.split("\n");
				char operator = numField.getText().charAt(0);
					cm.Calculator(Integer.parseInt(numArr[0]), Integer.parseInt(numArr[1]), operator);		
			}
		});
		
		btnNewButton_1.setBackground(Color.GREEN);
		btnNewButton_1.setBounds(293, 198, 125, 43);
		contentPane.add(btnNewButton_1);
		
		numArea = new JTextArea();
		numArea.setBackground(Color.PINK);
		numArea.setBounds(314, 89, 93, 43);
		contentPane.add(numArea);
		
		JLabel lblNewLabel_2 = new JLabel("숫자");
		lblNewLabel_2.setBounds(345, 63, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		numField = new JTextField();
		numField.setBackground(Color.LIGHT_GRAY);
		numField.setBounds(302, 162, 116, 24);
		contentPane.add(numField);
		numField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("연산자");
		lblNewLabel_3.setBounds(334, 144, 62, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("구글");
		lblNewLabel_4.setBounds(303, 63, 28, 18);
		contentPane.add(lblNewLabel_4);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "파일생성");
			putValue(SHORT_DESCRIPTION, "QR코드를 생성해준다");
		}
		public void actionPerformed(ActionEvent e) {
			
			QRCodeMaker qrcm = new QRCodeMaker();
			
			String url = urlArea.getText();
			String title = titleArea.getText();

			
			String[] urlArr = url.split("\n");
			String[] titleArr = title.split("\n");
			
			//trim: 문자열의 앞뒤 공백을 제거해준다!
			url = url.trim();
			title = title.trim();
			
		if (titleArr.length < urlArr.length) {
			
			for (int i = 0; i < titleArr.length; i++) {
						qrcm.makeQR(urlArr[i],titleArr[i]);
			}
			
		}else {
			for (int i = 0; i < urlArr.length; i++) {
				qrcm.makeQR(urlArr[i],titleArr[i]); 
			}
		}
			
			
				
		}
	}
}
