package com.danisagan.javec;

public class SqMatrix3d {
	private double[] data = new double[9];
	public static final int SIZE = 9;
	
	public SqMatrix3d() {
		for(int k = 0; k < SIZE; k++) {
			this.data[k] = 0.f;
		}
	}
	
	public SqMatrix3d(double value) {
		for(int k = 0; k < SIZE; k++) {
			this.data[k] = value;
		}
	}
	
	public static SqMatrix3d fromRows(double[] row0, double[] row1, double[] row2) {
		if(row0.length != 3 || row1.length != 3 || row2.length != 3) {
			throw new IllegalArgumentException("Arguments must have length of 3.");
		}
		SqMatrix3d result = new SqMatrix3d();
		result.set(0, 0, row0[0]);
		result.set(0, 1, row0[1]);
		result.set(0, 2, row0[2]);
		result.set(1, 0, row1[0]);
		result.set(1, 1, row1[1]);
		result.set(1, 2, row1[2]);
		result.set(2, 0, row2[0]);
		result.set(2, 1, row2[1]);
		result.set(2, 2, row2[2]);
		return result;
	}
	
	public static SqMatrix3d id() {
		SqMatrix3d result = new SqMatrix3d();
		for(int k = 0; k < 3; k++) {
			result.set(k, k, 1.);
		}
		return result;
	}
	
	public void set(int row, int col, double value) {
		this.data[col + 3*row] = value;
	}
	
	public double get(int row, int col) {
		return this.data[col + 3*row];
	}
	
	public double determinant() {
		return 
				get(0, 0)*(get(1, 1)*get(2, 2) - get(1, 2)*get(2, 1)) +
				get(0, 1)*(get(1, 2)*get(2, 0) - get(1, 0)*get(2, 2)) +
				get(0, 2)*(get(1, 0)*get(2, 1) - get(1, 1)*get(2, 0));
	}
	
	public SqMatrix3d inv() {
		throw new RuntimeException("Not implemented yet.");
	}
	
	public static SqMatrix3d add(SqMatrix3d... ms) {
		SqMatrix3d result = new SqMatrix3d();
		for(SqMatrix3d m: ms) {
			for(int k = 0; k < SIZE; k++) {
				result.data[k] += m.data[k];
			}
		}
		return result;
	}
	
	private static SqMatrix3d simpleMult(SqMatrix3d m1, SqMatrix3d m2) {
		SqMatrix3d result = new SqMatrix3d();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				double value = 0.f;
				for(int k = 0; k < 3; k++) {
					value += m1.get(i, k)*m2.get(k, j);
				}
				result.set(i, j, value);
			}
		}
		return result;
	}
	
	public static SqMatrix3d mult(SqMatrix3d... ms) {
		SqMatrix3d result = SqMatrix3d.id();
		for(SqMatrix3d m: ms) {
			result = SqMatrix3d.simpleMult(result, m);
		}
		return result;
	}
	
	public static SqMatrix3d mult(double x, SqMatrix3d m) {
		SqMatrix3d result = new SqMatrix3d();
		for(int k = 0; k < SIZE; k++) {
			result.data[k] = m.data[k]*x;
		}
		return result;
	}
}
