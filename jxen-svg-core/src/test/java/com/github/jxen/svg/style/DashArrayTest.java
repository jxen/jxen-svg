package com.github.jxen.svg.style;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DashArrayTest {

	@Test
	void testParse() {
		DashArray dashArray = DashArray.parse("1  2, 1.5");
		Assertions.assertEquals("1 2 1.5", dashArray.toString());
	}

	@Test
	void testParseEmpty() {
		DashArray dashArray = DashArray.parse("");
		Assertions.assertEquals("none", dashArray.toString());
	}

	@Test
	void testParseNone() {
		DashArray dashArray = DashArray.parse("none");
		Assertions.assertEquals("none", dashArray.toString());
	}
}
