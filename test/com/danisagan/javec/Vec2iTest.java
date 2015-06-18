package com.danisagan.javec;

import static org.junit.Assert.*;

import org.junit.Test;

public class Vec2iTest {

	@Test
	public void testVec2i() {
		Vec2i v = new Vec2i();
		assertEquals(0, v.x);
		assertEquals(0, v.y);
	}

	@Test
	public void testVec2iIntInt() {
		int xValue = 1;
		int yValue = 2;
		Vec2i v = new Vec2i(xValue, yValue);
		assertEquals(xValue, v.x);
		assertEquals(yValue, v.y);
	}

	@Test
	public void testAdd() {
		
	}

	@Test
	public void testSubs() {
		
	}

	@Test
	public void testCastVec2f() {
		
	}

	@Test
	public void testCastVec2d() {
		
	}

	@Test
	public void testGetQuadrant() {
		
	}

	@Test
	public void testToString() {
		
	}

	@Test
	public void testEqualsObject() {
		
	}

}
