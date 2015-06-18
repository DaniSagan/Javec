package com.danisagan.javec;

public class Transformation2f {
	//private SqMatrix3f matrix;
	
	private SqMatrix2f rotMatrix;
	private float angle;
	private float scale;
	private Vec2f p;
	private Vec2f pt;
	private Vec2f q;
	private Vec2f qt;
	
	/*public Transformation2f(Vec2f p, Vec2f q, Vec2f pt, Vec2f qt) {
		SqMatrix2f matrixP = SqMatrix2f.fromRows(
				new float[] {p.x,  p.y}, 
				new float[] {p.y, -p.x}
		); 
		SqMatrix2f matrixQ = SqMatrix2f.fromRows(
				new float[] {q.x,  q.y}, 
				new float[] {q.y, -q.x}
		); 
		SqMatrix2f matrixMu = SqMatrix2f.subs(matrixP, matrixQ).inv();
		Vec2f u = Vec2f.mult(matrixMu, Vec2f.subs(pt, qt));
		SqMatrix2f matrixMa = SqMatrix2f.subs(matrixP.inv(), matrixQ.inv()).inv();
		Vec2f aAux = Vec2f.subs(
				Vec2f.mult(matrixP.inv(), pt), 
				Vec2f.mult(matrixQ.inv(), qt)
		);
		Vec2f a = Vec2f.mult(matrixMa, aAux);
		this.matrix = SqMatrix3f.fromRows(
				new float[] { u.x, u.y, a.x}, 
				new float[] {-u.y, u.x, a.y}, 
				new float[] { 0.f, 0.f, 1.f}
		);
	}*/
	
	public Transformation2f(Vec2f p, Vec2f q, Vec2f pt, Vec2f qt) {
		this.p = p;
		this.q = q;
		this.pt = pt;
		this.qt = qt;
		this.angle = Vec2f.subs(qt, pt).arg() - Vec2f.subs(q, p).arg();
		this.scale = Vec2f.subs(pt, qt).length() / Vec2f.subs(p, q).length();
		this.rotMatrix = SqMatrix2f.rotationMatrix(angle);
	}
	
	/*public Vec2f transform(Vec2f v) { 
		Vec3f vAux = new Vec3f(v.x, v.y, 1.f);
		Vec3f vtAux = Vec3f.mult(this.matrix, vAux);
		return vtAux.projectXY();
	}*/
	
	public Vec2f transform(Vec2f v) {
		Vec2f vAux = Vec2f.scale(Vec2f.mult(this.rotMatrix, Vec2f.subs(v, this.p)), this.scale);
		return Vec2f.add(vAux, this.pt);
	}	

}
