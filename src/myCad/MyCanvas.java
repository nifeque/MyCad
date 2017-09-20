package myCad;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class MyCanvas extends JPanel{
	private MyCad mycad;
    private LinkedList<Shape> shapeList = new LinkedList<Shape>();
    
    private int currentShape = 0;
    private int index = 0;
    private Color color = Color.black; 
	public MyCanvas(MyCad mycad) {
		this.mycad = mycad;
		
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setBackground(Color.white); 
		addMouseListener(new MousePass()); 
		addMouseMotionListener(new MouseMotion());
		createNewShape();
	}
    
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g; 
		for (int j=0; j<=index; j++) {
			draw(g2d, shapeList.get(j));
		}
	}
	
	void draw(Graphics2D g2d , Shape shape) {
		shape.draw(g2d); 
	}
	
	//新建一个图形的基本单元对象的程序段
	void createNewShape(){
		if (index == shapeList.size()) {
			shapeList.add(null);
		}
		Shape shape = null;
		switch(currentShape){
		case 0: 
			shape = new Line();
			break;
		case 1: 
			shape = new Rect();
			break;
		case 2: 
			shape = new Circle();
			break;
		case 3: 
			shape = new Word();
			break;
		}
		shapeList.set(index, shape);
		shapeList.get(index).type = currentShape;
		shapeList.get(index).setColor(color);
		
	}
	
	public Shape getShape(int index) {
		return shapeList.get(index);
	}
	
	public void addShape(Shape shape) {
		shapeList.add(shape);
	}
	
	public void removeAllShape() {
		index = shapeList.size();
		while (--index > 0) {
			shapeList.remove(index);
		}
		
	}
   
    public void setIndex(int index){ 
    	this.index = index;
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
		this.color = JColorChooser.showDialog(mycad, "setColor", color);
		try {
			shapeList.get(index).setColor(color);
		} catch (Exception e) {
			color = new Color(0, 0, 0);
		}
		shapeList.get(index).setColor(color);
	}

	
	private class MousePass extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			mycad.setStatusBar("coordinate: ["+e.getX()+" ,"+e.getY()+"]");
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			mycad.setStatusBar("coordinate: ["+e.getX()+" ,"+e.getY()+"]");
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX(), y = e.getY();
			mycad.setStatusBar("coordinate: ["+x+" ,"+y+"]");
			shapeList.get(index).getStartPoint().setPoint(x, y);
	
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX(), y = e.getY();
			mycad.setStatusBar("coordinate: ["+x+" ,"+y+"]");
			shapeList.get(index).getEndtPoint().setPoint(x, y);
			repaint();
			index++;
			createNewShape();
		}

}

	private class MouseMotion extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			int x = e.getX(), y = e.getY();
			shapeList.get(index).getEndtPoint().setPoint(x, y);
			repaint();
			mycad.setStatusBar("coordinate: ["+x+" ,"+y+"]");
		}
		
		public void mouseMoved(MouseEvent e) {
    	  mycad.setStatusBar("coordinate: ["+e.getX()+" ,"+e.getY()+"]");
		}
	}

}
