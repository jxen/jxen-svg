package com.github.jxen.svg.style;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.svg.api.SvgException;
import org.junit.jupiter.api.Test;

class OverflowTest {

	@Test
	void testParse() {
		assertEquals(Overflow.AUTO, Overflow.parse("auto"));
	}

	@Test
	void testParseFailure() {
		assertThrows(SvgException.class, () -> Overflow.parse("error"));
	}
}
