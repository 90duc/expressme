package cn.expressme.other.res;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Point  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7628631247871203392L;
	private double x;
    private double y;
    
    public  Point() {
		super();
	}
	public Point(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
