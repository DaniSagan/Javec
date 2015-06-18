package com.danisagan.javec;

import java.io.Serializable;

import com.danisagan.javec.Vec2f;
import com.danisagan.javec.Vec3f;

public class Vec3f implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5866303952860232071L;
	public float x;
	public float y;
	public float z;
	
	public Vec3f() {
		this.x = 0.f;
		this.y = 0.f;
		this.z = 0.f;
	}
	
	public Vec3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec2f projectXY() {
		return new Vec2f(this.x, this.y);
	}
	
	/*public static Vec3f add(Vec3f v1, Vec3f v2) {
		return new Vec3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}*/
	
	public static Vec3f add(Vec3f... vs) {
		Vec3f res = new Vec3f(0.f, 0.f, 0.f);
		for(Vec3f v: vs) {
			res.x += v.x;
			res.y += v.y;
			res.z += v.z;
		}
		return res;
	}
	
	public static Vec3f scale(Vec3f v, float scale) {
		return new Vec3f(v.x*scale, v.y*scale, v.z*scale);
	}
	
	public static Vec3f subs(Vec3f v1, Vec3f v2) {
		return new Vec3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
	}
	
	public float length() {
		return (float)Math.sqrt(Math.pow((double)this.x, 2.) + Math.pow((double)this.y, 2.) + Math.pow((double)this.z, 2.));
	}
	
	public static Vec3f mult(SqMatrix3f m, Vec3f v) {
		return new Vec3f(
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
