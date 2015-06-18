package com.danisagan.javec;

public class SqMatrix2d {
	private double[] data = new double[4];
	
	public SqMatrix2d() {
		set(0, 0, 0.f);
		set(0, 1, 0.f);
		set(1, 0, 0.f);
		set(1, 1, 0.f);
	}
	
	public SqMatrix2d(double value) {
		set(0, 0, value);
		set(0, 1, value);
		set(1, 0, value);
		set(1, 1, value);
	}
	
	public static SqMatrix2d fromRows(double[] row0, double[] row1) {
		if(row0.length != 2 || row1.length != 2) {
			throw new IllegalArgumentException("Arguments must have length of 2.");
		}
		SqMatrix2d result = new SqMatrix2d();
		result.set(0, 0, row0[0]);
		result.set(0, 1, row0[1]);
		result.set(1, 0, row1[0]);
		result.set(1, 1, row1[1]);
		return result;
	}
	
	public static SqMatrix2d rotationMatrix(double angle) {
		return SqMatrix2d.fromRows(
				new double[] {Math.cos(angle), -Math.sin(angle)},
				new double[] {Math.sin(angle), Math.cos(angle)}
		);
	}
	
	public static SqMatrix2d id() {
		SqMatrix2d result = new SqMatrix2d();
		result.set(0, 0, 1.f);
		result.set(1, 1, 1.f);
		return result;
	}
	
	public void set(int row, int col, double value) {
		this.data[col + 2*row] = value;
	}
	
	public double get(int row, int col) {
		return this.data[col + 2*row];
	}
	
	public double determinant() {
		return this.get(0, 0)*this.get(1, 1) - this.get(0, 1)*this.get(0, 1);
	}
	
	public SqMatrix2d inv() {
		SqMatrix2d result = new SqMatrix2d();
		double det = determinant();
		result.set(0, 0, get(1, 1)/det);
		result.set(0, 1, -get(0, 1)/det);
		result.set(1, 0, -get(1, 0)/det);
		result.set(1, 1, get(0, 0)/det);
		return result;
	}
	
	public static SqMatrix2d add(SqMatrix2d... ms) {
		SqMatrix2d result = new SqMatrix2d();
		for(SqMatrix2d m: ms) {
			result.set(0, 0, result.get(0, 0) + m.get(0, 0));
			result.set(0, 1, result.get(0, 1) + m.get(0, 1));
			result.set(1, 0, result.get(1, 0) + m.get(1, 0));
			result.set(1, 1, result.get(1, 1) + m.get(1, 1));
		}
		return result;
	}
	
	public static SqMatrix2d subs(SqMatrix2d m1, SqMatrix2d m2) {
		return SqMatrix2d.add(m1, SqMatrix2d.mult(-1, m2));
	}
	
	private static SqMatrix2d simpleMult(SqMatrix2d m1, SqMatrix2d m2) {
		SqMatrix2d result = new SqMatrix2d();
		result.set(0, 0, m1.get(0, 0)*m2.get(0, 0) + m1.get(0, 1)*m2.get(1, 0));
		result.set(0, 1, m1.get(0, 0)*m2.get(0, 1) + m1.get(0, 1)*m2.get(1, 1));
		result.set(1, 0, m1.get(1, 0)*m2.get(0, 0) + m1.get(1, 1)*m2.get(1, 0));
		result.set(1, 1, m1.get(1, 0)*m2.get(0, 1) + m1.get(1, 1)*m2.get(1, 1));
		return result;
	}
	
	public static SqMatrix2d mult(SqMatrix2d... ms) {
		SqMatrix2d result = SqMatrix2d.id();
		for(SqMatrix2d m: ms) {
			result = SqMatrix2d.simpleMult(result, m);
		}
		return result;
	}
	
	public static SqMatrix2d mult(double x, SqMatrix2d m) {
		SqMatrix2d result = new SqMatrix2d();
		result.set(0, 0, m.get(0, 0)*x);
		result.set(0, 1, m.get(0, 1)*x);
		result.set(1, 0, m.get(1, 0)*x);
		result.set(1, 1, m.get(1, 1)*x);
		return result;
	}
}
