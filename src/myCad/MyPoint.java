package myCad;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyPoint implements Serializable {
	private int x = 0;
	private int y = 0;
	
	public int  getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
