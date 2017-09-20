package myCad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MyCad extends JFrame implements ActionListener{
	/**
	 * 
	 */
	//定义菜单
	private static final long serialVersionUID = -1578205253542877715L;
	private JMenuBar menubar;
	private JMenu mfile, mediter, mhelp;
	private JMenuItem newfile, openfile, savefile, exit;
	private JMenuItem clear, revoke;
	private JMenuItem about, tips;
	//定义状态栏
	private JLabel statusbar;
	//定义画布内容
	private MyCanvas canvas;
	//定义帮助类
	private Help help;
	//定义文件对象
	private MyFile file;
	//定义图形栏
	private JPanel shapebar;
	private JToolBar toolbar;
	//定义按钮
	private JButton[] btShape;
	private JButton[] btTool;
	
	public MyCad() {
		//设定标题
		setTitle("MyCAD");
		setMenubar();
		setToolbar();
		setShapebar();
		
		//获取内容面板
		canvas = new MyCanvas(this);
		help = new Help(this);
		statusbar = new JLabel();
		
		Container con = getContentPane();
		con.add(toolbar,  BorderLayout.NORTH);
		con.add(shapebar, BorderLayout.WEST);
		con.add(canvas, BorderLayout.CENTER);
		con.add(statusbar, BorderLayout.SOUTH);
		
		//获取设备屏幕大小
		Toolkit toolkit = getToolkit();
		Dimension dim = toolkit.getScreenSize();
		
		//设置窗口大小
		setBounds((int)Math.rint(dim.getWidth()*0.17), (int)Math.rint(dim.getHeight() *0.15),
				(int)Math.rint(dim.getWidth()*0.66),(int)Math.rint(dim.getHeight() *0.7));
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		file = new MyFile(this, canvas);
	}
	
	private void setMenubar() {
		
		//初始化菜单
		menubar = new JMenuBar();
		menubar.setBackground(Color.WHITE);
		mfile = new JMenu("File");
		mediter = new JMenu("Editer");
		mhelp = new JMenu("Help");
		
		menubar.add(mfile);
		menubar.add(mediter);
		menubar.add(mhelp);
				
		setJMenuBar(menubar);
		
		//初始化菜单项
		newfile = new JMenuItem("New");
		openfile = new JMenuItem("Open file");
		savefile = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		
		mfile.add(newfile);
		mfile.add(openfile);
		mfile.add(savefile);
		mfile.add(exit);
		
		clear = new JMenuItem("Clear");
		revoke = new JMenuItem("Revoke");
		
		mediter.add(clear);
		mediter.add(revoke);
		
		about = new JMenuItem("About");
		tips = new JMenuItem("Tips");
		
		mhelp.add(about);
		mhelp.add(tips);
		
		//添加注册监听
		newfile.addActionListener(this);
		openfile.addActionListener(this);
		savefile.addActionListener(this);
		exit.addActionListener(this);
		clear.addActionListener(this);
		revoke.addActionListener(this);
		about.addActionListener(this);
		tips.addActionListener(this);
	}
	
	private void setToolbar() {
		toolbar = new JToolBar();
		toolbar.setBackground(new Color(225, 255, 255));
		String[] btToolName = {"Color", "Move", "Size"};
		btTool = new JButton[btToolName.length];
		
		for (int i=0; i < btTool.length; i++) {
			btTool[i] = new JButton(btToolName[i]);
			btTool[i].setBorderPainted(false);
			btTool[i].setBackground(Color.WHITE);
	    	toolbar.add(btTool[i]);
	    	btTool[i].addActionListener(this);
	    	btTool[i].setContentAreaFilled(false);
	    	
		}
		btTool[0].setBackground(Color.LIGHT_GRAY);
		toolbar.setFloatable(false);
		toolbar.setOrientation(0); 			// 0 表示工具栏横向放置
	}
	
	private void setShapebar() {
		shapebar = new JPanel();
		shapebar.setLayout(new FlowLayout(0, 0, 0));
		String[] btShapeName = {"Liner", "Rect", "Circ", "Word"};
		btShape = new JButton[btShapeName.length];
		
		for (int i=0; i < btShapeName.length; i++) {
			btShape[i] = new JButton(btShapeName[i]);
			btShape[i].setBorderPainted(true);
			btShape[i].setBackground(Color.WHITE);
	    	shapebar.add(btShape[i]);
	    	btShape[i].addActionListener(this);
	    	btShape[i].setPreferredSize(new Dimension(100, 40));
		}
		btShape[0].setBackground(Color.CYAN);
		shapebar.setBackground(Color.LIGHT_GRAY);
		shapebar.setPreferredSize(new Dimension(100, 100));
	}
	
	public void setStatusBar(String s) {
		statusbar.setText(s);
	}
	
	private void setbtShapeColor(JButton button) {
		for (int i = 0; i < this.btShape.length; i++) {
			btShape[i].setBackground(Color.WHITE);
		}
		button.setBackground(Color.CYAN);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btShape[0]) {
			canvas.setCurrentShape(0);
			canvas.repaint();
			setbtShapeColor(btShape[0]);
		}
		else if (e.getSource() == btShape[1]) {
			canvas.setCurrentShape(1);
			canvas.createNewShape();
			canvas.repaint();
			setbtShapeColor(btShape[1]);
		}
		else if (e.getSource() == btShape[2]) {
			canvas.setCurrentShape(2);
			canvas.createNewShape();
			canvas.repaint();
			setbtShapeColor(btShape[2]);
		}
		else if (e.getSource() == btShape[3]) {
			canvas.setCurrentShape(3);
			canvas.createNewShape();
			canvas.repaint();
			setbtShapeColor(btShape[3]);
		}
		if (e.getSource() == newfile) {
			file.newFile();
		}
		else if(e.getSource() == openfile) {
			file.openFile();
		}
		else if(e.getSource() == savefile) {
			file.saveFile();
		}
		else if(e.getSource() == exit) {
			System.exit(0);
		}
		else if(e.getSource() == btTool[0]) {
			canvas.chooseColor();
		}
		else if(e.getSource() == about ) {
			help.aboutHelp();
		}
		else if(e.getSource() == tips) {
			help.tipsHelp();
		}
		//...
	}
	
	public static void main(String[] argv) {
		MyCad my = new MyCad();
	}
}
