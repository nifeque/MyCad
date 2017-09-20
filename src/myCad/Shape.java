package myCad;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Shape implements Serializable {

	int x1,x2,y1,y2;   	     
	int  R,G,B;				 
	int type;				 
	String s1;				 
	String s2;			 

	public void draw(Graphics2D g2d ){} 
}

@SuppressWarnings("serial")
class Line extends Shape 
{
	public void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B)); 
		 
		g2d.drawLine(x1, y1, x2, y2); 
	}
}
@SuppressWarnings("serial")
class Rect extends Shape{ 
	public void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		
		g2d.drawRect(Math.min(x1, x2), Math.min(y2, y2), Math.abs(x1-x2), Math.abs(y1-y2));
	}
}

@SuppressWarnings("serial")
class Circle extends Shape{ 
	public void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		
		g2d.drawOval(Math.min(x1, x2), Math.min(y2, y2), Math.max(Math.abs(x1-x2), 
				Math.abs(y1-y2)), Math.max(Math.abs(x1-x2), Math.abs(y1-y2)));
	}
}

@SuppressWarnings("serial")
class Word extends Shape{ 
	public void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
	    if(s1 != null) {
	    	g2d.drawString(s1, x1,y1);
	    }
	}
}