package com.danisagan.javec;

import java.io.Serializable;

import com.danisagan.javec.Vec2d;

public class Vec2d implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1150276375430438197L;
	public double x;
	public double y;
	
	public Vec2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/*public static Vec2d add(Vec2d v1, Vec2d v2) {
		return new Vec2d(v1.x + v2.x, v1.y + v2.y);
	}*/
	
	public static Vec2d add(Vec2d... vs) {
		Vec2d res = new Vec2d(0., 0.);
		for(Vec2d v: vs) {
			res.x += v.x;
			res.y += v.y;
		}
		return res;
	}
	
	public static Vec2d subs(Vec2d v1, Vec2d v2) {
		return new Vec2d(v1.x - v2.x, v1.y - v2.y);
	}
	
	public static double dot(Vec2d v1, Vec2d v2) {
		return v1.x*v2.x + v1.y*v2.y;
	}
	
	public static Vec2d scale(Vec2d v, double scale) {
		return new Vec2d(v.x*scale, v.y*scale);
	}
	
	public double length() {
		return Math.sqrt(Math.pow(this.x, 2.) + Math.pow(this.y, 2.));
	}
	
	public double arg() {
		return Math.atan2(this.y, this.x);
	}
	
	public static Vec2d mult(SqMatrix2d m, Vec2d v) {
		return new Vec2d(
				m.get(0, 0)*v.x + m.get(0, 1)*v.y,
				m.get(1, 0)*v.x + m.get(1, 1)*v.y
		);
	}
	
	public static Vec2d cast(Vec2f v) {
		return new Vec2d(v.x, v.y);
	}
	
	public static Vec2d interpolate(Vec2d v1, Vec2d v2, double k) {
		return Vec2d.add(v1, Vec2d.scale(Vec2d.subs(v2, v1), k));
	}
	
	@Override 
	public String toString() {
		return new String(String.valueOf(this.x) + ", " + String.valueOf(this.y));
	}
}
