package com.danisagan.javec;

import java.io.Serializable;

import com.danisagan.javec.Vec2d;
import com.danisagan.javec.Vec3d;

public class Vec3d implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5241795080806219611L;
	public double x;
	public double y;
	public double z;
	
	public Vec3d() {
		this.x = 0.;
		this.y = 0.;
		this.z = 0.;
	}
	
	public Vec3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec2d projectXY() {
		return new Vec2d(this.x, this.y);
	}
	
	public static Vec3d add(Vec3d v1, Vec3d v2) {
		return new Vec3d(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}
	
	public static Vec3d add(Vec3d... vs) {
		Vec3d res = new Vec3d(0., 0., 0.);
		for(Vec3d v: vs) {
			res.x += v.x;
			res.y += v.y;
			res.z += v.z;
		}
		return res;
	}
	
	public static Vec3d scale(Vec3d v, double scale) {
		return new Vec3d(v.x*scale, v.y*scale, v.z*scale);
	}
	
	public static Vec3d subs(Vec3d v1, Vec3d v2) {
		return new Vec3d(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
	}
	
	public double length() {
		return Math.sqrt(Math.pow(this.x, 2.) + Math.pow(this.y, 2.) + Math.pow(this.z, 2.));
	}
	public static Vec3d mult(SqMatrix3d m, Vec3d v) {
		return new Vec3d(
				m.get(0, 0)*v.x + m.get(0, 1)*v.y + m.get(0, 2)*v.z,
				m.get(1, 0)*v.x + m.get(1, 1)*v.y + m.get(1, 2)*v.z,
				m.get(2, 0)*v.x + m.get(2, 1)*v.y + m.get(2, 2)*v.z
		);
	}
	
	@Override 
	public String toString() {
		return new String(String.valueOf(this.x) + ", " + String.valueOf(this.y) + ", " +  String.valueOf(this.z));
	}
}