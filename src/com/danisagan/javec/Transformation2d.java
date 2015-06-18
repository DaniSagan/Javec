package com.danisagan.javec;

public class Transformation2d {
	//private SqMatrix3d matrix;
	
	private SqMatrix2d rotMatrix;
	private double angle;
	private double scale;
	private Vec2d p;
	private Vec2d pt;
	private Vec2d q;
	private Vec2d qt;
	
	/*
	public Transformation2d(Vec2d p, Vec2d q, Vec2d pt, Vec2d qt) {
		SqMatrix2d matrixP = SqMatrix2d.fromRows(
				new double[] {p.x,  p.y}, 
				new double[] {p.y, -p.x}
		); 
		SqMatrix2d matrixQ = SqMatrix2d.fromRows(
				new double[] {q.x,  q.y}, 
				new double[] {q.y, -q.x}
		); 
		SqMatrix2d matrixMu = SqMatrix2d.subs(matrixP, matrixQ).inv();
		Vec2d u = Vec2d.mult(matrixMu, Vec2d.subs(pt, qt));
		SqMatrix2d matrixMa = SqMatrix2d.subs(matrixP.inv(), matrixQ.inv()).inv();
		Vec2d aAux = Vec2d.subs(
				Vec2d.mult(matrixP.inv(), pt), 
				Vec2d.mult(matrixQ.inv(), qt)
		);
		Vec2d a = Vec2d.mult(matrixMa, aAux);
		this.matrix = SqMatrix3d.fromRows(
				new double[] {u.x,  u.y, a.x}, 
				new double[] {-u.y, u.x, a.y}, 
				new double[] {0.f,  0.f, 1.f}
		);
	}*/
	
	public Transformation2d(Vec2d p, Vec2d q, Vec2d pt, Vec2d qt) {
		this.p = p;
		this.q = q;
		this.pt = pt;
		this.qt = qt;
		this.angle = Vec2d.subs(qt, pt).arg() - Vec2d.subs(q, p).arg();
		this.scale = Vec2d.subs(pt, qt).length() / Vec2d.subs(p, q).length();
		this.rotMatrix = SqMatrix2d.rotationMatrix(angle);
	}
	
	/*public Vec2d transform(Vec2d v) { 
		Vec3d vAux = new Vec3d(v.x, v.y, 1.f);
		Vec3d vtAux = Vec3d.mult(this.matrix, vAux);
		return vtAux.projectXY();
	}*/
	
	public Vec2d transform(Vec2d v) {
		Vec2d vAux = Vec2d.scale(Vec2d.mult(this.rotMatrix, Vec2d.subs(v, this.p)), this.scale);
		return Vec2d.add(vAux, this.pt);
	}
}
