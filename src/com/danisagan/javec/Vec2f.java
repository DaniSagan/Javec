package com.danisagan.javec;

import java.io.Serializable;

import com.danisagan.javec.Quadrant;
import com.danisagan.javec.Vec2f;
import com.danisagan.javec.Vec2i;
import com.danisagan.javec.Vec3f;

public class Vec2f implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3805709688168936196L;
	public float x;
	public float y;
	
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/*public static Vec2f add(Vec2f v1, Vec2f v2) {
		return new Vec2f(v1.x + v2.x, v1.y + v2.y);
	}*/
	
	public static Vec2f add(Vec2f... vs) {
		Vec2f res = new Vec2f(0.f, 0.f);
		for(Vec2f v: vs) {
			res.x += v.x;
			res.y += v.y;
		}
		return res;
	}
	
	public static Vec2f subs(Vec2f v1, Vec2f v2) {
		return new Vec2f(v1.x - v2.x, v1.y - v2.y);
	}
	
	public static float dot(Vec2f v1, Vec2f v2) {
		return v1.x*v2.x + v1.y*v2.y;
	}
	
	public static Vec2f scale(Vec2f v, float scale) {
		return new Vec2f(v.x*scale, v.y*scale);
	}
	
	public static Vec2f polar(float length, float arg) {
		return new Vec2f(length*(float)Math.cos(arg), length*(float)Math.sin(arg));
	}
	
	public float length() {
		return (float)Math.sqrt(Math.pow((double)this.x, 2.) + Math.pow((double)this.y, 2.));
	}
	
	public float arg() {
		return (float)Math.atan2((double)this.y, (double)this.x);
	}
	
	public Vec3f unproject() {
		return new Vec3f(this.x, this.y, 0.f);
	}
	
	public Quadrant getQuadrant() {
		if(this.x >= 0.f && this.y >= 0.f) return Quadrant.FIRST;
		else if(this.x < 0.f && this.y >= 0.f) return Quadrant.SECOND;
		else if(this.x < 0.f && this.y < 0.f) return Quadrant.THIRD;
		else return Quadrant.FOURTH;
	}
	
	public static Vec2f cast(Vec2i v) {
		return new Vec2f(v.x, v.y);
	}
	
	public static Vec2f mult(SqMatrix2f m, Vec2f v) {
		return new Vec2f(
				m.get(0, 0)*v.x + m.get(0, 1)*v.y,
				m.get(1, 0)*v.x + m.get(1, 1)*v.y
		);
	}
	
	public static Vec2f interpolate(Vec2f v1, Vec2f v2, float k) {
		return Vec2f.add(v1, Vec2f.scale(Vec2f.subs(v2, v1), k));
	}
	
	@Override 
	public String toString() {
		return new String(String.valueOf(this.x) + ", " + String.valueOf(this.y));
	}
}