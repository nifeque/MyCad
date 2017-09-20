package myCad;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class MyCanvas extends JPanel{
	private MyCad mycad;
    Shape[] itemList =new Shape[5000];
    
    private int currentShape = 0;
    private int index = 0;
    private Color color = Color.black; 
    private int R,G,B; 
	public MyCanvas(MyCad mycad) {
		this.mycad = mycad;
		
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setBackground(Color.white); 
		addMouseListener(new MousePass()); 
		addMouseMotionListener(new MouseMotion());
		 createNewitem();
		
	}
    
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g; 
		int  j = 0;
		while(j<=index)
		{
			draw(g2d, itemList[j]);
			j++;
	    }
		
	}
	void draw(Graphics2D g2d , Shape shape) {
		shape.draw(g2d); 
	}
	
	//新建一个图形的基本单元对象的程序段
	void createNewitem(){
		switch(currentShape){
		case 0: 
			itemList[index] = new Line();
			break;
		case 1: 
			itemList[index] = new Rect();
			break;
		case 2: 
			itemList[index] = new Circle();
			break;
		case 3: 
			itemList[index] = new Word();
			break;
		}	
		itemList[index].type = currentShape;
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
		
	}
   
    public void setIndex(int x){ 
    	index = x;
    }
    
    public int getIndex(){ 
    	return index ;
    }
    
    public void setColor(Color color) {
    	this.color = color; 
    }
    
    public void setCurrentShape(int i) {
		this.currentShape = i;
	}
    
	public void chooseColor() {
		color = JColorChooser.showDialog(mycad, "setColor", color);
		try {
			R = color.getRed();
			G = color.getGreen();
			B = color.getBlue();
		} catch (Exception e) {
			R = 0;
			G = 0;
			B = 0;
		}
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
	}

	
	private class MousePass extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent me) {
			mycad.setStratBar("coordinate: ["+me.getX()+" ,"+me.getY()+"]");
		}
	
		@Override
		public void mouseExited(MouseEvent me) {
			mycad.setStratBar("coordinate: ["+me.getX()+" ,"+me.getY()+"]");
		}
	
		@Override
		public void mousePressed(MouseEvent me) {
			mycad.setStratBar("coordinate: ["+me.getX()+" ,"+me.getY()+"]");
			
			int x = me.getX();
			int y = me.getY();
			itemList[index].x1  = x;
			itemList[index].y1  = y;
	
		}
	
		@Override
		public void mouseReleased(MouseEvent me) {
			mycad.setStratBar("coordinate: ["+me.getX()+" ,"+me.getY()+"]");
			itemList[index].x2 = me.getX();
			itemList[index].y2 = me.getY();
			repaint();
			index++;
			createNewitem();
		}

}

	private class MouseMotion extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent me) {
			itemList[index].x2 = me.getX();
			itemList[index].y2 = me.getY();
			repaint();
			mycad.setStratBar("coordinate: ["+me.getX()+" ,"+me.getY()+"]");
		}
		
		public void mouseMoved(MouseEvent me) {
    	  mycad.setStratBar("coordinate: ["+me.getX()+" ,"+me.getY()+"]");
		}
	}

}
