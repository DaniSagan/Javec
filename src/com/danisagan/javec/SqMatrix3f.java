package com.danisagan.javec;

public class SqMatrix3f {
	private float[] data = new float[9];
	public static final int SIZE = 9;
	
	public SqMatrix3f() {
		for(int k = 0; k < SIZE; k++) {
			this.data[k] = 0.f;
		}
	}
	
	public SqMatrix3f(float value) {
		for(int k = 0; k < SIZE; k++) {
			this.data[k] = value;
		}
	}
	
	public static SqMatrix3f fromRows(float[] row0, float[] row1, float[] row2) {
		if(row0.length != 3 || row1.length != 3 || row2.length != 3) {
			throw new IllegalArgumentException("Arguments must have length of 3.");
		}
		SqMatrix3f result = new SqMatrix3f();
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
	
	public static SqMatrix3f id() {
		SqMatrix3f result = new SqMatrix3f();
		for(int k = 0; k < 3; k++) {
			result.set(k, k, 1.f);
		}
		return result;
	}
	
	public void set(int row, int col, float value) {
		this.data[col + 3*row] = value;
	}
	
	public float get(int row, int col) {
		return this.data[col + 3*row];
	}
	
	public float determinant() {
		return 
				get(0, 0)*(get(1, 1)*get(2, 2) - get(1, 2)*get(2, 1)) +
				get(0, 1)*(get(1, 2)*get(2, 0) - get(1, 0)*get(2, 2)) +
				get(0, 2)*(get(1, 0)*get(2, 1) - get(1, 1)*get(2, 0));
	}
	
	public SqMatrix3f inv() {
		throw new RuntimeException("Not implemented yet.");
	}
	
	public static SqMatrix3f add(SqMatrix3f... ms) {
		SqMatrix3f result = new SqMatrix3f();
		for(SqMatrix3f m: ms) {
			for(int k = 0; k < SIZE; k++) {
				result.data[k] += m.data[k];
			}
		}
		return result;
	}
	
	private static SqMatrix3f simpleMult(SqMatrix3f m1, SqMatrix3f m2) {
		SqMatrix3f result = new SqMatrix3f();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				float value = 0.f;
				for(int k = 0; k < 3; k++) {
					value += m1.get(i, k)*m2.get(k, j);
				}
				result.set(i, j, value);
			}
		}
		return result;
	}
	
	public static SqMatrix3f mult(SqMatrix3f... ms) {
		SqMatrix3f result = SqMatrix3f.id();
		for(SqMatrix3f m: ms) {
			result = SqMatrix3f.simpleMult(result, m);
		}
		return result;
	}
	
	public static SqMatrix3f mult(float x, SqMatrix3f m) {
		SqMatrix3f result = new SqMatrix3f();
		for(int k = 0; k < SIZE; k++) {
			result.data[k] = m.data[k]*x;
		}
		return result;
	}
}
