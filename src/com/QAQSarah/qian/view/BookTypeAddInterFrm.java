package com.QAQSarah.qian.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.QAQSarah.qian.Util.DBUtil;
import com.QAQSarah.qian.Util.StringUtil;
import com.QAQSarah.qian.dao.BookTypeDao;
import com.QAQSarah.qian.model.BookType;



public class BookTypeAddInterFrm extends JInternalFrame {
	
	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;
	
	private DBUtil dbUtil=new DBUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInterFrm frame = new BookTypeAddInterFrm();
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
	public BookTypeAddInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeAddActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(btnNewButton))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1)
								.addComponent(bookTypeDescTxt))))
					.addContainerGap(69, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(28))
		);
		getContentPane().setLayout(groupLayout);
		
		// �����ı���߿�
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));

	}
	
	/**
	 * ͼ���������¼�����
	 * @param e
	 */
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		String bookTypeName=this.bookTypeNameTxt.getText();
		String bookTypeDesc=this.bookTypeDescTxt.getText();
		if(StringUtil.isEmpty(bookTypeName)){
			JOptionPane.showMessageDialog(null, "ͼ��������Ʋ���Ϊ�գ�");
			return;
		}
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=bookTypeDao.add(con, bookType);
			if(n==1){
				JOptionPane.showMessageDialog(null, "ͼ�������ӳɹ���");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "ͼ��������ʧ�ܣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ͼ��������ʧ�ܣ�");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * �����¼�����
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}

	/**
	 * ���ñ�
	 */
	private void resetValue(){
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
}
