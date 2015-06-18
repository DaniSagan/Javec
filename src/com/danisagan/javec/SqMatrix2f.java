package com.danisagan.javec;

public class SqMatrix2f {
	private float[] data = new float[4];
	
	public SqMatrix2f() {
		set(0, 0, 0.f);
		set(0, 1, 0.f);
		set(1, 0, 0.f);
		set(1, 1, 0.f);
	}
	
	public SqMatrix2f(float value) {
		set(0, 0, value);
		set(0, 1, value);
		set(1, 0, value);
		set(1, 1, value);
	}
	
	public static SqMatrix2f fromRows(float[] row0, float[] row1) {
		if(row0.length != 2 || row1.length != 2) {
			throw new IllegalArgumentException("Arguments must have length of 2.");
		}
		SqMatrix2f result = new SqMatrix2f();
		result.set(0, 0, row0[0]);
		result.set(0, 1, row0[1]);
		result.set(1, 0, row1[0]);
		result.set(1, 1, row1[1]);
		return result;
	}
	
	public static SqMatrix2f rotationMatrix(float angle) {
		return SqMatrix2f.fromRows(
				new float[] {(float)Math.cos(angle), -(float)Math.sin(angle)},
				new float[] {(float)Math.sin(angle), (float)Math.cos(angle)}
		);
	}
	
	public static SqMatrix2f id() {
		SqMatrix2f result = new SqMatrix2f();
		result.set(0, 0, 1.f);
		result.set(1, 1, 1.f);
		return result;
	}
	
	public void set(int row, int col, float value) {
		this.data[col + 2*row] = value;
	}
	
	public float get(int row, int col) {
		return this.data[col + 2*row];
	}
	
	public float determinant() {
		return this.get(0, 0)*this.get(1, 1) - this.get(0, 1)*this.get(0, 1);
	}
	
	public SqMatrix2f inv() {
		SqMatrix2f result = new SqMatrix2f();
		float det = determinant();
		result.set(0, 0, get(1, 1)/det);
		result.set(0, 1, -get(0, 1)/det);
		result.set(1, 0, -get(1, 0)/det);
		result.set(1, 1, get(0, 0)/det);
		return result;
	}
	
	public static SqMatrix2f add(SqMatrix2f... ms) {
		SqMatrix2f result = new SqMatrix2f();
		for(SqMatrix2f m: ms) {
			result.set(0, 0, result.get(0, 0) + m.get(0, 0));
			result.set(0, 1, result.get(0, 1) + m.get(0, 1));
			result.set(1, 0, result.get(1, 0) + m.get(1, 0));
			result.set(1, 1, result.get(1, 1) + m.get(1, 1));
		}
		return result;
	}
	
	public static SqMatrix2f subs(SqMatrix2f m1, SqMatrix2f m2) {
		return SqMatrix2f.add(m1, SqMatrix2f.mult(-1, m2));
	}
	
	private static SqMatrix2f simpleMult(SqMatrix2f m1, SqMatrix2f m2) {
		SqMatrix2f result = new SqMatrix2f();
		result.set(0, 0, m1.get(0, 0)*m2.get(0, 0) + m1.get(0, 1)*m2.get(1, 0));
		result.set(0, 1, m1.get(0, 0)*m2.get(0, 1) + m1.get(0, 1)*m2.get(1, 1));
		result.set(1, 0, m1.get(1, 0)*m2.get(0, 0) + m1.get(1, 1)*m2.get(1, 0));
		result.set(1, 1, m1.get(1, 0)*m2.get(0, 1) + m1.get(1, 1)*m2.get(1, 1));
		return result;
	}
	
	public static SqMatrix2f mult(SqMatrix2f... ms) {
		SqMatrix2f result = SqMatrix2f.id();
		for(SqMatrix2f m: ms) {
			result = SqMatrix2f.simpleMult(result, m);
		}
		return result;
	}
	
	public static SqMatrix2f mult(float x, SqMatrix2f m) {
		SqMatrix2f result = new SqMatrix2f();
		result.set(0, 0, m.get(0, 0)*x);
		result.set(0, 1, m.get(0, 1)*x);
		result.set(1, 0, m.get(1, 0)*x);
		result.set(1, 1, m.get(1, 1)*x);
		return result;
	}
}
