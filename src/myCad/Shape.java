package myCad;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Shape implements Serializable {
	protected Color color;
	protected MyPoint start = new MyPoint(), end = new MyPoint();
	
	int type;				 
	String s;				 

	public void draw(Graphics2D g2d ) {}
	
	public MyPoint getStartPoint() {
		return start;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public MyPoint getEndtPoint() {
		return end;
	}

}
	
@SuppressWarnings("serial")
class Line extends Shape 
{	
	public void draw(Graphics2D g2d) {
		g2d.setPaint(color); 
		g2d.drawLine(start.getX(), start.getY(), end.getX(), end.getY()); 
	}
	
}

@SuppressWarnings("serial")
class Rect extends Shape{ 
	
	public void draw(Graphics2D g2d ){
		g2d.setPaint(color);
		
		g2d.drawRect(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()),
				Math.abs(start.getX()-end.getX()), Math.abs(start.getY()-end.getY()));
	}
}

@SuppressWarnings("serial")
class Circle extends Shape{ 
	public void draw(Graphics2D g2d ){
		g2d.setPaint(color);
		
		g2d.drawOval(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()), 
				Math.max(Math.abs(start.getX()-end.getX()), Math.abs(start.getY()-end.getY())), 
				Math.max(Math.abs(start.getX()-end.getX()), Math.abs(start.getY()-end.getY())));
	}
}

@SuppressWarnings("serial")
class Word extends Shape{ 
	
	public void draw(Graphics2D g2d ){
		g2d.setPaint(color);
	    if(s != null) {
	    	g2d.drawString(s, start.getX(),start.getX());
	    }
	}
}