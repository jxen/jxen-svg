package com.github.jxen.svg.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TransformTest {

	@Test
	void testMatrix() {
		Transform transform = Transform.of(1, 0, 1, 2, 1, 1);
		assertEquals("matrix(1,0,1,2,1,1)", transform.toString());
	}

	@Test
	void testTranslate1() {
		Transform transform = Transform.of(1, 0, 0, 1, 1, 0);
		assertEquals("translate(1)", transform.toString());
	}

	@Test
	void testTranslate2() {
		Transform transform = Transform.of(1, 0, 0, 1, 1, 1);
		assertEquals("translate(1,1)", transform.toString());
	}

	@Test
	void testScale1() {
		Transform transform = Transform.of(2, 0, 0, 2, 0, 0);
		assertEquals("scale(2)", transform.toString());
	}

	@Test
	void testScale2() {
		Transform transform = Transform.of(2, 0, 0, 1, 0, 0);
		assertEquals("scale(2,1)", transform.toString());
	}

	@Test
	void testRotate1() {
		Transform transform = Transform.of(0, 1, -1, 0, 0, 0);
		assertEquals("rotate(90)", transform.toString());
	}

	@Test
	void testRotate2() {
		Transform transform = Transform.of(0, 1, -1, 0, 20, 0);
		assertEquals("rotate(90,10,10)", transform.toString());
	}

	@Test
	void testSkewX() {
		Transform transform = Transform.of(1, 0, 1, 1, 0, 0);
		assertEquals("skewX(45)", transform.toString());
	}

	@Test
	void testSkewY() {
		Transform transform = Transform.of(1, 1, 0, 1, 0, 0);
		assertEquals("skewY(45)", transform.toString());
	}
}
