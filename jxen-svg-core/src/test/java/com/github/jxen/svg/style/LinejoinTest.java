package com.github.jxen.svg.style;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.svg.api.SvgException;
import org.junit.jupiter.api.Test;

class LinejoinTest {

	@Test
	void testParse() {
		assertEquals(Linejoin.ROUND, Linejoin.parse("round"));
	}

	@Test
	void testParseFailure() {
		assertThrows(SvgException.class, () -> Linejoin.parse("error"));
	}
}
