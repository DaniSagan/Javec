package com.danisagan.javec;

import java.io.Serializable;

import com.danisagan.javec.Quadrant;
import com.danisagan.javec.Vec2d;
import com.danisagan.javec.Vec2f;
import com.danisagan.javec.Vec2i;

public class Vec2i implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5320809018291704250L;
	public int x;
	public int y;
	
	public Vec2i() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vec2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*public static Vec2i add(Vec2i v1, Vec2i v2) {
		return new Vec2i(v1.x + v2.x, v1.y + v2.y);
	}*/
	
	public static Vec2i add(Vec2i... vs) {
		Vec2i res = new Vec2i(0, 0);
		for(Vec2i v: vs) {
			res.x += v.x;
			res.y += v.y;
		}
		return res;
	}
	
	public static Vec2i subs(Vec2i v1, Vec2i v2) {
		return new Vec2i(v1.x - v2.x, v1.y - v2.y);
	}
	
	public static Vec2i cast(Vec2f v) {
		return new Vec2i((int)Math.floor(v.x), (int)Math.floor(v.y));
	}
	
	public static Vec2i cast(Vec2d v) {
		return new Vec2i((int)Math.floor(v.x), (int)Math.floor(v.y));
	}
	
	public Quadrant getQuadrant() {
		if(this.x >= 0 && this.y >= 0) return Quadrant.FIRST;
		else if(this.x < 0 && this.y >= 0) return Quadrant.SECOND;
		else if(this.x < 0 && this.y < 0) return Quadrant.THIRD;
		else return Quadrant.FOURTH;
	}
	
	@Override 
	public String toString() {
		return new String(String.valueOf(this.x) + ", " + String.valueOf(this.y));
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Vec2i))return false;
	    Vec2i vOther = (Vec2i)other;
	    return (this.x == vOther.x) && (this.y == vOther.y);
	}
	
	@Override
	public int hashCode() {
		return (this.x * 37 + this.y);
	}
}
