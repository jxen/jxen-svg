package com.github.jxen.svg.model.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LengthTest {

	private static final double EPSILON = 1e-5;

	@Test
	void testDefault() {
		Length length = Length.parse("1.0");
		assertEquals(1.0, length.getValue(), EPSILON);
		assertEquals(Length.LengthType.DEFAULT, length.getType());
	}

	@Test
	void testInch() {
		Length length = Length.parse("1.0in");
		assertEquals(1.0, length.getValue(), 1e-5);
		assertEquals(Length.LengthType.IN, length.getType());
	}

	@Test
	void testPixel() {
		Length length = Length.parse("1px");
		assertEquals(1.0, length.getValue(), 1e-5);
		assertEquals(Length.LengthType.PX, length.getType());
	}
}
